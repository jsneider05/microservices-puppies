package com.puppies.user.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.puppies.security.auth.model.UserRoleEnum;
import com.puppies.user.domain.model.User;
import com.puppies.user.domain.model.UserRole;
import com.puppies.user.domain.port.dao.UserSecurityDao;
import com.puppies.user.domain.port.dao.UserSecurityRoleDao;
import com.puppies.user.domain.port.repository.UserRepository;
import com.puppies.user.domain.port.repository.UserSecurityRepository;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

  private static final String EMAIL_ALREADY_EXISTS = "Email already exist";
  private static final int ONE_INVOCATION = 1;

  private CreateUserService underTest;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserSecurityRoleDao userSecurityRoleDao;

  @Mock
  private UserSecurityRepository userSecurityRepository;

  @Mock
  private UserSecurityDao userSecurityDao;

  @BeforeEach
  void setUp() {
    underTest = new CreateUserService(userSecurityRepository, userSecurityDao, userSecurityRoleDao,
        userRepository);
  }

  @Test
  void canAddUserTest() {
    // Given
    User user = User.builder()
        .firstName("joan")
        .email("joan@gmail.com")
        .build();

    given(userSecurityDao.existsByEmail(anyString())).willReturn(Boolean.FALSE);
    given(userRepository.save(user)).willReturn(UUID.randomUUID());
    given(userSecurityRoleDao.getAll()).willReturn(
        Set.of(UserRole.builder()
            .id(UUID.randomUUID())
            .name(UserRoleEnum.CUSTOMER.name())
            .build()));

    // When
    UUID userIdCreated = underTest.execute(user);

    // Then
    InOrder order = inOrder(userSecurityDao, userRepository, userSecurityRoleDao,
        userSecurityRepository);

    order.verify(userSecurityDao, times(ONE_INVOCATION)).existsByEmail(user.getEmail());
    order.verify(userRepository, times(ONE_INVOCATION)).save(user);
    order.verify(userSecurityRoleDao, times(ONE_INVOCATION)).getAll();

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    order.verify(userSecurityRepository, times(ONE_INVOCATION)).save(userArgumentCaptor.capture());

    User capturedUser = userArgumentCaptor.getValue();

    assertThat(userIdCreated).isEqualTo(capturedUser.getId());
    assertThat(capturedUser).isEqualTo(user);
    assertThat(capturedUser.getUserRoles())
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  void willThrowWhenAddUserWithEmailIsTakenTest() {
    // Given
    User user = User.builder()
        .firstName("joan")
        .email("joan@gmail.com")
        .build();

    given(userSecurityDao.existsByEmail(anyString())).willReturn(Boolean.TRUE);

    // When
    // Then
    assertThatThrownBy(() -> underTest.execute(user))
        .hasMessageContaining(EMAIL_ALREADY_EXISTS, user.getEmail())
        .isInstanceOf(IllegalArgumentException.class);

    verify(userSecurityDao, times(ONE_INVOCATION)).existsByEmail(user.getEmail());
    verify(userRepository, never()).save(any());
  }

}
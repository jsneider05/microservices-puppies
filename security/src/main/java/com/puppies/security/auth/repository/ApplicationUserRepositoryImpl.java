package com.puppies.security.auth.repository;

import com.puppies.security.auth.entity.UserEntity;
import com.puppies.security.auth.mapper.ApplicationUserMapper;
import com.puppies.security.auth.model.ApplicationUser;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository("postgresApplicationUserRepository")
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

  private final ApplicationUserJpaRepository applicationUserJpaRepository;
  private final ApplicationUserMapper applicationUserMapper;

  @Override
  public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
    return this.applicationUserMapper.mapToApplicationUser(
        this.applicationUserJpaRepository.selectApplicationUserByUsername(username));
  }

  @Override
  public UserEntity create(UserEntity userEntity) {
    return applicationUserJpaRepository.save(userEntity);
  }

  @Override
  public Boolean existByEmail(String email) {
    return applicationUserJpaRepository.selectExistsByEmail(email);
  }
}

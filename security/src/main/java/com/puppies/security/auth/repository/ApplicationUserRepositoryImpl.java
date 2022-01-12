package com.puppies.security.auth.repository;

import com.puppies.security.auth.entity.UserSecurityEntity;
import com.puppies.security.auth.mapper.ApplicationUserMapper;
import com.puppies.security.auth.model.ApplicationUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository("postgresApplicationUserRepository")
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

  private final ApplicationUserJpaRepository applicationUserJpaRepository;
  private final ApplicationUserMapper applicationUserMapper;

  @Override
  public ApplicationUser selectApplicationUserRolePrivilegesByUsername(String username) {
    return this.applicationUserMapper.mapToApplicationUser(
        this.applicationUserJpaRepository.selectApplicationUserByUsername(username));
  }

  @Override
  public ApplicationUser selectApplicationMainUserDataByUsername(String username) {
    return this.applicationUserMapper.mapToApplicationMainUserData(
        this.applicationUserJpaRepository.selectApplicationUserByUsername(username));
  }

  @Override
  public UserSecurityEntity create(UserSecurityEntity userSecurityEntity) {
    return applicationUserJpaRepository.save(userSecurityEntity);
  }

  @Override
  public Boolean existByEmail(String email) {
    return applicationUserJpaRepository.selectExistsByEmail(email);
  }
}

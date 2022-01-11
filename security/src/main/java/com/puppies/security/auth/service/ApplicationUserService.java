package com.puppies.security.auth.service;

import com.puppies.security.auth.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationUserService implements UserDetailsService {

  private final ApplicationUserRepository applicationUserRepository;

  public ApplicationUserService(
      @Qualifier("postgresApplicationUserRepository") ApplicationUserRepository applicationUserRepository) {
    this.applicationUserRepository = applicationUserRepository;
  }

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return applicationUserRepository
        .selectApplicationUserByUsername(username)
        .orElseThrow(() ->
            new UsernameNotFoundException(String.format("Username %s not found", username))
        );
  }
}

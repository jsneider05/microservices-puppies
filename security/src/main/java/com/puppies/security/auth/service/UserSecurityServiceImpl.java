package com.puppies.security.auth.service;

import com.puppies.security.auth.model.ApplicationUser;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserSecurityServiceImpl implements UserSecurityService {

  private final PasswordEncoder passwordEncoder;

  @Override
  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  @Override
  public UUID getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
      return applicationUser.getUserId();
    }
    throw new IllegalArgumentException("User not found!");
  }
}

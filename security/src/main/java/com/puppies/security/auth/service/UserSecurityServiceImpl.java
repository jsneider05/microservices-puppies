package com.puppies.security.auth.service;

import com.puppies.security.auth.model.ApplicationUser;
import java.util.UUID;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityServiceImpl implements UserSecurityService {

  @Override
  public UUID getId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
      return applicationUser.getId();
    }
    throw new IllegalArgumentException("User not found!");
  }
}

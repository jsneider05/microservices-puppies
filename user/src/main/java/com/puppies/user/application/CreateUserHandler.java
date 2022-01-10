package com.puppies.user.application;

import com.puppies.user.application.factory.UserFactory;
import com.puppies.user.application.request.CreateUserRequest;
import com.puppies.user.domain.service.CreateUserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateUserHandler {

  private final CreateUserService service;
  private final UserFactory userFactory;

  @Transactional
  public UUID execute(CreateUserRequest request) {
    return service.execute(
        userFactory.create(request)
    );
  }
}

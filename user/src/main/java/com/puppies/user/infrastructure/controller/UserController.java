package com.puppies.user.infrastructure.controller;

import com.puppies.user.application.CreateUserHandler;
import com.puppies.user.application.request.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
@RestController
@RequestMapping(path = "/v1/user")
@Tag(name = "User", description = "User Controller")
@Validated
public class UserController {

  private final CreateUserHandler createUserHandler;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create User", description = "User creation", tags = { "user-creation" })
  public UUID createTransfer(@Valid @RequestBody CreateUserRequest request) {
    return createUserHandler.execute(request);
  }

//  @GetMapping
//  @ResponseStatus(HttpStatus.CREATED)
//  @Operation(summary = "Create User", description = "User creation", tags = { "user-creation" })
//  public UUID createTransfer(@Valid @RequestBody CreateUserRequest request) {
//    return createUserHandler.execute(request);
//  }

}

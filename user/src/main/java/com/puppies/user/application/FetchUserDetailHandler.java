package com.puppies.user.application;

import com.puppies.security.auth.service.UserSecurityService;
import com.puppies.user.application.factory.UserResponseMapper;
import com.puppies.user.application.response.UserDetailResponse;
import com.puppies.user.domain.port.dao.UserDao;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FetchUserDetailHandler {

  private final UserDao dao;
  private final UserSecurityService userSecurityService;
  private final UserResponseMapper mapper;

  public UserDetailResponse execute() {
    UUID userId = userSecurityService.getUserId();
    return mapper.toUserDetailResponse(dao.getUserDetailsById(userId));
  }

}

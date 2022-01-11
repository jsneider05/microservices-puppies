package com.puppies.security.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermissionEnum {

    POST_CREATE("post:write"),
    POST_READ("post:read"),
    POST_UPDATE("post:update"),
    POST_DELETE("post:delete"),
    POST_ARCHIVE("post:archive");

    private final String permission;

}

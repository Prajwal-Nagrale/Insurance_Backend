package com.cg.users.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    MEMBER_READ("management:read"),
    MEMBER_CREATE("management:create"),
    VIEWER_READ("view:read"),
    VIEWER_CREATE("view:CREATE")
    ;

    @Getter
    private final String permission;
}

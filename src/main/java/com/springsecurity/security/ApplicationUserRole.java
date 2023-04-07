package com.springsecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet(COURSE_WRITE)),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMINISTRATION(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> userPermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }
    public Set<ApplicationUserPermission> getUserPermissions() {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getPermissionAndRole() {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getUserPermissions().stream()
                .map(permiss -> new SimpleGrantedAuthority(permiss.getPermissions()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return simpleGrantedAuthorities;
    }
}

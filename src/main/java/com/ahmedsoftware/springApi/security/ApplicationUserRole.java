package com.ahmedsoftware.springApi.security;

import com.google.common.collect.Sets;

import java.util.Set;


import static com.ahmedsoftware.springApi.security.ApplicatonUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet(COURSE_READ,STUDENT_READ,STUDENT_WRITE)),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));
    
    
    private final Set<ApplicatonUserPermission> permissions;
    
    ApplicationUserRole(Set<ApplicatonUserPermission> permissions) {
        this.permissions = permissions;
    }
}

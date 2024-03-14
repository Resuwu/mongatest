package com.example.mongatest.service;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.model.Client;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<ApplicationUser> find(String userId);
    Boolean permissionCheck(ApplicationUser user, String permission);
    ApplicationUser permissionUpdate(ApplicationUser user, String permission, Boolean b);
    List<ApplicationUser> findUsersWithPermission(String permission, Boolean b);
}
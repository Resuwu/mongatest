package com.example.mongatest.service;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.model.Client;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    void signUpNewUser(ApplicationUser user);

    void removeUser(String userId);

    Optional<ApplicationUser> find(String userId);

    Boolean permissionCheck(ApplicationUser user, String permission);

    ApplicationUser addPermission(ApplicationUser user, String permission);

    ApplicationUser removePermission(ApplicationUser user, String permission);

    List<ApplicationUser> findUsersWithPermission(String permission);
}
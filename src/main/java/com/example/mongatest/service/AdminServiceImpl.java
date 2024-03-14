package com.example.mongatest.service;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.repos.ClientRepository;
import com.example.mongatest.repos.UserRepository;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<ApplicationUser> find(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Boolean permissionCheck(ApplicationUser user, String permission) {
        return user.getPermissions().get(permission);
    }

    @Override
    public ApplicationUser permissionUpdate(ApplicationUser user, String permission, Boolean b) {
        if (user.setPermission(permission, b)) return userRepository.save(user);
        else return null;
    }

    @Override
    public List<ApplicationUser> findUsersWithPermission(String permission, Boolean b) {
        return userRepository.findUsersByPermission(permission, b);
    }
}

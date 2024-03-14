package com.example.mongatest.service;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final Set<String> aviablePermissions = Set.of("ADMIN","POST","GET","PUT","DELETE");

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUpNewUser(ApplicationUser user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<ApplicationUser> find(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Boolean permissionCheck(ApplicationUser user, String permission) {
        return user.getPermissions().contains(permission);
    }

    @Override
    public ApplicationUser addPermission(ApplicationUser user, String permission) {
        if (aviablePermissions.contains(permission)) {
            user.addPermission(permission);
            return userRepository.save(user);
        }
        else return null;
    }

    @Override
    public ApplicationUser removePermission(ApplicationUser user, String permission) {
        if (aviablePermissions.contains(permission)) {
            return user.removePermission(permission)
                    ? userRepository.save(user)
                    : null;
        }
        else return null;
    }

    @Override
    public List<ApplicationUser> findUsersWithPermission(String permission) {
        return userRepository.findUsersByPermission(permission);
    }
}

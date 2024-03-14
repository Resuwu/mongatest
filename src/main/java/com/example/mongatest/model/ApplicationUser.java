package com.example.mongatest.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Document
public class ApplicationUser {
    @Id
    @Setter
    private String id;
    private Set<String> permissions;

    public ApplicationUser() {
        permissions = new HashSet<>();
    }

    public void addPermission(String permission) {
        permissions.add(permission);
    }
    public boolean removePermission(String permission) {
        if (permissions.contains(permission)) {
            permissions.remove(permission);
            return true;
        }
        return false;
    }
}

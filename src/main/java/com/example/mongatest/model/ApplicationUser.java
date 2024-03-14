package com.example.mongatest.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Document
public class ApplicationUser {
    @Id
    @Setter
    private String id;
    private Set<String> permissions;

    ApplicationUser(String id) {
        this.id = id;
    }

    public boolean addPermission(String permission) {

    }
    public boolean removePermission(String permission) {

    }
}

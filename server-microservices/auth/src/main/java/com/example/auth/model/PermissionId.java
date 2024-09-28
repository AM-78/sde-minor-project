package com.example.auth.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
import java.util.Objects;

@Getter
@Setter
public class PermissionId implements Serializable {
    private UUID user;
    private UUID docId;

    // Default constructor
    public PermissionId() {}

    // Parameterized constructor
    public PermissionId(UUID user, UUID docId) {
        this.user = user;
        this.docId = docId;
    }


    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionId that = (PermissionId) o;
        return Objects.equals(user, that.user) && Objects.equals(docId, that.docId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, docId);
    }
}
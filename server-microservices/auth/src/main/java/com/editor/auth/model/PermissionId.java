package com.editor.auth.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
import java.util.Objects;

@Getter
@Setter
public class PermissionId implements Serializable {
    private UUID userId;
    private UUID docId;

    // Default constructor
    public PermissionId() {}

    // Parameterized constructor
    public PermissionId(UUID userId, UUID docId) {
        this.userId = userId;
        this.docId = docId;
    }


    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionId that = (PermissionId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(docId, that.docId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, docId);
    }
}
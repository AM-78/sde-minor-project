package com.editor.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@IdClass(PermissionId.class)
@Table(name = "\"PERMISSION\"")
@Getter
@Setter
public class Permission {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @Column(name = "doc_id")
    private UUID docId;

    @Column(name = "can_write")
    private int canWrite;

    // No-argument constructor
    public Permission() {
    }

    // Constructor with arguments
    public Permission(User user, UUID docId, int canWrite) {
        this.user = user;
        this.docId = docId;
        this.canWrite = canWrite;
    }
}
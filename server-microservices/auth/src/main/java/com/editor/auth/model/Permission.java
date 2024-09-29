package com.editor.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@IdClass(PermissionId.class)
@Table(name = "PERMISSION")
@Getter
@Setter
public class Permission {
    @Id
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(name = "user_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    @Id
    @Column(name = "doc_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID docId;

    @Column(name = "can_write")
    private int canWrite;

    // No-argument constructor
    public Permission() {
    }

    // Constructor with arguments
    public Permission(UUID userId, UUID docId, int canWrite) {
        this.userId = userId;
        this.docId = docId;
        this.canWrite = canWrite;
    }
}
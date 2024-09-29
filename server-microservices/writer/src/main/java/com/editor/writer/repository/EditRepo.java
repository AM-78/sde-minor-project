package com.editor.writer.repository;

import com.editor.writer.model.Edit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EditRepo extends JpaRepository<Edit,Integer> {

    List<Edit> findByDocIdAndVersionGreaterThan(UUID docId, int version);
}

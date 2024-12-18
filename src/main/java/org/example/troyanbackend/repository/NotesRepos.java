package org.example.troyanbackend.repository;

import org.example.troyanbackend.entity.NotesTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotesRepos extends JpaRepository<NotesTable, UUID> {
}

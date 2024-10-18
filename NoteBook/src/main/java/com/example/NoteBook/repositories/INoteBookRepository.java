package com.example.NoteBook.repositories;

import com.example.NoteBook.entities.NoteBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class INoteBookRepository extends JpaRepository<NoteBookEntity, Long> {
    List<NoteBookEntity> findByFirstNameContainingIgnoreCase(String firstName);
}

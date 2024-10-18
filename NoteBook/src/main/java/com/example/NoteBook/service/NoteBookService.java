package com.example.NoteBook.service;

import com.example.NoteBook.dto.errorDTO.NoteBookErrorDTO;
import com.example.NoteBook.entities.NoteBookEntity;

import java.util.List;

public interface NoteBookService {
    NoteBookErrorDTO save(NoteBookEntity dataNoteBookCover)throws Exception;

    List<NoteBookErrorDTO> searchAll()throws Exception;

    List<NoteBookErrorDTO> searchByName(String name) throws Exception;

    NoteBookErrorDTO modify(Integer id, NoteBookEntity noteBook)throws Exception;

    Boolean delete(Integer id);
}

package com.example.NoteBook.service.impl;

import com.example.NoteBook.dto.errorDTO.NoteBookErrorDTO;
import com.example.NoteBook.entities.NoteBookEntity;
import com.example.NoteBook.mapper.INoteBookMapper;
import com.example.NoteBook.repositories.INoteBookRepository;
import com.example.NoteBook.service.NoteBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteBookServiceImpl implements NoteBookService {
    @Autowired
    private INoteBookRepository iNoteBookRepository;

    @Autowired
    private INoteBookMapper iNoteBookMapper;

    @Override
    @Transactional
    public NoteBookErrorDTO save(NoteBookEntity dataNoteBookCover) throws Exception {
        try {
            return iNoteBookMapper.noteBookToDTO(iNoteBookRepository.save(dataNoteBookCover));
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    @Override
    @Transactional
    public List<NoteBookErrorDTO> searchAll() throws Exception {
        try {
            return iNoteBookRepository.findAll().stream()
                    .map(iNoteBookMapper::noteBookToDTO)
                    .collect(Collectors.toList());
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    @Override
    @Transactional
    public List<NoteBookErrorDTO> searchByName(String name) throws Exception {
        List<NoteBookEntity> bookEntities = iNoteBookRepository.findByFirstNameContainingIgnoreCase(name);
        if (bookEntities.isEmpty()) {
            throw new Exception("No se encontraron libros con el título proporcionado.");
        }
        return bookEntities.stream()
                .map(iNoteBookMapper::noteBookToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NoteBookErrorDTO modify(Integer id, NoteBookEntity noteBook) throws Exception {
        try {
            Optional<NoteBookEntity> optionalNoteBook = iNoteBookRepository.findById(Long.valueOf(id));
            if (optionalNoteBook.isPresent()) {
                NoteBookEntity existingNoteBook = optionalNoteBook.get();
                existingNoteBook.setImg(noteBook.getImg());
                existingNoteBook.setEmail(noteBook.getEmail());
                existingNoteBook.setSurname(noteBook.getSurname());
                existingNoteBook.setFirstName(noteBook.getFirstName());

                return iNoteBookMapper.noteBookToDTO(iNoteBookRepository.save(existingNoteBook));
            } else {
                throw new Exception("NoteBook not found");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        Optional<NoteBookEntity> delete = iNoteBookRepository.findById(Long.valueOf(id));
        if (delete.isPresent()) {
            iNoteBookRepository.delete(delete.get());
            return true;
        }
        return false;
    }
}

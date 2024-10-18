package com.example.NoteBook.controllers;

import com.example.NoteBook.dto.NoteBookDTO;
import com.example.NoteBook.dto.errorDTO.NoteBookErrorDTO;
import com.example.NoteBook.entities.NoteBookEntity;
import com.example.NoteBook.service.NoteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin(originPatterns = "*")
public class NoteBookController {
    @Autowired
    private NoteBookService noteBookService;

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody NoteBookEntity dataReceived){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.noteBookService.save(dataReceived));
        }catch (Exception error){
            NoteBookDTO errorPersonalized = new NoteBookDTO();
            errorPersonalized.setMessageError(error.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorPersonalized.getMessageError());
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchAll(){
        try {
            List<NoteBookErrorDTO> noteBookErrorDTOList = (List<NoteBookErrorDTO>) this.noteBookService.searchAll();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(noteBookErrorDTOList);
        }catch (Exception error){
            NoteBookDTO noteBookPersonalized = new NoteBookDTO();
            noteBookPersonalized.setMessageError(error.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(noteBookPersonalized.getMessageError());
        }

    }
    @GetMapping("/name")
    public ResponseEntity<?> searchByName(@RequestParam String firstName){
        try {
            List<NoteBookErrorDTO> noteBookErrorDTOList = this.noteBookService.searchByName(firstName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(noteBookErrorDTOList);
        }catch (Exception e){
            NoteBookDTO errorPersonalized = new NoteBookDTO();
            errorPersonalized.setMessageError(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorPersonalized.getMessageError());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@RequestBody NoteBookEntity dataReceived, @PathVariable Integer id){
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(this.noteBookService.modify(id, dataReceived));
        }catch (Exception error){
            NoteBookDTO errorPersonalized = new NoteBookDTO();
            errorPersonalized.setMessageError(error.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorPersonalized.getMessageError());
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public Optional<ResponseEntity<?>> delete(@PathVariable int id){
        Boolean result = noteBookService.delete(id);

        if (result){
            return Optional.of(ResponseEntity.noContent().build());
        }
        return Optional.of(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}

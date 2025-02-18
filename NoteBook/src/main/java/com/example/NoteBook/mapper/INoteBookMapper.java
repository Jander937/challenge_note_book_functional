package com.example.NoteBook.mapper;

import com.example.NoteBook.dto.errorDTO.NoteBookErrorDTO;
import com.example.NoteBook.entities.NoteBookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public class INoteBookMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "img", target = "img"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "email", target = "email")
    })
    NoteBookErrorDTO noteBookToDTO(NoteBookEntity noteBook);

    List<NoteBookErrorDTO> toDTOList(List<NoteBookEntity> noteBookEntities);
}

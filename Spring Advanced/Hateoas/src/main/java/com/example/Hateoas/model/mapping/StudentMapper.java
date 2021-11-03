package com.example.Hateoas.model.mapping;

import com.example.Hateoas.model.dto.StudentDTO;
import com.example.Hateoas.model.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO mapEntityToDTO(StudentEntity student);
}

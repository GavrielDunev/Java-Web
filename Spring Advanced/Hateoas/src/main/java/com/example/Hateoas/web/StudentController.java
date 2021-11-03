package com.example.Hateoas.web;

import com.example.Hateoas.model.dto.OrderDTO;
import com.example.Hateoas.model.dto.StudentDTO;
import com.example.Hateoas.model.entity.OrderEntity;
import com.example.Hateoas.model.entity.StudentEntity;
import com.example.Hateoas.model.mapping.StudentMapper;
import com.example.Hateoas.repository.StudentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentController(StudentRepository studentRepository,
                             StudentMapper studentMapper) {
        //Not a good idea to inject repositories here.

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {
        List<EntityModel<StudentDTO>> allStudents = this.studentRepository
                .findAll()
                .stream()
                .map(this.studentMapper::mapEntityToDTO)
                .map(studentDTO -> EntityModel.of(studentDTO, createStudentLinks(studentDTO)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(allStudents));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable Long id) {

        StudentDTO student = this.studentRepository.findById(id)
                .map(this.studentMapper::mapEntityToDTO)
                .orElseThrow();

        return ResponseEntity.ok(EntityModel.of(student, createStudentLinks(student)));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getOrders(@PathVariable Long id) {

        StudentEntity student = this.studentRepository.findById(id).orElseThrow();

        List<EntityModel<OrderDTO>> orders = student.getOrders()
                .stream()
                .map(this::map)
                .map(EntityModel::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(orders));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> update(@PathVariable Long id,
                                                          StudentDTO studentDTO) {
        //Implementation not needed.

        return ResponseEntity.ok().build();
    }

    private OrderDTO map(OrderEntity orderEntity) {
        return new OrderDTO().setId(orderEntity.getId()).setStudentId(orderEntity.getStudent().getId())
                .setCourseId(orderEntity.getCourse().getId());
    }

    private Link[] createStudentLinks(StudentDTO studentDTO) {
        List<Link> result = new ArrayList<>();

        Link selfLink = linkTo(methodOn(StudentController.class)
                .getStudentById(studentDTO.getId())).withSelfRel();

        result.add(selfLink);

        Link updateLink = linkTo(methodOn(StudentController.class).update(studentDTO.getId(), studentDTO))
                .withRel("update");

        result.add(updateLink);

        Link orderLink = linkTo(methodOn(StudentController.class).getOrders(studentDTO.getId()))
                .withRel("orders");

        result.add(orderLink);

        return result.toArray(new Link[0]);
    }
}

package com.example.crud.demo.crud.controller;


import com.example.crud.demo.crud.model.Student;
import com.example.crud.demo.crud.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("adminDept")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    RegistrationService service;

    @GetMapping
    public Flux<Student> getAll() {
        System.out.println("::will returns ALL Students records::");
        return service.getAll();
    }

    @GetMapping("{id}")
    public Mono<Student> getById(@PathVariable("id") final String id) {
        System.out.println("::will return a Student record::");
        return service.getById(id);
    }

    @PostMapping
    public  Mono save(@RequestBody final Student student){
        System.out.println("will insert the student's record :: "+ student.getId() + " :: " + student.getFirtsName());
        return service.save(student);

    }
    @PutMapping("{id}")
    public  Mono update(@PathVariable("id") final String id, @RequestBody final Student student){
        System.out.println("::update the Student record::");
        return service.update(id,student);
    }
    @DeleteMapping("{id}")
    public Mono delete(@PathVariable final String id){
        System.out.println("::will delete a Student record::");
        return service.delete(id);

    }


}

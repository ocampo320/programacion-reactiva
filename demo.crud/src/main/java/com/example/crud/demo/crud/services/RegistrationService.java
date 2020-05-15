package com.example.crud.demo.crud.services;

import com.example.crud.demo.crud.model.Student;
import com.example.crud.demo.crud.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    RegistrationRepository repository;


    public Flux<Student>getAll(){
        return  repository.findAll().switchIfEmpty(Flux.empty());
    }


    public Mono<Student>getById(final String id){
        return repository.findById(id);
    }
    public Mono<Student> update(final String id,final Student student){
        return repository.save(student);

    }
    public Mono<Student> save(final Student student){
        return  repository.save(student);
    }
    public  Mono<Student> delete (final String id){
        final Mono<Student> dbStudent=getById(id);
        if (Objects.isNull(dbStudent)){
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(studentToBeDeleted -> repository
                .delete(studentToBeDeleted).then(Mono.just(studentToBeDeleted)));

    }
}

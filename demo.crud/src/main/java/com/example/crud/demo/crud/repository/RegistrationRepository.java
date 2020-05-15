package com.example.crud.demo.crud.repository;

import com.example.crud.demo.crud.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository  extends ReactiveMongoRepository<Student,String> {
}

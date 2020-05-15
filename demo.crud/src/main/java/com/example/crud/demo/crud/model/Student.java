package com.example.crud.demo.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document
@ToString
public class Student {

    private  String id;
    private  String firtsName;
    private  String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

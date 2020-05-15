package com.profile.reactor.profile.reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    private String id;

    private String email;

    public Profile(String id, String email) {
        this.id = id;
        this.email = email;
    }
}

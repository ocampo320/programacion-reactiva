package com.profile.reactor.profile.reactor;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfileRepository  extends ReactiveMongoRepository<Profile, String> {
}

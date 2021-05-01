package com.s1cket.labs.client.model.repository;

import com.s1cket.labs.client.model.dao.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
package com.s1cket.labs.client.model.repository;

import com.s1cket.labs.client.model.dao.LetterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends CrudRepository<LetterEntity, Long> {

}
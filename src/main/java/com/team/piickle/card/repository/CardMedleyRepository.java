package com.team.piickle.card.repository;

import com.team.piickle.card.domain.CardMedley;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardMedleyRepository extends MongoRepository<CardMedley, String> {

    List<CardMedley> findAll();
}

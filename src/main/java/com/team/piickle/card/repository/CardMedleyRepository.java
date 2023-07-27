package com.team.piickle.card.repository;

import com.team.piickle.card.domain.CardMedley;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardMedleyRepository extends MongoRepository<CardMedley, String> {

    List<CardMedley> findAll();
}

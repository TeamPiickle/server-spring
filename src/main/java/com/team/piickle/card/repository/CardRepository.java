package com.team.piickle.card.repository;

import com.team.piickle.bookmark.dto.CardIdAndCntDto;
import com.team.piickle.card.domain.Card;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findAllByIdIn(List<String> idList);

    @Aggregation(pipeline = {"{'$match':{'_id': { '$nin': ?0 }} }", "{'$sort' :  {'_id' :  1}}", "{'$limit': ?1}"})
    List<CardIdAndCntDto> findByNIdAndSortAndExtraLimit(List<String> ids, int size);

    List<Card> findAllByCategory(String id);

    @Query("{'filter' : {'$all' : ?0}}")
    List<Card> findByFilter(List<String> search);


}

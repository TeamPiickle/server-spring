package com.team.piickle.card.repository;

import com.team.piickle.card.domain.Card;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findAllByIdIn(List<String> idList);

    // @Query("select c.id, c.content, c.filters, c.tags from Card c where c.id in :ids")
    // List<Card> findAllByIdList(List<String> ids);
}

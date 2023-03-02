package com.team.piickle.card.repository;

import com.team.piickle.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select c.id, c.content, c.filters, c.tags from Card c where c.id in :ids")
    List<Card> findAllByIdList(List<Long> ids);
}

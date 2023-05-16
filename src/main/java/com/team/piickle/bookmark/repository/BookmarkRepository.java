package com.team.piickle.bookmark.repository;

import com.team.piickle.bookmark.domain.Bookmark;
import com.team.piickle.bookmark.dto.CardIdAndCntDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {

    Optional<Bookmark> findByUserAndCard(ObjectId user, ObjectId card);
    List<Bookmark> findAll();

    @Aggregation(pipeline = {"{'$group':{'_id':$card, 'count': {$sum: 1} }}",
    "{'$sort' : {'count' : -1, '_id' :  1}}", "{'$limit': ?0}"})
    List<CardIdAndCntDto> groupByCardAndSort(int size);
}

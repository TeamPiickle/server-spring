package com.team.piickle.ballot.repository;

import com.team.piickle.ballot.domain.BallotTopic;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BallotTopicRepository extends MongoRepository<BallotTopic, String> {

    Optional<BallotTopic> findById(String id);

    @Query(sort = "{order :  -1}")
    List<BallotTopic> findAllOrderByOrder(Pageable pageable);

    @Query(sort = "{order :  -1}")
    List<BallotTopic> findAllByOrderByOrder(Pageable pageable);

    @Query(value = "{ '_id' :  {'$in' : ?0}}", sort = "{order :  1}")
    List<BallotTopic> findByIdInOrderByOrder(List<String> ids, Pageable pageable);

    @Query(value = "{ '_id' :  {'$nin' : ?0}}", sort = "{order :  1}")
    List<BallotTopic> findByIdNinOrderByOrder(List<String> collect, PageRequest of);

    @Query(value = "{'order' : {'$gt' : ?0}}", sort = "{order :  -1}")
    List<BallotTopic> findOneSmallerOrder(long standard);

    @Query(value = "{'order' : {'$lt' : ?0}}", sort = "{order :  1}")
    List<BallotTopic> findOneBiggerOrder(long standard);
}

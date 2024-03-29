package com.team.piickle.ballot.repository;

import com.team.piickle.ballot.domain.BallotItem;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BallotItemRepository extends MongoRepository<BallotItem, String> {
    Optional<BallotItem> findById(ObjectId ballotItemId);

    List<BallotItem> findByBallotTopicId(ObjectId ballotTopicId);
}

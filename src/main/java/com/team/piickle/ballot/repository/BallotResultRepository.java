package com.team.piickle.ballot.repository;

import com.team.piickle.ballot.domain.BallotResult;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BallotResultRepository extends MongoRepository<BallotResult, String> {
    Optional<BallotResult> findByBallotTopicIdAndUserIdAndGuestId(
            ObjectId ballotTopicId, ObjectId userId, ObjectId guestId);

    BallotResult findByBallotTopicIdAndUserIdOrGuestId(
            String ballotTopicId, String userId, String guestId);

    List<BallotResult> findByUserId(String userId);

    List<BallotResult> findByBallotTopicId(String ballotTopicId);

    List<BallotResult> findByBallotItemId(String id);

    Optional<BallotResult> findByBallotTopicIdAndUserId(ObjectId ballotTopicId, ObjectId userId);
}

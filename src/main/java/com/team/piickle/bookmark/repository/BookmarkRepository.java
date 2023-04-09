package com.team.piickle.bookmark.repository;

import com.team.piickle.bookmark.domain.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {

    // @Query("select bk.card.id from Bookmark bk group by bk.card.id order by count(bk.card.id)
    // DESC")
    // List<Long> findBestCardsId();

    // boolean findByUser(User user);
    // Bookmark findByUserIdAndCardId(String id, String id1);
}

package com.team.piickle.bookmark.repository;

import com.team.piickle.bookmark.domain.Bookmark;
import com.team.piickle.card.domain.Card;
import com.team.piickle.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select bk.card.id from Bookmark bk group by bk.card.id order by count(bk.card.id) DESC")
    List<Long> findBestCardsId();

    boolean findByUser(User user);
    Bookmark findByUserIdAndCardId(Long id, Long id1);
}

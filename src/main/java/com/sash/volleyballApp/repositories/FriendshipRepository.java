package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByPlayer1IdOrPlayer2IdAndStatus(Long player1Id, Long player2Id, Friendship.Status status);
}


package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Friendship;
import com.sash.volleyballApp.models.Friendship.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findByPlayer1IdAndStatus(Long playerId, Status status);

    List<Friendship> findByPlayer2IdAndStatus(Long playerId, Status status);

    List<Friendship> findByPlayer1IdOrPlayer2IdAndStatus(Long playerId, Long player2Id, Status status);
}

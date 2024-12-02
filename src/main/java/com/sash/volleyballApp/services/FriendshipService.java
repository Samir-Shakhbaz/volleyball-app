package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Friendship;
import com.sash.volleyballApp.models.Friendship.Status;
import com.sash.volleyballApp.repositories.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public Friendship sendFriendRequest(Long player1Id, Long player2Id) {
        Friendship friendship = new Friendship(null, player1Id, player2Id, Status.PENDING);
        return friendshipRepository.save(friendship);
    }

    public List<Friendship> getFriends(Long playerId) {
        return friendshipRepository.findByPlayer1IdOrPlayer2IdAndStatus(playerId, playerId, Status.ACCEPTED);
    }

    public void removeFriend(Long friendshipId) {
        friendshipRepository.deleteById(friendshipId);
    }
}

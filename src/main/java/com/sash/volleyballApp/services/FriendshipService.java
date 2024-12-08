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

    public List<Friendship> getFriends(Long userId) {
        return friendshipRepository.findByPlayer1IdOrPlayer2IdAndStatus(userId, userId, Status.ACCEPTED);
    }

    public List<Friendship> getPendingRequests(Long userId) {
        return friendshipRepository.findByPlayer2IdAndStatus(userId, Status.PENDING);
    }

    public Friendship sendFriendRequest(Long player1Id, Long player2Id) {
        Friendship friendship = new Friendship();
        friendship.setPlayer1Id(player1Id);
        friendship.setPlayer2Id(player2Id);
        friendship.setStatus(Friendship.Status.PENDING);
        return friendshipRepository.save(friendship); // Return the saved Friendship object
    }
    public void acceptFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow();
        friendship.setStatus(Status.ACCEPTED);
        friendshipRepository.save(friendship);
    }

    public void blockFriend(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow();
        friendship.setStatus(Status.BLOCKED);
        friendshipRepository.save(friendship);
    }

    public void removeFriend(Long friendshipId) {
        // Check if the friendship exists before deleting
        if (!friendshipRepository.existsById(friendshipId)) {
            throw new IllegalArgumentException("Friendship with id " + friendshipId + " does not exist.");
        }
        friendshipRepository.deleteById(friendshipId); // Deletes the friendship by its ID
    }
}

package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Friendship;
import com.sash.volleyballApp.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipControllerREST {

    @Autowired
    private FriendshipService friendshipService;

    // Send a friend request
    @PostMapping("/send")
    public ResponseEntity<Friendship> sendFriendRequest(
            @RequestParam Long senderId,
            @RequestParam Long receiverId) {
        Friendship friendship = friendshipService.sendFriendRequest(senderId, receiverId);
        return ResponseEntity.ok(friendship);
    }

    // Accept a friend request
    @PostMapping("/accept/{friendshipId}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable Long friendshipId) {
        friendshipService.acceptFriendRequest(friendshipId);
        return ResponseEntity.ok().build();
    }

    // Block a user
    @PostMapping("/block/{friendshipId}")
    public ResponseEntity<Void> blockFriend(@PathVariable Long friendshipId) {
        friendshipService.blockFriend(friendshipId);
        return ResponseEntity.ok().build();
    }

    // Remove a friend
    @DeleteMapping("/{friendshipId}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long friendshipId) {
        friendshipService.removeFriend(friendshipId);
        return ResponseEntity.noContent().build();
    }

    // Get all friends of a user
    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<Friendship>> getFriends(@PathVariable Long userId) {
        List<Friendship> friends = friendshipService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // Get pending friend requests for a user
    @GetMapping("/requests/{userId}")
    public ResponseEntity<List<Friendship>> getPendingRequests(@PathVariable Long userId) {
        List<Friendship> requests = friendshipService.getPendingRequests(userId);
        return ResponseEntity.ok(requests);
    }
}

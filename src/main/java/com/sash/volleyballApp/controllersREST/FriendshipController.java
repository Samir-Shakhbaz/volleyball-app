package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Friendship;
import com.sash.volleyballApp.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping
    public ResponseEntity<Friendship> sendFriendRequest(
            @RequestParam Long player1Id,
            @RequestParam Long player2Id) {
        Friendship friendship = friendshipService.sendFriendRequest(player1Id, player2Id);
        return ResponseEntity.ok(friendship);
    }

    @GetMapping
    public ResponseEntity<List<Friendship>> getFriends(@RequestParam Long playerId) {
        List<Friendship> friends = friendshipService.getFriends(playerId);
        return ResponseEntity.ok(friends);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long id) {
        friendshipService.removeFriend(id);
        return ResponseEntity.noContent().build();
    }
}


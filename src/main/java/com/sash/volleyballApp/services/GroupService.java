package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Group;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(String name, String description, User creator) {
        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        group.setCreatedBy(creator);
        group.getMembers().add(creator); // Add creator as the first member
        return groupRepository.save(group);
    }

    public List<Group> getGroupsCreatedByUser(User user) {
        return groupRepository.findByCreatedBy(user);
    }
}

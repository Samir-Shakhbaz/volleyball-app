package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Group;
import com.sash.volleyballApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByCreatedBy(User user);
}


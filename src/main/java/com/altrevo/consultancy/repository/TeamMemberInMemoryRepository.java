package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.InMemoryTeamMemberStore;
import com.altrevo.consultancy.entity.TeamMember;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TeamMemberInMemoryRepository {
    public TeamMember save(TeamMember member) {
        if (member.getId() == null) {
            member.setId(InMemoryTeamMemberStore.idCounter++);
        }
        InMemoryTeamMemberStore.TEAM_MEMBERS.put(member.getId(), member);
        return member;
    }

    public Optional<TeamMember> findById(Long id) {
        return Optional.ofNullable(InMemoryTeamMemberStore.TEAM_MEMBERS.get(id));
    }

    public List<TeamMember> findAll() {
        return new ArrayList<>(InMemoryTeamMemberStore.TEAM_MEMBERS.values());
    }

    public void deleteById(Long id) {
        InMemoryTeamMemberStore.TEAM_MEMBERS.remove(id);
    }
}


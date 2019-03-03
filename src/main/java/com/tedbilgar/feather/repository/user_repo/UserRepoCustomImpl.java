package com.tedbilgar.feather.repository.user_repo;

import com.tedbilgar.feather.domain.units.UserGroup;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepoCustomImpl implements UserRepoCustom {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<UserGroup> getAllUserGroups() {
        Query query = entityManager.createNativeQuery("select * from user_groups as ug  ", UserGroup.class);
        return query.getResultList();
    }

    @Override
    public List<UserGroup> getByUserId(Long userId) {
        Query query = entityManager.createNativeQuery("select * from user_groups as ug where user_id = :userId", UserGroup.class);
        query.setParameter("userId",userId);
        return query.getResultList();
    }

    @Override
    public List<UserGroup> getByGroupId(Long groupId) {
        Query query = entityManager.createNativeQuery("select * from user_groups as ug where group_id = :groupId", UserGroup.class);
        query.setParameter("groupId",groupId);
        return query.getResultList();    }


    @Override
    public void updateRelation(Long userId, Long groupId, String role) {
        Query query = entityManager.createNativeQuery("update user_groups set role = :role where user_id = :userId and group_id = :groupId",UserGroup.class);
        query.setParameter("groupId",groupId);
        query.setParameter("userId", userId);
        query.setParameter("role",role);
        query.executeUpdate();
    }


    @Override
    public void deleteRelation(Long userId, Long groupId) {
        Query query = entityManager.createNativeQuery("delete from user_groups as ug where group_id = :groupId and user_id = :userId ", UserGroup.class);
        query.setParameter("groupId", groupId);
        query.setParameter("userId",userId);
        query.executeUpdate();
    }
}

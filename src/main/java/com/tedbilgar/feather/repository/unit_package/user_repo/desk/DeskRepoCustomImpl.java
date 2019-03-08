package com.tedbilgar.feather.repository.unit_package.user_repo.desk;

import com.tedbilgar.feather.domain.task_units.Desk;
import com.tedbilgar.feather.domain.task_units_relation.UserDesk;
import com.tedbilgar.feather.domain.units.User;
import com.tedbilgar.feather.domain.units.UserGroup;
import com.tedbilgar.feather.repository.task_unit_controller.desk_repo.DeskRepo;
import com.tedbilgar.feather.repository.unit_package.user_repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeskRepoCustomImpl implements DeskRepoCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserDesk> findAllUserDesks() {
        Query query = entityManager.createNativeQuery("select * from user_desk as ud  ", UserDesk.class);
        return query.getResultList();
    }

    @Override
    public List<UserDesk> findAllUserByDesk(Long deskId) {
        Query query = entityManager.createNativeQuery("select * from user_desk as ud where desk_id = :deskId", UserDesk.class);
        query.setParameter("deskId",deskId);
        return query.getResultList();
    }

    @Override
    public List<UserDesk> findAllDeskByUser(Long userId) {
        Query query = entityManager.createNativeQuery("select * from user_desk as ud where user_id = :userId", UserDesk.class);
        query.setParameter("userId",userId);
        return query.getResultList();
    }


    @Override
    public void deleteRelationUserDesk(Long userId, Long deskId) {
        Query query = entityManager.createNativeQuery("delete from user_desk as ud where desk_id = :deskId and user_id = :userId ", UserDesk.class);
        query.setParameter("deskId", deskId);
        query.setParameter("userId",userId);
        query.executeUpdate();
    }
}

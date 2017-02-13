package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.database.BaseRepository;
import com.eureka.smartrecruit.domain.Membership;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends BaseRepository<Membership> {

    List<Membership> findAllByOrderByNameAsc();
}

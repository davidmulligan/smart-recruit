package com.eureka.smartrecruit.respository;

import com.eureka.smartrecruit.domain.Membership;
import com.eureka.smartrecruit.domain.QMembership;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends QueryableRepository<Membership, QMembership> {
}

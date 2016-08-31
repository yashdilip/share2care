/**
 * 
 */
package cs544.project.share2care.repository;

import org.springframework.stereotype.Repository;

import cs544.project.share2care.domain.MemberCircle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Dilip
 *
 */

@Repository
public interface MemberCircleRepository extends JpaRepository<MemberCircle, Integer> {
	public MemberCircle findByCircleCircleIdAndMemberMemberId(Integer circleId, Integer memberId);
}

/**
 * 
 */
package cs544.project.share2care.repository;

import org.springframework.stereotype.Repository;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.MemberCircle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Dilip
 *
 */

@Repository
public interface MemberCircleRepository extends JpaRepository<MemberCircle, Integer> {
	public MemberCircle findDistinctByCircleCircleIdAndMemberMemberId(Integer circleId, Integer memberId);
	
	public List<Member> findDistinctMemberByCircleCircleId(Integer circleId);
	
	@Query("select distinct m.member from MemberCircle m where m.circle.circleId=:circleId")
	public List<Member> getAllMemberOfACircle(@Param("circleId") Integer circleId);
	
}

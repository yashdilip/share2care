/**
 * 
 */
package cs544.project.share2care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cs544.project.share2care.domain.Member;

/**
 * @author Dilip
 *
 */
public interface MemberRepository extends JpaRepository<Member, Integer>{
	@Query("from Member m where m.firstName = :firstName")
	Member getOneMemberByLoggedInUsername(@Param("firstName") String firstName);
	
	@Query("from Member m where m.memberId <> :memberId")
	List<Member> getAllMembersExceptMe(Integer memberId);
	
/*	@Query("select distinct c.member from MemberCircle c where c.circleId=:circleId")
	List<Member> getAllMembersOfCircleByCircleId(@Param("circleId") Integer circleId);*/
	
	//List<Member> findByCirclesCircleId(Integer circleId);
	
	List<Member> findByCirclesCircleCircleId(Integer circleId);
	
}

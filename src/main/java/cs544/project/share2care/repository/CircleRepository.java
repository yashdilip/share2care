/**
 * 
 */
package cs544.project.share2care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;

/**
 * @author Dilip
 *
 */
@Repository
public interface CircleRepository extends JpaRepository<Circle, Integer>{
	
	@Query("from Circle c where c.circleId= :circleId")
	public Circle findOneCircleByCircleId(@Param("circleId") Integer circleId);	
	
	public List<Circle> findByOwnerMemberId(Integer memberId);
	
	@Query("from Circle c where c.owner.memberId <> :memberId")
	public List<Circle> findAllCirclesNotOwnedByMemberId(@Param("memberId") Integer memberId);
	
	public List<Circle> findByOwnerMemberIdIsNot(Integer memberId);
	
	public List<Circle> findDistinctByMembersMemberMemberIdIsNotAndOwnerMemberIdIsNot(Integer memberId, Integer memberID);
	
	public List<Circle> findDistinctByMembersMemberMemberIdAndOwnerMemberId(Integer memberId, Integer memberID);
	
	/*@Query("select distinct c from Circle c where :member not in c.members")
	public List<Circle> findAllCirclesNotBelongToMe(Member member);*/
	
	//public List<Circle> findByMembersNotContainingAndOwnerIsNot(Member member, Member mem);
	
	@Query("from Circle c join c.owner o where c.circleName like CONCAT('%',:keyword,'%')")
	List<Circle> findAllCirclesByKeyword(@Param("keyword") String keyword);
	
	List<Circle> findDistinctByCircleNameLike(String keyword);
	
}

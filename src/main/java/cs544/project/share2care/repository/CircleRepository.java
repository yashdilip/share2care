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
	
	public List<Circle> findByMembersMemberMemberIdIsNotAndOwnerMemberIdIsNot(Integer memberId, Integer memberID);
	
	public List<Circle> findByMembersMemberMemberIdAndOwnerMemberId(Integer memberId, Integer memberID);
}

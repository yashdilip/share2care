/**
 * 
 */
package cs544.project.share2care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cs544.project.share2care.domain.Member;

/**
 * @author Dilip
 *
 */
public interface MemberRepository extends JpaRepository<Member, Integer>{
	@Query("from User u where u.username = :username")
	Member getOneMemberByLoggedInUsername(@Param("username") String username);
}

/**
 * 
 */
package cs544.project.share2care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cs544.project.share2care.domain.UserRole;

/**
 * @author Dilip
 *
 */
@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
	@Query("select a.role from UserRole a, User b where b.userName=?1 and a.userid=b.userId")
	public List<String> findRoleByUserName(String username);
}

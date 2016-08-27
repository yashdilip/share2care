/**
 * 
 */
package cs544.project.share2care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs544.project.share2care.domain.User;

/**
 * @author Dilip
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserName(String username);
}

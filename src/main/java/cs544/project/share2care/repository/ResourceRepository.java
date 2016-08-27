/**
 * 
 */
package cs544.project.share2care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs544.project.share2care.domain.Resource;

/**
 * @author Dilip
 *
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
	

}

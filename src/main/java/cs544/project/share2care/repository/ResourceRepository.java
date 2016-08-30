/**
 * 
 */
package cs544.project.share2care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cs544.project.share2care.domain.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
	@Query("from Resource r where r.status=:status")
	List<Resource> findAllByStatus(@Param("status") String status);

	@Query("from Resource r where r.importance=:importance")
	List<Resource> findAllByImportance(@Param("importance") String importance);

	@Query("from Resource r where r.description like CONCAT('%',:description,'%') ")
	List<Resource> findAllByDescriptionLike(@Param("description") String description);

	List<Resource> findByNameLike(String name);
}

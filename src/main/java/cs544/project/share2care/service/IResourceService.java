/**
 * 
 */
package cs544.project.share2care.service;

import java.util.List;

import cs544.project.share2care.domain.Resource;

/**
 * @author Chao Ping
 *
 */
public interface IResourceService {
	public List<Resource> findAllResources();
	
	public void saveOrUpdateResource(Resource resource);
	
	public Resource getResourceById(int resourceId);

	public void deleteResource(int resourceId) ;
		
	public List<Resource> findAllByStatus(String status);

	public List<Resource> findAllByImportance(String importance);

	public List<Resource> findAllByDescriptionLike(String description);

	public List<Resource> findByNameLike(String name);
	
	public List<Resource> findAllByEventId(int eventId);
}



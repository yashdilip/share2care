/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.Resource;
import cs544.project.share2care.repository.ResourceRepository;
import cs544.project.share2care.service.IResourceService;

/**
 * @author Chao Ping
 *
 */
@Service
public class ResourceServiceImpl implements IResourceService {
	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public List<Resource> findAllByStatus(String status) {
		// TODO Auto-generated method stub
		return resourceRepository.findAllByStatus(status);
	}

	@Override
	public List<Resource> findAllByImportance(String importance) {
		// TODO Auto-generated method stub
		return resourceRepository.findAllByImportance(importance);
	}

	@Override
	public List<Resource> findAllByDescriptionLike(String description) {
		// TODO Auto-generated method stub
		return resourceRepository.findAllByDescriptionLike(description);
	}

	@Override
	public List<Resource> findByNameLike(String name) {
		// TODO Auto-generated method stub
		return resourceRepository.findByNameLike(name);
	}

	@Override
	public List<Resource> findAllResources() {
		// TODO Auto-generated method stub
		return resourceRepository.findAll();
	}

	@Override
	public void saveOrUpdateResource(Resource resource) {
		// TODO Auto-generated method stub
		resourceRepository.save(resource);
	}

	@Override
	public Resource getResourceById(int resourceId) {
		// TODO Auto-generated method stub
		return resourceRepository.findOne(Integer.valueOf(resourceId));
	}

	@Override
	public void deleteResource(int resourceId) {
		// TODO Auto-generated method stub
		resourceRepository.delete(Integer.valueOf(resourceId));
	}

	@Override
	public List<Resource> findAllByEventId(int eventId) {
		// TODO Auto-generated method stub
		return resourceRepository.findAllByEventId(eventId);
	}

}

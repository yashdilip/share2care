/**
 * 
 */
package cs544.project.share2care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.User;
import cs544.project.share2care.repository.UserRepository;
import cs544.project.share2care.repository.UserRolesRepository;

/**
 * @author Dilip
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public void saveNewUser(User user) {
		userRepository.save(user);
	}

}

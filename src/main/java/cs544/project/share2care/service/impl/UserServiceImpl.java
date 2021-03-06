/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.User;
import cs544.project.share2care.repository.UserRepository;
import cs544.project.share2care.service.IUserService;

/**
 * @author Dilip
 *
 */
@Component
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	/* (non-Javadoc)
	 * @see cs544.project.share2care.service.IUserService#saveNewUser(cs544.project.share2care.domain.User)
	 */
	@Override
	public void saveNewUser(User user) {
		userRepository.save(user);
	}
	/* (non-Javadoc)
	 * @see cs544.project.share2care.service.IUserService#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	/* (non-Javadoc)
	 * @see cs544.project.share2care.service.IUserService#verifyUserByEmail(java.lang.Long)
	 */
	@Override
	public String verifyUserByEmail(Long userId) {
		User user = userRepository.findOne(userId);
		user.setEnabled(1);
		userRepository.save(user);
		return user.getUsername();
	}
	/* (non-Javadoc)
	 * @see cs544.project.share2care.service.IUserService#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}

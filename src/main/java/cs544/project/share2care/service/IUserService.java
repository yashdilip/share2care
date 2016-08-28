/**
 * 
 */
package cs544.project.share2care.service;

import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.User;

/**
 * @author Dilip
 *
 */
@Service
public interface IUserService {
	void saveNewUser(User user);
	User getUserByUsername(String username);
	String verifyUserByEmail(Long userId);
}

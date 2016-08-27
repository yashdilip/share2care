/**
 * 
 */
package cs544.project.share2care.service;

import org.springframework.security.core.userdetails.UserDetails;
import cs544.project.share2care.domain.User;

/**
 * @author Dilip
 *
 */
public interface IUserService {
	void saveNewUser(User user);
}

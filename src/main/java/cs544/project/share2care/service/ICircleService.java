/**
 * 
 */
package cs544.project.share2care.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;

/**
 * @author Dilip
 *
 */
@Service
public interface ICircleService {
	void addMember(Member member);
	List<Circle> findAllCircles(Integer memberId);
	Circle findOneCircleByCircleId(Integer circleId);
	void saveNewCircle(Circle circle);
	
}

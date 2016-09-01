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
	List<Circle> findAllCircles(Integer memberId);
	Circle findOneCircleByCircleId(Integer circleId);
	void saveNewCircle(Circle circle, Member member);
	String joinCircle(Integer circleId, Integer memberId);
	List<Circle> findAllCirclesOfApp();
	List<Circle> findAllCirclesOfAppNotOwnedMyMember(Integer memberId);
	void saveUpdateCircle(Circle circle, Member member);
	List<Circle> searchCirclesByKeywords(String keyword);
}

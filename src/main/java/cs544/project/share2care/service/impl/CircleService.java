/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.repository.CircleRepository;
import cs544.project.share2care.service.ICircleService;

/**
 * @author Dilip
 *
 */
@Component
public class CircleService implements ICircleService{
	@Autowired
	private CircleRepository circleRepository;
	
	@Override
	public void addMember(Member member) {
		//circleRepository.save(member);		
	}

	@Override
	public List<Circle> findAllCircles(Integer memberId) {
		return circleRepository.findByOwnerMemberId(memberId);
	}

	@Override
	public Circle findOneCircleByCircleId(Integer circleId) {
		return circleRepository.findOneCircleByCircleId(circleId);
	}

	@Override
	public void saveNewCircle(Circle circle) {
		circleRepository.save(circle);
		
	}

}

/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.MemberCircle;
import cs544.project.share2care.repository.CircleRepository;
import cs544.project.share2care.repository.MemberRepository;
import cs544.project.share2care.service.ICircleService;
import cs544.project.share2care.service.IMemberCircleService;

/**
 * @author Dilip
 *
 */
@Component
public class CircleService implements ICircleService{
	@Autowired
	private CircleRepository circleRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private IMemberCircleService memberCircleService;
	
	@Override
	public List<Circle> findAllCircles(Integer memberId) {
		//return circleRepository.findByOwnerMemberId(memberId);
		return circleRepository.findDistinctByMembersMemberMemberIdAndOwnerMemberId(memberId, memberId);
	}

	@Override
	public Circle findOneCircleByCircleId(Integer circleId) {
		return circleRepository.findOneCircleByCircleId(circleId);
	}

	@Override
	public void saveNewCircle(Circle circle, Member member) {
		circle.setOwner(member);
		MemberCircle memberCircle = new MemberCircle();
		memberCircle.setMember(member);
		memberCircle.setCircle(circle);
		circleRepository.save(circle);
		memberCircleService.saveMemberCircle(memberCircle);
		
	}

	@Override
	public String joinCircle(Integer circleId, Integer memberId) {
		Member member = memberRepository.findOne(memberId);
		Circle circle = circleRepository.findOneCircleByCircleId(circleId);
		
		MemberCircle memberCircle = new MemberCircle();
		memberCircle.setCircle(circle);
		memberCircle.setMember(member);
		if(memberCircle.getMember().getMemberId()!=memberId || memberCircle.getCircle().getCircleId()!=circleId){
			memberCircleService.saveMemberCircle(memberCircle);
		}else{
			return "duplicate entry";
		}
		return "circle joined successfully";
	}

	@Override
	public List<Circle> findAllCirclesOfApp() {
		return circleRepository.findAll();
	}

	@Override
	public List<Circle> findAllCirclesOfAppNotOwnedMyMember(Integer memberId) {
		System.out.println(memberId);
		return circleRepository.findDistinctByMembersMemberMemberIdIsNotAndOwnerMemberIdIsNot(memberId, memberId);
	}

	@Override
	public void saveUpdateCircle(Circle circle, Member member) {
		Circle c = circleRepository.findOne(circle.getCircleId());
		c.setCircleName(circle.getCircleName());
		circleRepository.save(circle);
		
	}

}

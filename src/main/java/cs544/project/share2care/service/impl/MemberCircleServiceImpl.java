/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.MemberCircle;
import cs544.project.share2care.repository.MemberCircleRepository;
import cs544.project.share2care.service.IMemberCircleService;

/**
 * @author Dilip
 *
 */
@Component
public class MemberCircleServiceImpl implements IMemberCircleService{

	@Autowired
	private MemberCircleRepository memberCircleRepository;
	
	@Override
	public void saveMemberCircle(MemberCircle memberCircle) {
		memberCircleRepository.save(memberCircle);
	}

	@Override
	public MemberCircle findByCircleIdandMemberId(Integer circleId, Integer memberId) {
		return memberCircleRepository.findDistinctByCircleCircleIdAndMemberMemberId(circleId, memberId);
	}

	@Override
	public List<Member> findMembersOfACircle(Integer circleId) {
		
		return null;
	}

	@Override
	public List<MemberCircle> findAllMemberCircle() {
		
		return memberCircleRepository.findAll();
	}


	@Override
	public List<Member> getAllMemberOfACircle(Integer circleId) {
		return memberCircleRepository.getAllMemberOfACircle(circleId);
	}

}

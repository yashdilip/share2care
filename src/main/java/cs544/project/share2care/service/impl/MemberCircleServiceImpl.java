/**
 * 
 */
package cs544.project.share2care.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}

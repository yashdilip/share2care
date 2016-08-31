/**
 * 
 */
package cs544.project.share2care.service;

import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.MemberCircle;

/**
 * @author Dilip
 *
 */
@Service
public interface IMemberCircleService {
	void saveMemberCircle(MemberCircle memberCircle);
	MemberCircle findByCircleIdandMemberId(Integer circleId, Integer memberId);
}

/**
 * 
 */
package cs544.project.share2care.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.MemberCircle;

/**
 * @author Dilip
 *
 */
@Service
public interface IMemberCircleService {
	void saveMemberCircle(MemberCircle memberCircle);
	MemberCircle findByCircleIdandMemberId(Integer circleId, Integer memberId);
	List<Member> findMembersOfACircle(Integer circleId);
	
	List<MemberCircle> findAllMemberCircle();
	
	List<Member> getAllMemberOfACircle(Integer circleId);
}

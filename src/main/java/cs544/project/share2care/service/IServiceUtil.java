/**
 * 
 */
package cs544.project.share2care.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.MemberCircle;

/**
 * @author Dilip
 *
 */
@Service
public interface IServiceUtil {
	public List<MemberCircle> getAllMemberCircleList();
	public List<Circle> getAllCircleNotContainingMe(Integer memberId);
	public List<Circle> getAllCircleBelongsToMe(Integer memberId);
	public List<Member> getAllNewMembers(Integer memberId);
	public List<Member> getAllFriends(Integer circleId);
	
}

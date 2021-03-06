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
public interface IMemberService {
	Member getLoggedInMemeberByMemberName(String name);
	void saveMember(Member member);
	List<Member> findAllMembers();
	List<Member> findAllMembersNotMe(Integer memberId);
	List<Member> findMembersOfCircleByCircleId(Integer circleId);
	Member getMemberByMemberId(Integer memberId);
	String addMemberIntoCircle(Member member, Circle circle);
	List<Member> getAllMemberOfACircle(Integer circleId);
	List<Member> allFriends(Integer memberId);
	List<Member> searchMembersByKeyword(String keyword);
}

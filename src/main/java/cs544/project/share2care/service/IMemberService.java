/**
 * 
 */
package cs544.project.share2care.service;

import java.util.List;

import cs544.project.share2care.domain.Member;

/**
 * @author Dilip
 *
 */
public interface IMemberService {
	Member getLoggedInMemeberByMemberName(String name);
	void saveMember(Member member);
	List<Member> findAllMembers();
}

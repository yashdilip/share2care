/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.User;
import cs544.project.share2care.repository.MemberRepository;
import cs544.project.share2care.repository.UserRepository;
import cs544.project.share2care.service.IMemberService;

/**
 * @author Dilip
 *
 */
public class MemberServiceImpl implements IMemberService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Override
	public Member getLoggedInMemeberByMemberName(String name) {
		User user = userRepository.findByUsername(name);
		Member member = new Member();
		member.setFirstName(user.getUsername());
		member.setLastName("");
		member.setEmail(user.getEmail());
		return member;
	}

	@Override
	public void saveMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}

}

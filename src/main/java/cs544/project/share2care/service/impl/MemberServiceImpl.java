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
import cs544.project.share2care.domain.User;
import cs544.project.share2care.repository.MemberRepository;
import cs544.project.share2care.repository.UserRepository;
import cs544.project.share2care.service.IMemberService;

/**
 * @author Dilip
 *
 */
@Component
public class MemberServiceImpl implements IMemberService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Override
	public Member getLoggedInMemeberByMemberName(String name) {
		
		Member member = memberRepository.getOneMemberByLoggedInUsername(name);
		if(member==null){
			User user = userRepository.findByUsername(name);
			Member mem = new Member();
			mem.setFirstName(user.getUsername());
			mem.setLastName("");
			mem.setEmail(user.getEmail());
			return mem;
		}
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


	@Override
	public List<Member> findAllMembersNotMe(Integer memberId) {
		return memberRepository.getAllMembersExceptMe(memberId);
	}

	@Override
	public List<Member> findMembersOfCircleByCircleId(Integer circleId) {
		//return memberRepository.findByCirclesCircleId(circleId);
		return memberRepository.findByCirclesCircleCircleId(circleId);
	}

	@Override
	public Member getMemberByMemberId(Integer memberId) {
		return memberRepository.findOne(memberId);
	}

	@Override
	public void addMemberIntoCircle(Member member, Circle circle) {
		
/*		member.getCircles().add(circle);
		circle.getMembers().add(member);
		this.saveMember(member);		*/
	}

}

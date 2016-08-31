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
import cs544.project.share2care.domain.User;
import cs544.project.share2care.repository.MemberRepository;
import cs544.project.share2care.repository.UserRepository;
import cs544.project.share2care.service.ICircleService;
import cs544.project.share2care.service.IMemberCircleService;
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
	@Autowired
	private ICircleService circleService;
	@Autowired
	private IMemberCircleService memberCircleService;
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
		return memberRepository.findByMemberIdIsNot(memberId);
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
	public String addMemberIntoCircle(Member member, Circle circle) {	
			MemberCircle memberCircle = new MemberCircle();
			memberCircle.setCircle(circle);
			memberCircle.setMember(member);
			MemberCircle mclist = memberCircleService.findByCircleIdandMemberId(circle.getCircleId(), member.getMemberId());
			
			if(mclist.getCircle().getCircleId()!=circle.getCircleId() && mclist.getMember().getMemberId()!=member.getMemberId()){
				memberCircleService.saveMemberCircle(memberCircle);
				return "saved";
			}
			return "not saved";
	}

}

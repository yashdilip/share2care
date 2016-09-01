/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.MemberCircle;
import cs544.project.share2care.service.ICircleService;
import cs544.project.share2care.service.IMemberCircleService;
import cs544.project.share2care.service.IMemberService;
import cs544.project.share2care.service.IServiceUtil;

/**
 * @author Dilip
 *
 */
@Component
public class ServiceUtilImpl implements IServiceUtil {

	@Autowired
	private ICircleService circleService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMemberCircleService memberCircleService;

	@Override
	public List<MemberCircle> getAllMemberCircleList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Circle> getAllCircleNotContainingMe(Integer memberId) {
		List<MemberCircle> memberCircleList = memberCircleService.findAllMemberCircle();
		List<Circle> circlelist_new = new ArrayList<>();
		Set<Circle> setCircle = new TreeSet<Circle>();
		if (memberCircleList != null) {
			for (MemberCircle mc : memberCircleList) {
				if ((mc.getCircle().getOwner().getMemberId() != memberId)
						&& (mc.getMember().getMemberId() != memberId)) {
					setCircle.add(mc.getCircle());
				}
			}

			for (Circle c : setCircle) {
				circlelist_new.add(c);
			}
			return circlelist_new;
		}
		return null;
	}

	@Override
	public List<Circle> getAllCircleBelongsToMe(Integer memberId) {

		List<MemberCircle> memberCircleList = memberCircleService.findAllMemberCircle();

		List<Circle> circles_new = new ArrayList<>();
		if (memberCircleList != null) {
			for (MemberCircle mc : memberCircleList) {
				if (mc.getMember().getMemberId() == memberId || mc.getCircle().getOwner().getMemberId() == memberId) {
					circles_new.add(mc.getCircle());
				}
			}
			return circles_new;
		}

		return null;
	}

	@Override
	public List<Member> getAllNewMembers(Integer memberId) {

		List<Member> members_new = new ArrayList<>();

		List<MemberCircle> mclist = memberCircleService.findAllMemberCircle();

		for (MemberCircle mc : mclist) {

		}

		return null;
	}

	@Override
	public List<Member> getAllFriends(Integer memberId) {
		List<MemberCircle> memberCircleList = memberCircleService.findAllMemberCircle();
		List<Circle> circlelist = new ArrayList<>();
		List<Member> members_new = new ArrayList<Member>();
		Member member = memberService.getMemberByMemberId(memberId);
		if (memberCircleList != null) {
			for (MemberCircle mc : memberCircleList) {
				if ((mc.getCircle().getOwner().getMemberId() == memberId)
						|| mc.getCircle().getMembers().contains(member)) {
					circlelist.add(mc.getCircle());
				}
			}
			for (Circle c : circlelist) {
				List<Member> mlist = memberService.getAllMemberOfACircle(c.getCircleId());
				members_new.addAll(mlist);
			}

			return members_new;
		}
		return null;

	}

}

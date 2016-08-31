/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.ArrayList;
import java.util.List;

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
		if (memberCircleList != null) {
			for (MemberCircle mc : memberCircleList) {
				if (mc.getMember().getMemberId() != memberId
						&& mc.getCircle().getOwner().getMemberId() != memberId) {
					circlelist_new.add(mc.getCircle());
				}
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
				if(mc.getMember().getMemberId()==memberId || mc.getCircle().getOwner().getMemberId()==memberId){
					circles_new.add(mc.getCircle());
				}
			}
			return circles_new;
		}

		return null;
	}

}

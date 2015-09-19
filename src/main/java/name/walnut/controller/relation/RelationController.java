package name.walnut.controller.relation;

import name.walnut.controller.relation.vo.RelationCount;
import name.walnut.relation.dto.FriendAndPhotoCount;
import name.walnut.relation.dto.MobileRelation;
import name.walnut.relation.service.InviteService;
import name.walnut.relation.service.RelationService;
import name.walnut.web.vo.Normal;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relation")
public class RelationController {
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public List<MobileRelation> getRelations() {

		return relationService.getAllRelation();
	}
	
	@RequestMapping(value="count", method=RequestMethod.GET)
	public RelationCount getRelationCount() {
		
		return new RelationCount(relationService.getUnreadCount(), 
								 relationService.getFriendsCount());
	}
	
	/**
	 * 邀请
	 * @param param
	 * @return
	 */
	@RequestMapping(value="invite", method=RequestMethod.POST)
	public Normal invite(@RequestBody Map<String, Long> param) {

		inviteService.invite(param.get("id"));
		return Normal.INSTANCE;
	}
	
	/**
	 * 同意邀请
	 * @param param
	 * @return
	 */
	@RequestMapping(value="agreeInvite", method=RequestMethod.POST)
	public Normal agreeInvite(@RequestBody Map<String, Long> param) {

		inviteService.agreeInvite(param.get("id"));
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="friends", method=RequestMethod.GET)
	public List<FriendAndPhotoCount> getFriends() {
		
		return relationService.getAllFindWhithPhotoCount();
	}
	
	
	@Autowired
	private RelationService relationService;

	@Autowired
	private InviteService inviteService;
}

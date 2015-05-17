package name.walnut.controller.relation;

import java.util.List;
import java.util.Map;

import name.walnut.controller.relation.vo.RelationCount;
import name.walnut.relation.dto.FriendAndPhotoCount;
import name.walnut.relation.dto.MobileRelation;
import name.walnut.relation.service.RelationService;
import name.walnut.web.vo.Normal;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value="invit", method=RequestMethod.POST)
	public Normal invit(@RequestBody Map<String, Number> param) {
		
		relationService.invit(NumberUtils.toLong(param.get("id").toString()));
		return Normal.INSTANCE;
	}
	
	/**
	 * 同意邀请
	 * @param param
	 * @return
	 */
	@RequestMapping(value="agreeInvit", method=RequestMethod.POST)
	public Normal agreeInvit(@RequestBody Map<String, Number> param) {
		
		relationService.agreeInvit(NumberUtils.toLong(param.get("id").toString()));
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="friends", method=RequestMethod.GET)
	public List<FriendAndPhotoCount> getFriends() {
		
		return relationService.getAllFindWhithPhotoCount();
	}
	
	
	@Autowired
	private RelationService relationService;
}

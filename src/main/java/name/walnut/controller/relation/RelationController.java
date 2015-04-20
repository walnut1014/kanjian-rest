package name.walnut.controller.relation;

import java.util.ArrayList;
import java.util.List;

import name.walnut.controller.relation.vo.RelationCount;
import name.walnut.relation.RelationStatus;
import name.walnut.relation.dto.FriendAndPhotoCount;
import name.walnut.relation.dto.MobileRelation;
import name.walnut.relation.entity.Relation;
import name.walnut.web.vo.Normal;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
public class RelationController {
	
	@RequestMapping(value="count", method=RequestMethod.GET)
	public RelationCount getRelationCount() {
		return new RelationCount();
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public List<MobileRelation> getRelations() {
		List<MobileRelation> list = new ArrayList<>();
		MobileRelation relation = new MobileRelation();
		relation.setMobilephone("13800000000");
		relation.setRelationStatus(RelationStatus.waitVerify);
		list.add(relation);
		relation = new MobileRelation();
		relation.setMobilephone("13800000001");
		relation.setRelationStatus(RelationStatus.sendRequest);
		list.add(relation);
		relation = new MobileRelation();
		relation.setMobilephone("13800000002");
		relation.setRelationStatus(RelationStatus.accetp);
		list.add(relation);
		
		return list;
	}
	
	@RequestMapping(value="invit", method=RequestMethod.POST)
	public Normal invit(@RequestBody Relation relation) {
		
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="relation/friends", method=RequestMethod.GET)
	public List<FriendAndPhotoCount> getFriends() {
		List<FriendAndPhotoCount> list = new ArrayList<>();
		FriendAndPhotoCount friendAndPhotoCount = new FriendAndPhotoCount();
		friendAndPhotoCount.setUserId(1L);
		friendAndPhotoCount.setPhotoCount(5);
		friendAndPhotoCount.setMobilephone("1300000000");
		list.add(friendAndPhotoCount);
		
		return list;
	}
	
}

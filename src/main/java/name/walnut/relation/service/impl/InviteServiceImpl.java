package name.walnut.relation.service.impl;

import name.walnut.common.BusinessException;
import name.walnut.common.entity.Friend;
import name.walnut.common.entity.InviteRecord;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.relation.dao.FriendDao;
import name.walnut.relation.dao.InviteDao;
import name.walnut.relation.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by walnut on 15/9/16.
 */
@Service
public class InviteServiceImpl implements InviteService {

    @Override
    public void invite(long id) {

        if(OnlineUtils.getOnlineUserId() == id)
            throw new BusinessException("不能邀请自己为好友");

        inviteDao.save(new InviteRecord(OnlineUtils.getOnlineUserId(), id));
    }

    @Override
    public void agreeInvite(long id) {

        final long targetId = OnlineUtils.getOnlineUserId();

        InviteRecord record = inviteDao.getInviteRecord(id, targetId);
        if(record == null)
            throw new BusinessException("没有找到邀请纪录");
        record.setAgree(true);

        friendDao.save(new Friend(id, targetId));
        friendDao.save(new Friend(targetId, id));
    }

    @Autowired
    private InviteDao inviteDao;

    @Autowired
    private FriendDao friendDao;
}

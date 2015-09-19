package name.walnut.relation.dao;

import name.walnut.common.BaseDao;
import name.walnut.common.entity.InviteRecord;

/**
 * Created by walnut on 15/9/16.
 */
public interface InviteDao extends BaseDao<InviteRecord> {
    /**
     * 通过邀请人和被邀请人获得邀请纪录
     * @param inviteUserId 邀请人id
     * @param targetUserId 被邀请人id
     * @return
     */
    InviteRecord getInviteRecord(long inviteUserId, long targetUserId);
}


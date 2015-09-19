package name.walnut.relation.service;

/**
 * 邀请用户业务
 */
public interface InviteService {

    /**
     * 邀请用户
     *
     * @param id 被邀请人的id
     */
    void invite(long id);

    /**
     * 邀请人ID
     * @param id
     */
    void agreeInvite(long id);
}

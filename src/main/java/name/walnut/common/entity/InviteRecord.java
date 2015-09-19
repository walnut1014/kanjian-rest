package name.walnut.common.entity;

import javax.persistence.*;

/**
 * 邀请纪录
 *
 * @author walnut
 */
@Entity
public class InviteRecord extends BaseEntity {

    /** 邀请人*/
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="invite_id",referencedColumnName="id", foreignKey = @ForeignKey(name="FK_USER_INVITE_ID"))
    private User inviteUser;
    /** 被邀请人*/
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="target_id",referencedColumnName="id", foreignKey = @ForeignKey(name="FK_USER_TARGET_ID"))
    private User targetUser;
    /** 是否同意*/
    private boolean agree;

    public InviteRecord() {}

    public InviteRecord(long inviteUserId, long targetUserId) {
        this.inviteUser = new User(inviteUserId);
        this.targetUser = new User(targetUserId);
    }

    public User getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(User inviteUser) {
        this.inviteUser = inviteUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }
}

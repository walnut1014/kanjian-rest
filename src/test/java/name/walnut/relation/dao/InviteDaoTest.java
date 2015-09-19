package name.walnut.relation.dao;

import name.walnut.auth.dao.UserDao;
import name.walnut.common.entity.InviteRecord;
import name.walnut.common.entity.User;
import name.walnut.test.TestSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by walnut on 15/9/17.
 */
public class InviteDaoTest extends TestSupport {

    @Test
    public void testSave() {

        long l =userDao.save(new User());
        inviteDao.save(new InviteRecord(l,l));
    }

    @Autowired
    private InviteDao inviteDao;

    @Autowired
    private UserDao userDao;
}

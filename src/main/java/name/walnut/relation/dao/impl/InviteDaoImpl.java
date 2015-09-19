package name.walnut.relation.dao.impl;

import name.walnut.common.HibernateGenerationDao;
import name.walnut.common.entity.InviteRecord;
import name.walnut.relation.dao.InviteDao;
import org.springframework.stereotype.Repository;

/**
 * Created by walnut on 15/9/16.
 */
@Repository
public class InviteDaoImpl extends HibernateGenerationDao<InviteRecord> implements InviteDao {
}

package name.walnut.auth.dao;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.common.BaseDao;
import name.walnut.mapper.auth.AuthAccountMapper;

public interface AuthAccountDao extends BaseDao<AuthAccountMapper> {
	
	long insert(AuthAccount authAccount);
	
}

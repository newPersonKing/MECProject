package mec.com.mecprojection.master;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import mec.com.mecprojection.model.User;
import mec.com.mecprojection.model.CheckData;
import mec.com.mecprojection.model.UserMessage;

import mec.com.mecprojection.master.UserDao;
import mec.com.mecprojection.master.CheckDataDao;
import mec.com.mecprojection.master.UserMessageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig checkDataDaoConfig;
    private final DaoConfig userMessageDaoConfig;

    private final UserDao userDao;
    private final CheckDataDao checkDataDao;
    private final UserMessageDao userMessageDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        checkDataDaoConfig = daoConfigMap.get(CheckDataDao.class).clone();
        checkDataDaoConfig.initIdentityScope(type);

        userMessageDaoConfig = daoConfigMap.get(UserMessageDao.class).clone();
        userMessageDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        checkDataDao = new CheckDataDao(checkDataDaoConfig, this);
        userMessageDao = new UserMessageDao(userMessageDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(CheckData.class, checkDataDao);
        registerDao(UserMessage.class, userMessageDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        checkDataDaoConfig.clearIdentityScope();
        userMessageDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CheckDataDao getCheckDataDao() {
        return checkDataDao;
    }

    public UserMessageDao getUserMessageDao() {
        return userMessageDao;
    }

}
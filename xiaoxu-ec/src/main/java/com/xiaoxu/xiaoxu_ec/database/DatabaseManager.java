package com.xiaoxu.xiaoxu_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 * DatabaseManager
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserBeanDao mDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {

        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "xiaoxu_for_working.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserBeanDao();
    }

    public final UserBeanDao getDao() {
        return mDao;
    }
}

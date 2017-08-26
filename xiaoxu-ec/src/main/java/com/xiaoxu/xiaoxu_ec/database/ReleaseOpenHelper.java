package com.xiaoxu.xiaoxu_ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}

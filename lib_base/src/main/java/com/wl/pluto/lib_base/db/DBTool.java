package com.wl.pluto.lib_base.db;

import android.content.Context;

import androidx.room.Room;

import com.tencent.wcdb.room.db.WCDBOpenHelperFactory;
import com.wl.pluto.lib_base.base.BaseApplication;
import com.wl.pluto.lib_base.db.entity.AppDatabase;
import com.wl.pluto.lib_base.db.entity.UserDao;

/**
 * @author szy
 * Created on 2019-09-18
 * @function
 */
public class DBTool {

    private AppDatabase mAppDB;

    private static final DBTool ourInstance = new DBTool(BaseApplication.getContext());

    public static DBTool getInstance() {
        return ourInstance;
    }

    private DBTool(Context context) {

        mAppDB = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app-db")
                .allowMainThreadQueries()
                .openHelperFactory(new WCDBOpenHelperFactory()
                        .passphrase("passphrase".getBytes())
                        //.cipherSpec(sCipherSpec)
                        .writeAheadLoggingEnabled(true)
                        .asyncCheckpointEnabled(true)
                )
                .build();

    }

    public UserDao getUserDao() {
        return mAppDB.userDao();
    }
}

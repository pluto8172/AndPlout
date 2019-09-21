package com.wl.pluto.module_component;

import android.app.Application;
import android.content.Context;
import android.util.Log;




/**
 * Created by goldze on 2018/6/21 0021.
 */

public class ComponentModuleInit  {

    private static final String TAG = "SettingModuleInit";
    private static Context sContext;



    public boolean onInitAhead(Application application) {
        Log.e(TAG, "ComponentModuleInit -- onInitAhead");
        return false;
    }


    public boolean onInitLow(Application application) {
        Log.e(TAG, "ComponentModuleInit -- onInitLow");
        //initMatrix(application);
        return false;
    }


    public static Context getContext() {
        return sContext;
    }
}

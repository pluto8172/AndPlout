package com.wl.pluto.module_component.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wl.pluto.lib_kit.rxtool.RxActivityTool;


/**
 * @author vondear
 */
public class BaseActivity extends AppCompatActivity {

    public BaseActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        RxActivityTool.addActivity(this);
//        DragAndDropPermissionsCompat
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxActivityTool.finishActivity(this);
    }
}

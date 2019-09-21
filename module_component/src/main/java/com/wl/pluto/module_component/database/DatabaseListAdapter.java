package com.wl.pluto.module_component.database;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wl.pluto.lib_base.db.entity.User;
import com.wl.pluto.module_component.R;

import java.util.List;

/**
 * @author szy
 * Created on 2019-09-18
 * @function
 */
public class DatabaseListAdapter extends BaseQuickAdapter<User, BaseViewHolder> {


    public DatabaseListAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {

        TextView name = helper.getView(R.id.tv_name);
        name.setText(item.firstName);
    }
}

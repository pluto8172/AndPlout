package com.wl.pluto.lib_kit.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wl.pluto.lib_kit.R;

import java.util.List;

public class TestListAdapter extends BaseAdapter {

    private List<TestBean> mListData;

    public TestListAdapter(List<TestBean> dataList) {
        mListData = dataList;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public TestBean getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.utils_layout_test_list_item, null);
            holder.name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TestBean data = mListData.get(position);
        holder.name.setText(data.name);
        return convertView;
    }


    static class ViewHolder {
        public TextView name;
    }
}

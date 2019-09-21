/*
 * Tencent is pleased to support the open source community by making
 * WCDB available.
 *
 * Copyright (C) 2017 THL A29 Limited, a Tencent company.
 * All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *       https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wl.pluto.module_component.database;


import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;


import com.wl.pluto.lib_base.db.DBTool;
import com.wl.pluto.lib_base.db.entity.User;
import com.wl.pluto.lib_base.db.entity.UserDao;
import com.wl.pluto.module_component.R;
import com.wl.pluto.module_component.base.BaseActivity;

import java.util.List;

public class DataBaseActivityTest extends BaseActivity {


    private UserDao mUsers;

    private RecyclerView mRecyclerView;

    private DatabaseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        mRecyclerView = findViewById(R.id.rv_recycler_view);
        mUsers = DBTool.getInstance().getUserDao();

        List<User> userList = mUsers.getAll();
        mAdapter = new DatabaseListAdapter(R.layout.item_database_layout, userList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUsers = null;
    }
}

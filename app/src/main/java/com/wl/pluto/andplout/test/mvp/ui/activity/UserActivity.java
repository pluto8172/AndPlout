/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wl.pluto.andplout.test.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.paginate.Paginate;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wl.pluto.andplout.R;
import com.wl.pluto.andplout.test.di.component.DaggerUserComponent;
import com.wl.pluto.andplout.test.mvp.contract.UserContract;
import com.wl.pluto.andplout.test.mvp.presenter.UserPresenter;
import com.wl.pluto.lib_base.base.BaseActivity;
import com.wl.pluto.lib_base.base.DefaultAdapter;
import com.wl.pluto.lib_base.di.component.AppComponent;
import com.wl.pluto.lib_base.utils.ArmsUtils;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

import static com.wl.pluto.lib_kit.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * 展示 View 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.2">View wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 10:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class UserActivity extends BaseActivity<UserPresenter> implements UserContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swf_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    RxPermissions mRxPermissions;

    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    @Inject
    RecyclerView.Adapter mAdapter;

    private Paginate mPaginate;
    private boolean isLoadingMore;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUserComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
        mRecyclerView.setAdapter(mAdapter);
        initPaginate();
    }


    @Override
    public void onRefresh() {
        mPresenter.requestUsers(true);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        ArmsUtils.configRecyclerView(mRecyclerView, mLayoutManager);
    }


    @Override
    public void showLoading() {
        Timber.tag(TAG).w("showLoading");
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        Timber.tag(TAG).w("hideLoading");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    /**
     * 开始加载更多
     */
    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    /**
     * 结束加载更多
     */
    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestUsers(false);
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    protected void onDestroy() {
        DefaultAdapter.releaseAllHolder(mRecyclerView);//super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
        this.mRxPermissions = null;
        this.mPaginate = null;
    }
}

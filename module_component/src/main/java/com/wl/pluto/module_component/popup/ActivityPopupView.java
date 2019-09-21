package com.wl.pluto.module_component.popup;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.wl.pluto.lib_kit.rxtool.model.ActionItem;
import com.wl.pluto.lib_kit.rxtool.view.RxToast;
import com.wl.pluto.module_component.R;
import com.wl.pluto.module_component.view.RxTitle;
import com.wl.pluto.module_component.view.popupwindows.RxPopupImply;
import com.wl.pluto.module_component.view.popupwindows.RxPopupSingleView;
import com.wl.pluto.module_component.view.popupwindows.tools.RxPopupView;
import com.wl.pluto.module_component.view.popupwindows.tools.RxPopupViewManager;

/**
 * @author vondear
 */
public class ActivityPopupView extends AppCompatActivity implements RxPopupViewManager.TipListener {


    public static final String TIP_TEXT = "Tip";
    private static final String TAG = ActivityPopupView.class.getSimpleName();
    RxTitle mRxTitle;
    TextView mTvImply;
    TextView mTvDefinition;
    TextInputEditText mTextInputEditText;
    TextInputLayout mTextInputLayout;
    TextView mTextViewButtonsLabel;
    Button mButtonAbove;
    Button mButtonBelow;
    Button mButtonLeftTo;
    Button mButtonRightTo;
    LinearLayout mLinearLayoutButtonsAboveBelow;
    RadioButton mButtonAlignLeft;
    RadioButton mButtonAlignCenter;
    RadioButton mButtonAlignRight;
    RadioGroup mLinearLayoutButtonsAlign;
    TextView mTextView;
    RelativeLayout mParentLayout;
    RelativeLayout mRootLayout;
    LinearLayout mActivityPopupView;
    RxPopupViewManager mRxPopupViewManager;
    @RxPopupView.Align
    int mAlign = RxPopupView.ALIGN_CENTER;
    private RxPopupSingleView titlePopup;
    private RxPopupImply popupImply;//提示  一小时后有惊喜

    public Activity mContext;

    RxPopupView.Builder builder;
    View tipvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_view);
        initView();
    }

    protected void initView() {
        mContext = this;

        mRxTitle = findViewById(R.id.rx_title);
        mRxTitle.setLeftFinish(mContext);

        mRxPopupViewManager = new RxPopupViewManager(this);

        mTvImply = findViewById(R.id.tv_imply);
        mTvImply.setOnClickListener(v -> {
            if (popupImply == null) {
                popupImply = new RxPopupImply(mContext);
            }
            popupImply.show(mTvImply);
        });

        mTvDefinition = findViewById(R.id.tv_definition);
        mTvDefinition.setOnClickListener(v -> {
            initPopupView();
            titlePopup.show(mTvDefinition, 0);
        });

        mTextInputEditText = findViewById(R.id.text_input_edit_text);
        String text = TextUtils.isEmpty(mTextInputEditText.getText()) ? TIP_TEXT : mTextInputEditText.getText().toString();


        mTextInputLayout = findViewById(R.id.text_input_layout);
        mTextViewButtonsLabel = findViewById(R.id.text_view_buttons_label);

        mButtonAbove = findViewById(R.id.button_above);
        mButtonAbove.setOnClickListener(v -> {
            mRxPopupViewManager.findAndDismiss(mTextView);
            builder = new RxPopupView.Builder(this, mTextView, mParentLayout, text, RxPopupView.POSITION_ABOVE);
            builder.setAlign(mAlign);
            tipvView = mRxPopupViewManager.show(builder.build());
        });

        mButtonBelow = findViewById(R.id.button_below);
        mButtonBelow.setOnClickListener(v -> {
            mRxPopupViewManager.findAndDismiss(mTextView);
            builder = new RxPopupView.Builder(this, mTextView, mParentLayout, text, RxPopupView.POSITION_BELOW);
            builder.setAlign(mAlign);
            builder.setBackgroundColor(getResources().getColor(R.color.orange));
            tipvView = mRxPopupViewManager.show(builder.build());
        });

        mButtonLeftTo = findViewById(R.id.button_left_to);
        mButtonLeftTo.setOnClickListener(v -> {
            mRxPopupViewManager.findAndDismiss(mTextView);
            builder = new RxPopupView.Builder(this, mTextView, mParentLayout, text, RxPopupView.POSITION_LEFT_TO);
            builder.setBackgroundColor(getResources().getColor(R.color.greenyellow));
            builder.setTextColor(getResources().getColor(R.color.black));
            builder.setGravity(RxPopupView.GRAVITY_CENTER);
            builder.setTextSize(12);
            tipvView = mRxPopupViewManager.show(builder.build());
        });

        mButtonRightTo = findViewById(R.id.button_right_to);
        mButtonRightTo.setOnClickListener(v -> {
            mRxPopupViewManager.findAndDismiss(mTextView);
            builder = new RxPopupView.Builder(this, mTextView, mParentLayout, text, RxPopupView.POSITION_RIGHT_TO);
            builder.setBackgroundColor(getResources().getColor(R.color.paleturquoise));
            builder.setTextColor(getResources().getColor(android.R.color.black));
            tipvView = mRxPopupViewManager.show(builder.build());
        });

        mLinearLayoutButtonsAboveBelow = findViewById(R.id.linear_layout_buttons_above_below);

        mButtonAlignCenter = findViewById(R.id.button_align_center);
        mButtonAlignCenter.setOnClickListener(v -> {
            mAlign = RxPopupView.ALIGN_CENTER;
        });

        mButtonAlignLeft = findViewById(R.id.button_align_left);
        mButtonAlignLeft.setOnClickListener(v -> {
            mAlign = RxPopupView.ALIGN_LEFT;
        });

        mButtonAlignRight = findViewById(R.id.button_align_right);
        mButtonAlignRight.setOnClickListener(v -> mAlign = RxPopupView.ALIGN_RIGHT);

        mLinearLayoutButtonsAlign = findViewById(R.id.linear_layout_buttons_align);

        mTextView = findViewById(R.id.text_view);

        mParentLayout = findViewById(R.id.parent_layout);

        mRootLayout = findViewById(R.id.root_layout);

        mActivityPopupView = findViewById(R.id.activity_popup_view);

        mButtonAlignCenter.setChecked(true);

    }

    private void initPopupView() {
        titlePopup = new RxPopupSingleView(mContext, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.layout.popupwindow_definition_layout);
        titlePopup.addAction(new ActionItem("标清"));
        titlePopup.addAction(new ActionItem("高清"));
        titlePopup.addAction(new ActionItem("超清"));
        titlePopup.setItemOnClickListener(new RxPopupSingleView.OnItemOnClickListener() {

            @Override
            public void onItemClick(ActionItem item, int position) {
                // TODO Auto-generated method stub
                if (titlePopup.getAction(position).mTitle.equals(mTvDefinition.getText())) {
                    RxToast.showToast(mContext, "当前已经为" + mTvDefinition.getText(), 500);
                } else {
                    if (position >= 0 && position < 3) {
                        mTvDefinition.setText(titlePopup.getAction(position).mTitle);
                    }
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        RxPopupView.Builder builder = new RxPopupView.Builder(this, mTextView, mRootLayout, TIP_TEXT, RxPopupView.POSITION_ABOVE);
        builder.setAlign(mAlign);
        mRxPopupViewManager.show(builder.build());
    }


    @Override
    public void onTipDismissed(View view, int anchorViewId, boolean byUser) {
        Log.d(TAG, "tip near anchor view " + anchorViewId + " dismissed");

        if (anchorViewId == R.id.text_view) {
            // Do something when a tip near view with id "R.id.text_view" has been dismissed
        }
    }
}

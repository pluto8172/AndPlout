package com.wl.pluto.module_component.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author szy
 * Created on 2019-08-22
 * @function
 *
 * 16：9 的RelativeLayout
 */
public class VideoRelativeLayout extends LinearLayout {
    public VideoRelativeLayout(Context context) {
        super(context);
    }

    public VideoRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (width * 9) / 16;
        setMeasuredDimension(width, height);
    }
}

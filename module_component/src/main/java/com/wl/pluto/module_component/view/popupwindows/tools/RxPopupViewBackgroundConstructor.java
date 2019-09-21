package com.wl.pluto.module_component.view.popupwindows.tools;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.wl.pluto.module_component.R;


/**
 * @author Vondear
 */
class RxPopupViewBackgroundConstructor {

    /**
     * Select which background will be assign to the tip view
     */
    static void setBackground(View tipView, RxPopupView rxPopupView) {

        // show tool tip without arrow. no need to continue
        if (rxPopupView.hideArrow()) {
            setToolTipNoArrowBackground(tipView, rxPopupView.getBackgroundColor());
            return;
        }

        // show tool tip according to requested position
        switch (rxPopupView.getPosition()) {
            case RxPopupView.POSITION_ABOVE:
                setToolTipAboveBackground(tipView, rxPopupView);
                break;
            case RxPopupView.POSITION_BELOW:
                setToolTipBelowBackground(tipView, rxPopupView);
                break;
            case RxPopupView.POSITION_LEFT_TO:
                setToolTipLeftToBackground(tipView, rxPopupView.getBackgroundColor());
                break;
            case RxPopupView.POSITION_RIGHT_TO:
                setToolTipRightToBackground(tipView, rxPopupView.getBackgroundColor());
                break;
            default:
                break;
        }

    }

    private static void setToolTipAboveBackground(View tipView, RxPopupView rxPopupView) {
        switch (rxPopupView.getAlign()) {
            case RxPopupView.ALIGN_CENTER:
                setTipBackground(tipView, R.mipmap.tooltip_arrow_down, rxPopupView.getBackgroundColor());
                break;
            case RxPopupView.ALIGN_LEFT:
                setTipBackground(tipView,
                        !RxPopupViewTool.isRtl() ?
                                R.mipmap.tooltip_arrow_down_left :
                                R.mipmap.tooltip_arrow_down_right
                        , rxPopupView.getBackgroundColor());
                break;
            case RxPopupView.ALIGN_RIGHT:
                setTipBackground(tipView,
                        !RxPopupViewTool.isRtl() ?
                                R.mipmap.tooltip_arrow_down_right :
                                R.mipmap.tooltip_arrow_down_left
                        , rxPopupView.getBackgroundColor());
                break;
            default:
                break;
        }
    }

    private static void setToolTipBelowBackground(View tipView, RxPopupView rxPopupView) {

        switch (rxPopupView.getAlign()) {
            case RxPopupView.ALIGN_CENTER:
                setTipBackground(tipView, R.mipmap.tooltip_arrow_up, rxPopupView.getBackgroundColor());
                break;
            case RxPopupView.ALIGN_LEFT:
                setTipBackground(tipView,
                        !RxPopupViewTool.isRtl() ?
                                R.mipmap.tooltip_arrow_up_left :
                                R.mipmap.tooltip_arrow_up_right
                        , rxPopupView.getBackgroundColor());
                break;
            case RxPopupView.ALIGN_RIGHT:
                setTipBackground(tipView,
                        !RxPopupViewTool.isRtl() ?
                                R.mipmap.tooltip_arrow_up_right :
                                R.mipmap.tooltip_arrow_up_left
                        , rxPopupView.getBackgroundColor());
                break;
            default:
                break;
        }

    }

    private static void setToolTipLeftToBackground(View tipView, int color) {
        setTipBackground(tipView, !RxPopupViewTool.isRtl() ?
                        R.mipmap.tooltip_arrow_right : R.mipmap.tooltip_arrow_left,
                color);
    }

    private static void setToolTipRightToBackground(View tipView, int color) {
        setTipBackground(tipView, !RxPopupViewTool.isRtl() ?
                        R.mipmap.tooltip_arrow_left : R.mipmap.tooltip_arrow_right,
                color);
    }

    private static void setToolTipNoArrowBackground(View tipView, int color) {
        setTipBackground(tipView, R.mipmap.tooltip_no_arrow, color);
    }

    private static void setTipBackground(View tipView, int mipmapRes, int color) {
        Drawable paintedmipmap = getTintedmipmap(tipView.getContext(),
                mipmapRes, color);
        setViewBackground(tipView, paintedmipmap);
    }

    private static void setViewBackground(View view, Drawable mipmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackground(mipmap);
        } else {
            view.setBackgroundDrawable(mipmap);
        }
    }

    private static Drawable getTintedmipmap(Context context, int mipmapRes, int color) {
        Drawable mipmap;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mipmap = context.getResources().getDrawable(mipmapRes, null);
            if (mipmap != null) {
                mipmap.setTint(color);
            }
        } else {
            mipmap = context.getResources().getDrawable(mipmapRes);
            if (mipmap != null) {
                mipmap.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            }
        }

        return mipmap;
    }

}

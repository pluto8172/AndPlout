package com.wl.pluto.module_component.view.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wl.pluto.module_component.R;
import com.wl.pluto.module_component.view.RxTextAutoZoom;
import com.wl.pluto.module_component.view.cardstack.RxCardStackView;
import com.wl.pluto.module_component.view.cardstack.tools.RxAdapterStack;

import java.io.File;

public class AdapterCardViewModelPicture extends RxAdapterStack<ModelPicture> {

    public AdapterCardViewModelPicture(Context context) {
        super(context);
    }

    @Override
    public void bindView(ModelPicture data, int position, RxCardStackView.ViewHolder holder) {
        if (holder instanceof ColorItemViewHolder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind(data, position);
            if (getItemCount() < 2) {
                h.mRxTextAutoZoom.setVisibility(View.GONE);
            } else {
                h.mRxTextAutoZoom.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected RxCardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.card_item_picture, parent, false);
        return new ColorItemViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.card_item_picture;
    }

    static class ColorItemViewHolder extends RxCardStackView.ViewHolder {
        View mContainerContent;
        ImageView mIvPicture;
        TextView mTvPointLo;
        TextView mTvPointLa;
        TextView mTvCollectDate;
        RxTextAutoZoom mRxTextAutoZoom;


        public ColorItemViewHolder(View view) {
            super(view);
            mIvPicture = view.findViewById(R.id.iv_picture);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTvPointLo = view.findViewById(R.id.tv_point_lo);
            mTvPointLa = view.findViewById(R.id.tv_point_la);
            mTvCollectDate = view.findViewById(R.id.tv_collect_date);
            mRxTextAutoZoom = view.findViewById(R.id.tv_number);

        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(ModelPicture data, int position) {
            Glide.with(getContext()).
                    load(new File(data.getPicturePath())).
                    thumbnail(0.5f).
                    into(mIvPicture);
            mTvCollectDate.setText(data.getDate());
            mTvPointLo.setText(data.getLongitude());
            mTvPointLa.setText(data.getLatitude());
            mRxTextAutoZoom.setText("第 " + (position + 1) + " 张");
        }

    }

}

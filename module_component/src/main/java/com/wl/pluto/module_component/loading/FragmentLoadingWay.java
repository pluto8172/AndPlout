package com.wl.pluto.module_component.loading;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wl.pluto.module_component.R;
import com.wl.pluto.module_component.view.progressing.SpinKitView;
import com.wl.pluto.module_component.view.progressing.SpriteFactory;
import com.wl.pluto.module_component.view.progressing.Style;
import com.wl.pluto.module_component.view.progressing.sprite.Sprite;

import com.wl.pluto.lib_kit.rxtool.RxRecyclerViewDividerTool;
import com.wl.pluto.lib_kit.rxtool.RxSizeTool;


/**
 * Created by Vondear.
 *
 * @author vondear
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class FragmentLoadingWay extends Fragment {

    int[] colors = new int[]{
            android.graphics.Color.parseColor("#99CCFF"),
            android.graphics.Color.parseColor("#34A853"),
    };

    public static FragmentLoadingWay newInstance() {
        return new FragmentLoadingWay();
    }


    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page1, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RxRecyclerViewDividerTool(RxSizeTool.dp2px(5f)));
        recyclerView.setAdapter(new RecyclerView.Adapter<Holder>() {
            @Override
            public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(Holder holder, int position) {
                holder.bind(position);
            }

            @Override
            public int getItemCount() {
                return Style.values().length;
            }
        });
    }

    class Holder extends RecyclerView.ViewHolder {

        SpinKitView spinKitView;

        public Holder(View itemView) {
            super(itemView);
            spinKitView = itemView.findViewById(R.id.spin_kit);
        }

        public void bind(int position) {
            itemView.setBackgroundColor(colors[position % colors.length]);
            final int finalPosition = position;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityLoadingDetail.start(v.getContext(), finalPosition);
                }
            });
            position = position % 15;
            Style style = Style.values()[position];
            Sprite drawable = SpriteFactory.create(style);
            spinKitView.setIndeterminateDrawable(drawable);
        }
    }

}

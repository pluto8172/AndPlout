package com.wl.pluto.module_component.loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wl.pluto.module_component.R;
import com.wl.pluto.module_component.main.ui.EvaluatorARGB;
import com.wl.pluto.module_component.view.progressing.SpinKitView;
import com.wl.pluto.module_component.view.progressing.SpriteFactory;
import com.wl.pluto.module_component.view.progressing.Style;
import com.wl.pluto.module_component.view.progressing.sprite.Sprite;


/**
 * @author vondear
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class ActivityLoadingDetail extends AppCompatActivity {

    int[] colors = new int[]{
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#2B3E51"),
            android.graphics.Color.parseColor("#00BD9C"),
            android.graphics.Color.parseColor("#227FBB"),
            android.graphics.Color.parseColor("#7F8C8D"),
            android.graphics.Color.parseColor("#FFCC5C"),
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#1AAF5D"),
    };

    @SuppressWarnings("WeakerAccess")
    public static void start(Context context, int position) {
        Intent intent = new Intent(context, ActivityLoadingDetail.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_detail);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Style.values().length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_pager, null);

                SpinKitView spinKitView = view.findViewById(R.id.spin_kit);
                TextView name = view.findViewById(R.id.name);
                Style style = Style.values()[position];
                name.setText(style.name());
                Sprite drawable = SpriteFactory.create(style);
                spinKitView.setIndeterminateDrawable(drawable);
                container.addView(view);

                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int color = (int) EvaluatorARGB.getInstance().evaluate(positionOffset,
                        colors[position % colors.length],
                        colors[(position + 1) % colors.length]);
                getWindow().getDecorView().setBackgroundColor(color);
            }

            @Override
            public void onPageSelected(int position) {
                getWindow().getDecorView().setBackgroundColor(colors[position % colors.length]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
    }
}

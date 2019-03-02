package com.a1magway.bgg.v.mainhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.widget.CycleGalleryViewPager;


/**
 * 在GalleryViewPager的基础上加入了无限循环滑动功能
 */
public class CycleGalleryFragment extends Fragment {

    final private static int[] PIC_RES = new int[]{
//      R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4, R.mipmap.pic5
            R.mipmap.ic_guide_1, R.mipmap.ic_guide_2, R.mipmap.ic_guide_3
    };

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CycleGalleryViewPager viewPager = (CycleGalleryViewPager) inflater.inflate(R.layout.fragment_cycle_gallery, container, false);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return PIC_RES.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = inflater.inflate(R.layout.gallery_item, container);
                ((TextView) view.findViewById(R.id.title)).setText("Pager " + position);
                ((ImageView) view.findViewById(R.id.imageview)).setImageResource(PIC_RES[position]);
                container.addView(view);
                return container;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public float getPageWidth(int position) {
                return 0.8f;//建议值为0.6~1.0之间
            }
        });
        viewPager.setNarrowFactor(0.95f);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("pppppppp", position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return viewPager;
    }
}
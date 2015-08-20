package com.facipeople.jn.guideview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by jn on 2015/8/20.
 * 功能描述：ViewPager适配器，用来绑定数据和View
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> views;

    public ViewPagerAdapter(ArrayList<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        if(views != null){
            return views.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(views.get(position),0);
        //((ViewPager)view).addView(views.get(position),0);

        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView(views.get(position));
    }
}

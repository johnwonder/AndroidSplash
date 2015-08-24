package com.facipeople.jn.guideview02;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
http://www.cnblogs.com/dwinter/archive/2012/02/27/AndroidViewPager%E5%A4%9A%E9%A1%B5%E9%9D%A2%E6%BB%91%E5%8A%A8%E5%88%87%E6%8D%A2%E4%BB%A5%E5%8F%8A%E5%8A%A8%E7%94%BB%E6%95%88%E6%9E%9C.html#top
 */
public class MainActivity extends ActionBarActivity {

    private ViewPager mPager;
    private List<View> listViews;
    private ImageView cursor;
    private TextView t1,t2,t3;
    private int offset =0;//动画图片偏移量
    private  int currIndex = 0; //当前页卡编号
    private  int bmpW;//图片宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextView();
    }

    private  void initTextView()
    {
        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);

        t1.setOnClickListener(new MyOnClickListener(0));
        t1.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));

    }

    class MyOnClickListener implements  View.OnClickListener
    {
        public MyOnClickListener(int index) {
            this.index = index;
        }

        private int index =0;
        @Override
        public void onClick(View view) {

            mPager.setCurrentItem(index);
        }
    }

    private void initViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.lay1,null));
        listViews.add(mInflater.inflate(R.layout.lay2, null));
        listViews.add(mInflater.inflate(R.layout.lay3, null));

        mPager.setAdapter(new MyPagerAdapter(listViews));
    }

    public  class  MyPagerAdapter extends PagerAdapter{

        public  List<View> mListView;

        public MyPagerAdapter(List<View> mListView) {
            this.mListView = mListView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListView.get(position));
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

        @Override
        public int getCount() {
            return mListView.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListView.get(position),0);
            return mListView.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(ViewGroup container) {

        }
    }

    private  void initImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        //bmpW = BitmapFactory.decodeResource(getResources(),R.drawable.a).getWidth();//获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();//
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;//获取分辨率宽度
        offset = ((screenW / 3) -bmpW)/2;//计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset,0);
        cursor.setImageMatrix(matrix);//设置动画初始位置
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public  class  MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        int one = offset*2 +bmpW;// 页卡1->页卡2 偏移量
        int two = one*2 ;// 页卡1 到页卡3 偏移量

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            switch (position) {
                case 0:
                    if (currIndex == 1) {

                        animation = new TranslateAnimation(one, 0, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                    }
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, one, 0, 0);

                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                    }
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, two, 0, 0);
                    } else if (currIndex == 1) {

                        animation = new TranslateAnimation(one, two, 0, 0);
                    }
                    break;
            }
            currIndex = position;
            animation.setFillAfter(true);
            animation.setDuration(300);
            cursor.setAnimation(animation);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

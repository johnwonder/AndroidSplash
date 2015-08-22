package com.facipeople.jn.guideview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    //定义ViewPager对象
    private ViewPager viewPager;

    //定义ViewPager适配器
    private ViewPagerAdapter vpAdapter;

    //定义一个ArrayList来存放View
    private ArrayList<View> views;

    //引导图片资源
    private  static  final  int[] pics = { R.drawable.guide1,R.drawable.guide2,R.drawable.guide3,R.drawable.guide4 };

    //底部小点的图片
    private ImageView[] points;

    private  int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initView(){
        views = new ArrayList<View>();

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        vpAdapter = new ViewPagerAdapter(views);
    }

    private  void initData(){
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);

        for (int i = 0; i < pics.length; i++){
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            views.add(iv);
        }

        viewPager.setAdapter(vpAdapter);

        //http://www.bubuko.com/infodetail-990021.html
        viewPager.setOnPageChangeListener(this);

        initPoint();
    }

    private  void initPoint(){

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);

        points = new ImageView[pics.length];

        for (int i = 0; i < pics.length ; i++){
            points[i] = (ImageView) linearLayout.getChildAt(i);

            points[i].setEnabled(true);

            points[i].setOnClickListener(this);

            points[i].setTag(i);
        }

        currentIndex =0;
        points[currentIndex].setEnabled(false);
    }


    /*
     当当前页面被滑动时调用
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /*
        当滑动状态改变时调用
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
    }

    @Override
    public void onClick(View v) {
        int position =  Integer.parseInt(v.getTag().toString());
        setCurView(position);
        setCurDot(position);
    }

    private  void setCurView(int position){
        if(position < 0 || position >= pics.length)
            return;
        viewPager.setCurrentItem(position);
    }

    private  void setCurDot(int position){
        if(position < 0 || position > pics.length -1 || currentIndex == position){
            return;
        }
        points[position].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = position;
    }


}

package com.yyq.slidngmenudemo;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private TextView firstTv,secondTv,thirdTv;
    private ViewPager viewPager;
    
    //title下滑动光标
    private ImageView imageView;
    private MyFragmentPagerAdapter adapter;
    private ArrayList<Fragment> fragmentList;
    //宽度
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewpager();
    }

    private void initView() {
        firstTv = (TextView) findViewById(R.id.first_tv);
        secondTv = (TextView) findViewById(R.id.second_tv);
        thirdTv = (TextView) findViewById(R.id.third_tv);
        imageView = (ImageView) findViewById(R.id.scrollView);
        imageView.setBackgroundColor(Color.GREEN);
        firstTv.setOnClickListener(this);
        secondTv.setOnClickListener(this);
        thirdTv.setOnClickListener(this);

        width = firstTv.getWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels/3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width/2,5);
        params.leftMargin = width/4;
        imageView.setLayoutParams(params);
    }


    private void initViewpager() {

        Fragment fragmentFirst = new BlankFragment();
        Fragment fragmentSecond = new BlankFragment();
        Fragment fragmentThird = new BlankFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(fragmentFirst);
        fragmentList.add(fragmentSecond);
        fragmentList.add(fragmentThird);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        firstTv.setTextColor(Color.BLUE);
        viewPager.setOnPageChangeListener(this);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        params.leftMargin = (int) (position*width+positionOffset*width)+width/4;
        imageView.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        switch (position)
        {
            case 0:
                setTextColor(firstTv,secondTv,thirdTv);
                break;
            case 1:
                setTextColor(secondTv,firstTv,thirdTv);
                break;
            case 2:
                setTextColor(thirdTv,firstTv,secondTv);
                break;
        }
    }

    private void setTextColor(TextView tv1,TextView tv2,TextView tv3) {
        tv1.setTextColor(Color.BLUE);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.first_tv:
                viewPager.setCurrentItem(0);
                break;
            case R.id.second_tv:
                viewPager.setCurrentItem(1);
                break;
            case R.id.third_tv:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}

class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList;
    public MyFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

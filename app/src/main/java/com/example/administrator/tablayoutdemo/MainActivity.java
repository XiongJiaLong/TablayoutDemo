package com.example.administrator.tablayoutdemo;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout mTab;
    ViewPager mVp;
    List<View> list = new ArrayList<>();
    List<String> sList = new ArrayList<>();
    List<Fragment> fList = new ArrayList<>();
    int pages = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTab = (TabLayout) findViewById(R.id.mTab);
        mVp = (ViewPager) findViewById(R.id.mVp);
        initList();
        initTablayout();
        mTab.setupWithViewPager(mVp);
//        mVp.setAdapter(new mVpAdapter());
        mFragVpAdapter mFragVpAdapter = new mFragVpAdapter(getSupportFragmentManager());
        mVp.setAdapter(mFragVpAdapter);

    }

    private void initTablayout(){
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //不生效的颜色
//        mTab.setTabTextColors(R.color.colorAccent,R.color.colorPrimaryDark);
        //生效的颜色设置
        mTab.setTabTextColors(Color.GRAY,Color.GREEN);
        for (int i=0;i<sList.size();i++) {
            //联合vp设置的tab不会显示，单独设置的tab会显示
//            mTab.addTab(mTab.newTab().setText(sList.get(i)));
            ViewPagerFragment fragment = new ViewPagerFragment();
            fragment.setContent(i);
            fList.add(fragment);
        }
    }
    class mVpAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab+"+position;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
    class mFragVpAdapter extends FragmentPagerAdapter{

        public mFragVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fList.get(position);
        }

        @Override
        public int getCount() {
            return fList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sList.get(position);
        }
    }

    private void initList(){
        for (int i=0;i<pages;i++){
            TextView tv = new TextView(this);
            tv.setText("这是第 "+i+" 页");
            list.add(tv);
            sList.add("NO."+i);
        }
    }
}

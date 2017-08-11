package com.example.root.gridandbean;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends FragmentActivity {

    public TabPageIndicator mIndicator;
    private ViewPager awesomePager;
    private PagerAdapter pm;
    private static final String[] CONTENT = new String[] { "Recent", "Artists", "333" };
    String buttonNames[] = new String[21]; //Set the amount of button.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        awesomePager = findViewById(R.id.pager);
        mIndicator = findViewById(R.id.pagerIndicator);

        ArrayList<String> content = new ArrayList<>();
        for (int i = 0; i < CONTENT.length; i++) {
            content.add(i, CONTENT[i]);
        }
        Iterator<String> ct;
        ct = content.iterator();

        ArrayList<String> btn = new ArrayList<>();
        for (int i = 0; i < buttonNames.length; i++) {
            btn.add(i, buttonNames[i]);
        }
        Iterator<String> it;
        it = btn.iterator();
        int b = 0;

        List<GridFragment> gridFragments = new ArrayList<>();
        //Prepare all GridFragment
        while (it.hasNext()) {
            List<GridItems> itmLst = new ArrayList<>();
            while (it.hasNext()) {

                itmLst.add(new GridItems(b, it.next()));
                b = b + 1;

                if (b % 9 == 0 || !(it.hasNext())) {
                    GridItems[] gp = {};
                    GridItems[] gridPage = itmLst.toArray(gp);
                    gridFragments.add(new GridFragment(gridPage, MainActivity.this));
                    break;
                }

            }
        }


/* ----------------------------------------------
        ArrayList<String> btn = new ArrayList<>();

        for (int i = 0; i < buttonNames.length; i++) {
            btn.add(i, buttonNames[i]);

        }

        List<GridFragment> gridFragments = new ArrayList<>();

        Iterator<String> it;
        it = btn.iterator();
        int b = 0;

        //Prepare all GridFragment
        while (it.hasNext()) {
            List<GridItems> itmLst = new ArrayList<>();
            while (it.hasNext()) {

                itmLst.add(new GridItems(b, it.next()));
                b = b + 1;

                if (b % 9 == 0 || !(it.hasNext())) {
                    GridItems[] gp = {};
                    GridItems[] gridPage = itmLst.toArray(gp);
                    gridFragments.add(new GridFragment(gridPage, MainActivity.this));
                    break;
                }
            }
        }
----------------------------------------------*/
        pm = new PagerAdapter(getSupportFragmentManager(),gridFragments);
        awesomePager.setAdapter(pm);
        mIndicator.setViewPager(awesomePager); //Attach on awesomePager

    }

        private class PagerAdapter extends FragmentStatePagerAdapter {
        private List<GridFragment> fragments;

        public PagerAdapter(FragmentManager fm, List<GridFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }
        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
}
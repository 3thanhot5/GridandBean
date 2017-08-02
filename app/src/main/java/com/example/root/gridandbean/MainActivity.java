package com.example.root.gridandbean;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.PageIndicator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends FragmentActivity {

    public PageIndicator mIndicator;
    private ViewPager awesomePager;
    private PagerAdapter pm;

    String buttonNames[] = new String[14]; //Set the amount of button.
    /*String deviceNames[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z" };
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        awesomePager = findViewById(R.id.pager);
        mIndicator = findViewById(R.id.pagerIndicator);

        ArrayList<String> a = new ArrayList<>();

        for (int i = 0; i < buttonNames.length; i++) {
            a.add(i, buttonNames[i]);

        }

        List<GridFragment> gridFragments = new ArrayList<>();

        Iterator<String> it;
        it = a.iterator();
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

        pm = new PagerAdapter(getSupportFragmentManager(), gridFragments);
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
        public int getCount() {
            return this.fragments.size();
        }
    }
}
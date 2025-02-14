package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext = new ArrayList<String>();

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
//생성자?
    public  VPAdapter(FragmentManager fm){
        super(fm);
        items=new ArrayList<Fragment>();
        items.add(new Frag_dolvom());
        items.add(new Frag_friendRequest());

        itext.add("돌보미 목록");
        itext.add("돌보미 신청");

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }
}

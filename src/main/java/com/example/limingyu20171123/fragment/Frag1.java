package com.example.limingyu20171123.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.limingyu20171123.activity.Main2Activity;
import com.example.limingyu20171123.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class Frag1 extends Fragment {

    ArrayList<Fragment> fragmentlist = new ArrayList<>();
    ArrayList<String> titlelist = new ArrayList<>();
    private ViewPager vp;
    private TabLayout tl;
    private TextView jia;
    private String item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1, container, false);
        vp = view.findViewById(R.id.f1_vp);
        tl = view.findViewById(R.id.tl);
        jia = view.findViewById(R.id.jia);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDate();
        MyTAdapter myTAdapter = new MyTAdapter(getChildFragmentManager());
        vp.setAdapter(myTAdapter);
        tl.setTabMode(TabLayout.MODE_FIXED);
        tl.setupWithViewPager(vp);
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    private void initDate() {
        for (int a = 0; a < 4; a++) {
            fragmentlist.add(new CFrag());
        }
        for (int a = 0; a < 4; a++) {
            titlelist.add("aa" + a);
        }

        titlelist.add(item);

    }

    class MyTAdapter extends FragmentPagerAdapter {

        public MyTAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titlelist.get(position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences preferences = getActivity().getSharedPreferences("yk", Context.MODE_PRIVATE);
        item = preferences.getString("item", "无");

    }
}

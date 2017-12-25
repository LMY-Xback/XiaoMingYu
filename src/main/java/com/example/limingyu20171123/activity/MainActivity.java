package com.example.limingyu20171123.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.limingyu20171123.R;
import com.example.limingyu20171123.fragment.Frag1;
import com.example.limingyu20171123.fragment.Frag2;
import com.example.limingyu20171123.fragment.Frag3;
import com.example.limingyu20171123.fragment.Frag4;

public class MainActivity extends AppCompatActivity {


    private ViewPager vp;
    private RadioGroup rg;
    private RadioButton one;
    private RadioButton two;
    private RadioButton three;
    private RadioButton four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        one.setChecked(true);
                        one.setBackgroundColor(R.color.red);
                        two.setBackgroundColor(R.color.black);
                        three.setBackgroundColor(R.color.black);
                        four.setBackgroundColor(R.color.black);
                        break;
                    case 1:
                        two.setChecked(true);
                        one.setBackgroundColor(R.color.black);
                        two.setBackgroundColor(R.color.red);
                        three.setBackgroundColor(R.color.black);
                        four.setBackgroundColor(R.color.black);
                        break;
                    case 2:
                        three.setChecked(true);
                        one.setBackgroundColor(R.color.black);
                        two.setBackgroundColor(R.color.black);
                        three.setBackgroundColor(R.color.red);
                        four.setBackgroundColor(R.color.black);
                        break;
                    case 3:
                        four.setChecked(true);
                        one.setBackgroundColor(R.color.black);
                        two.setBackgroundColor(R.color.black);
                        three.setBackgroundColor(R.color.black);
                        four.setBackgroundColor(R.color.red);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int ii = 0;
                switch (i) {
                    case R.id.one:
                        ii = 0;
                        break;
                    case R.id.two:
                        ii = 1;
                        break;
                    case R.id.three:
                        ii = 2;
                        break;
                    case R.id.four:
                        ii = 3;
                        break;
                }
                vp.setCurrentItem(ii);
            }
        });

        vp.setAdapter(new MyVpAdapter(getSupportFragmentManager()));
    }

    private class MyVpAdapter extends FragmentPagerAdapter {
        public MyVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Frag1 frag1 = new Frag1();
                    return frag1;
                case 1:
                    Frag2 frag2 = new Frag2();
                    return frag2;
                case 2:
                    Frag3 frag3 = new Frag3();
                    return frag3;
                case 3:
                    Frag4 frag4 = new Frag4();
                    return frag4;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}

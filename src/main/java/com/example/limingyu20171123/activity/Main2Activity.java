package com.example.limingyu20171123.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.limingyu20171123.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    List<String> list = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    private GridView gv;
    private GridView gv2;
    private Button close;
    private TextView bianji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gv = findViewById(R.id.gv);
        gv2 = findViewById(R.id.gv2);
        close = findViewById(R.id.close);
        bianji = findViewById(R.id.bianji);
        initDate();
        gv.setAdapter(new GvAdapter());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 15; i++) {
                    list2.add("+添加" + i);
                }
                gv2.setAdapter(new My());
            }
        });
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences sp = getSharedPreferences("yk", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("itme","添加1");
                edit.commit();
                finish();
            }
        });

    }

    private void initDate() {
        for (int i = 0; i < 21; i++) {
            list.add("频道" + i);
        }

    }

    private class GvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view=View.inflate(Main2Activity.this,R.layout.gv_child,null);
            }
            Button btn = view.findViewById(R.id.gv_c_btn);
            btn.setText(list.get(i));
            return view;
        }
    }

    private class My extends BaseAdapter {
        @Override
        public int getCount() {
            return list2.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view=View.inflate(Main2Activity.this,R.layout.gv2_child,null);
            }
            TextView btn = view.findViewById(R.id.gv2_c_btn);
            btn.setText(list2.get(i));

            return view;
        }
    }
}

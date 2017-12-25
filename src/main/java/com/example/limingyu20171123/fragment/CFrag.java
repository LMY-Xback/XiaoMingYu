package com.example.limingyu20171123.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.limingyu20171123.JsonBeen;
import com.example.limingyu20171123.R;
import com.example.limingyu20171123.Utils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class CFrag extends Fragment {

    int i;

    List<JsonBeen.DataBean> list = new ArrayList<>();
    private PullToRefreshListView plv;
    private MyAdapter myAdapter;
    private ImageLoader instance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.child_frag, container, false);
        plv = view.findViewById(R.id.plv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instance = ImageLoader.getInstance();
        myAdapter = new MyAdapter();
        plv.setAdapter(myAdapter);
        plv.setMode(PullToRefreshBase.Mode.BOTH);
        i=1;
        String url = "http://www.93.gov.cn/93app/data.do?channelId=0&startNum="+i;
        new MyAsyncTask().execute(url);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                i=1;
                String url = "http://www.93.gov.cn/93app/data.do?channelId=0&startNum="+i;
                new MyAsyncTask().execute(url);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                i++;
                String url = "http://www.93.gov.cn/93app/data.do?channelId=0&startNum="+i;
                new MyAsyncTask().execute(url);
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
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
                view=View.inflate(getActivity(),R.layout.c_child,null);
            }
            ImageView iv = view.findViewById(R.id.cciv);
            TextView tv = view.findViewById(R.id.cctv);
            tv.setText(list.get(i).getTITLE());
            instance.displayImage((String) list.get(i).getIMAGEURL(),iv);
            return view;
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
                 /* *
                   * 这个方法在子线程,在这个方法里进行联网操作
                   *
                   * @param strings 可变参数,底层是数组,里面放的是传过来的参数;
                   * @return*/

        @Override
        protected String doInBackground(String... strings) {
            String s = strings[0];
            //直接用工具类获取json
            String netJson = Utils.getNetJson(s);
            return netJson;//返回给(前台方法,主线程方法onPostExecute)
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //解析s,  直接给控件展示;
            Gson gson = new Gson();
            JsonBeen jsonBeen = gson.fromJson(s, JsonBeen.class);
            //把一个集合的数据放入另一个集合
            List<JsonBeen.DataBean> data = jsonBeen.getData();
            list.addAll(data);
            //更新集合
            myAdapter.notifyDataSetChanged();
            plv.onRefreshComplete();
        }
    }
}

package com.danlu.android.danlupickerview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.danlu.lib.WheelView;
import com.danlu.lib.adapter.ArrayWheelAdapter;
import com.danlu.lib.listener.OnItemSelectedListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    private WheelView wheelView1;
    private WheelView wheelView2;
    private WheelView wheelView3;

    private List<AreaModel> areaModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_picker);

        wheelView1 = (WheelView) findViewById(R.id.wv_options1);
        wheelView2 = (WheelView) findViewById(R.id.wv_options2);
        wheelView3 = (WheelView) findViewById(R.id.wv_options3);

//        loadData();
        final List<AreaModel> datas = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            AreaModel model = new AreaModel();
            model.setAreaName("四川省" + i);
            model.setAreaId(i + "");

            List<AreaModel.SecondBean> secondBeen = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                AreaModel.SecondBean bean = new AreaModel.SecondBean();
                bean.setAreaName("成都市");
                bean.setAreaId(i + "");
                secondBeen.add(bean);
            }
            model.setSecond(secondBeen);
            datas.add(model);
        }
        wheelView1.setAdapter(new ArrayWheelAdapter(datas));
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentItem = wheelView3.getCurrentItem();
                AreaModel.SecondBean.ThreeBean item = (AreaModel.SecondBean.ThreeBean) wheelView3.getAdapter().getItem(currentItem);
                Log.w("@@@@ L66", "MainActivity:onClick() -> " + item.getAreaId());
            }
        });
    }

    private ProgressDialog progressDialog;

    private void loadData() {
        progressDialog = ProgressDialog.show(this, null, "loadding");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonStr = readArea(getAssets().open("dl_area.txt"));
                    areaModelList = new ArrayList<>();
                    areaModelList.addAll(fromJsonArray(jsonStr, AreaModel.class));
                    refreshUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void refreshUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wheelView1.setOnItemSelectedListener(MainActivity.this);
                wheelView2.setOnItemSelectedListener(MainActivity.this);
                wheelView3.setOnItemSelectedListener(MainActivity.this);
                wheelView1.setCyclic(false);
                wheelView2.setCyclic(false);
                wheelView3.setCyclic(false);
                wheelView1.setAdapter(new ArrayWheelAdapter<>(areaModelList));
                wheelView1.setCurrentItem(0);
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onItemSelected(View wheelView, int index) {
        Log.w("@@@@ L78", "MainActivity:onItemSelected() -> " + index);
        if (wheelView == wheelView1) {
            AreaModel model = (AreaModel) wheelView1.getAdapter().getItem(index);
            List<AreaModel.SecondBean> second = model.getSecond();
            if (null == second) {
                second = new ArrayList<>();
            }
            wheelView2.setAdapter(new ArrayWheelAdapter(second));
            if (!second.isEmpty()) {
                wheelView2.setCurrentItem(0);
            } else {
                wheelView3.setAdapter(new ArrayWheelAdapter(new ArrayList<AreaModel.SecondBean.ThreeBean>()));
            }
        } else if (wheelView == wheelView2) {
            AreaModel.SecondBean item = (AreaModel.SecondBean) wheelView2.getAdapter().getItem(index);
            List<AreaModel.SecondBean.ThreeBean> three = item.getThree();
            if (null == three) {
                three = new ArrayList<>();
            }
            wheelView3.setAdapter(new ArrayWheelAdapter(three));
            if (!three.isEmpty()) {
                wheelView3.setCurrentItem(0);
            }
        } else if (wheelView == wheelView3) {

        }
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) throws Exception {
        List<T> lst = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }

    private String readArea(InputStream is) {
        String res = null;
        try {
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            res = new String(buffer, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


}

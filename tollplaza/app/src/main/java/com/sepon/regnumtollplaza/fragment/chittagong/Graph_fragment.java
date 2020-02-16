package com.sepon.regnumtollplaza.fragment.chittagong;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sepon.regnumtollplaza.R;
import com.sepon.regnumtollplaza.admin.Report;
import com.sepon.regnumtollplaza.fragment.Previous_fragment;
import com.sepon.regnumtollplaza.pojo.Previous_pojo;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Graph_fragment extends Fragment {
    private static String TAG = "Graph_fragment";

    ArrayList<Previous_pojo> list2; // = new ArrayList<>();
    protected Typeface tfLight;
    PieChartView pieChartView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grapg_fragment, container, false);

      //  list2 = new ArrayList<>();
         pieChartView = view.findViewById(R.id.piechart);
        getCtrlPersentage();
        //List pieData = new ArrayList<>();
      //  list2 = getArrayList("previous_report");
//
//            for (int i=0; i<list2.size();i++){
//                int value = Integer.parseInt(list2.get(i).getCtrl());
//                String date = list2.get(i).getDate();
//                Random rnd = new Random();
//                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//                pieData.add(new SliceValue(value, color).setLabel(value+"/"+date));
//            }

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.graph_view);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected


                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0: // first button

                        getCtrlPersentage();
                        Toast.makeText(getActivity(), "Selected button number " + index, Toast.LENGTH_SHORT).show();
                        break;
                    case 1: // secondbutton

                        Toast.makeText(getActivity(), "Selected button number " + index, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        return view;
    }

    private void getCtrlPersentage() {
        List<SliceValue> pieData = new ArrayList<>();
        int a2,a3,a4,a5,a6,a7;
        int a22,a33,a44,a55,a66,a77;
        ArrayList<Double> pList = new ArrayList<>();
      a2 = getArrayList("ctrlA2").size();
      a3 = getArrayList("ctrlA3").size();
      a4 = getArrayList("ctrlA4").size();
      a5 = getArrayList("ctrlA5").size();
      a6 = getArrayList("ctrlA6").size();
      a7 = getArrayList("ctrlA7").size();

      double avg = (double) 100/(a2 + a3 + a4 + a5 + a6 + a7);
      Double v = Double.valueOf(new DecimalFormat().format(avg));
      Log.e(TAG, "avg : "+v);

        pieData.add(new SliceValue((float) (v*a2), Color.parseColor("#3DB0FF") ));
        pieData.add(new SliceValue((float) (v*a3), Color.parseColor("#A5A5A5") ));
        pieData.add(new SliceValue((float) (v*a4), Color.parseColor("#ED7D31") ));
        pieData.add(new SliceValue((float) (v*a5), Color.parseColor("#ED561B") ));
        pieData.add(new SliceValue((float) (v*a6), Color.parseColor("#50B432") ));
        pieData.add(new SliceValue((float) (v*a7), Color.parseColor("#C1DC0A") ));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        // pieChartData.setHasCenterCircle(true).setCenterText1("Chorshinddu Axel").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);


    }

    private ArrayList<Previous_pojo> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Previous_pojo>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void piechart(){}

}



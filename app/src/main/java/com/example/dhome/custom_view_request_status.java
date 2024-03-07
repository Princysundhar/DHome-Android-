package com.example.dhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.PrimitiveIterator;

public class custom_view_request_status extends BaseAdapter {
    String[]rid,service_name,category_name,date,amount;
    private Context context;

    public custom_view_request_status(Context applicationContext, String[] rid, String[] service_name, String[] category_name, String[] date, String[] amount) {
        this.context = applicationContext;
        this.rid = rid;
        this.service_name = service_name;
        this.category_name = category_name;
        this.date = date;
        this.amount = amount;

    }


    @Override
    public int getCount() {
        return service_name.length;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_view_request_status,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView12);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView14);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView16);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView10);
        Button b1 = (Button) gridView.findViewById(R.id.button6);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("rid",rid[i]);
                ed.putString("amount",amount[i]);
                ed.commit();
                Intent k = new Intent(context,payment_mode.class);
                k.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(k);

            }
        });


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(service_name[i]);
        tv2.setText(category_name[i]);
        tv3.setText(amount[i]);
        tv4.setText(date[i]);


        return gridView;
    }
}
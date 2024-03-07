package com.example.dhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class custom_view_allocated_staff extends BaseAdapter {
    String[]aid,staff_name,staff_latitude,staff_longitude,area_name;
    private Context context;

    public custom_view_allocated_staff(Context applicationContext, String[] aid, String[] staff_name, String[] staff_latitude, String[] staff_longitude, String[] area_name) {
        this.context = applicationContext;
        this.aid = aid;
        this.staff_name = staff_name;
        this.staff_latitude = staff_latitude;
        this.staff_longitude = staff_longitude;
        this.area_name = area_name;

    }


    @Override
    public int getCount() {
        return staff_name.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_allocated_staff,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView12);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView14);

        Button b1 = (Button)gridView.findViewById(R.id.button6);            // Locate
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ik=(int)view.getTag();
                String url = "http://maps.google.com/?q=" + staff_latitude[ik] + "," + staff_longitude[ik];
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);


        tv1.setText(staff_name[i]);
        tv2.setText(area_name[i]);



        return gridView;
    }
}
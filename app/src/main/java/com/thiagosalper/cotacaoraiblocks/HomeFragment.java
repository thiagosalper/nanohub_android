package com.thiagosalper.cotacaoraiblocks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main2, container, false);

//        TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
//        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static HomeFragment newInstance(String text) {

        HomeFragment f = new HomeFragment();
        Bundle b = new Bundle();
       // b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
package com.aziaka.donavan.apptestsphere;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private int valueDegree;
    private int valueFahr;
    private int valueKelvin;
    private TextView nameView;
    private TextView cityView;
    private TextView tempView;
    private TabLayout tabs;


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        nameView = (TextView) view.findViewById(R.id.name_info);
        cityView = (TextView) view.findViewById(R.id.city_info);
        tempView = (TextView) view.findViewById(R.id.nbr_data);
        tabs = (TabLayout) view.findViewById(R.id.tabs);

        String city = cityView.getHint().toString() + " " + getArguments().getString("userCity");
        String name = nameView.getHint().toString() + " " +getArguments().getString("userName");

        System.out.println("Name of the city : " + city + "\nName of the user : " + name);

        nameView.setText(name);
        cityView.setText(city);
        tempView.setText("42°C");
        
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0)
                    tempView.setText("42°C");
                else if (tab.getPosition() == 1)
                    tempView.setText("42°F");
                else
                    tempView.setText("42°K");
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

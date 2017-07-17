package com.aziaka.donavan.apptestsphere;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AuthFragment extends Fragment {

    private String userName = "";
    private String userCity = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            final View view = inflater.inflate(R.layout.fragment_auth, container, false);
            final Button button = (Button) view.findViewById(R.id.validButton);
            final EditText name = (EditText) view.findViewById(R.id.username);
            final EditText city = (EditText) view.findViewById(R.id.usercity);

        button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    userCity = city.getText().toString();
                    userName = name.getText().toString();
                    if (!userName.isEmpty() && !userCity.isEmpty())
                    {
                        DetailFragment detail = new DetailFragment();
                        ((MainActivity)getActivity()).getJSON(userCity);
                        Bundle i = new Bundle();
                        i.putString("userName", userName);
                        i.putString("userCity", userCity);

                        detail.setArguments(i);

                        button.setVisibility(View.INVISIBLE);
                        name.setVisibility(View.INVISIBLE);
                        city.setVisibility(View.INVISIBLE);
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.detail_fragment, detail, "DetailFragmentTag");
                        ft.commit();
                    }
                    else
                    {
                        Snackbar snackbar = Snackbar
                                .make(view, getString(R.string.error_mess), Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.RED);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(Color.DKGRAY);
                        snackbar.show();
                    }
                }
            });
            return view;
        }

}

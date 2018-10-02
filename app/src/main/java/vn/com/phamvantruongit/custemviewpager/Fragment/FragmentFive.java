package vn.com.phamvantruongit.custemviewpager.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.com.phamvantruongit.custemviewpager.MainActivity;
import vn.com.phamvantruongit.custemviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFive extends Fragment {


    public FragmentFive() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        return view;

    }


}

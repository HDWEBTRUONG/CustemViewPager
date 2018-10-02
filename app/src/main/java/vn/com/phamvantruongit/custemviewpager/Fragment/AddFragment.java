package vn.com.phamvantruongit.custemviewpager.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.phamvantruongit.custemviewpager.AdapterView;
import vn.com.phamvantruongit.custemviewpager.AdapterViewPager;
import vn.com.phamvantruongit.custemviewpager.IndicatorView;
import vn.com.phamvantruongit.custemviewpager.PagerLessException;
import vn.com.phamvantruongit.custemviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements AdapterViewPager.OnClickPager {

    ViewPager viewPager;
    AdapterViewPager adapter;
    IndicatorView indicatorView;
    AdapterView adapterView;
    IOnClick iOnClick;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iOnClick= (IOnClick) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iOnClick= (IOnClick) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add, container, false);
        viewPager=view.findViewById(R.id.viewPager);
        indicatorView =view.findViewById(R.id.indicator);
        adapter=new AdapterViewPager(getContext(),this);
        viewPager.setAdapter(adapter);


        try {
            indicatorView.setViewPager(viewPager);
        } catch (PagerLessException e) {
            e.printStackTrace();
        }
       return view;
    }

    @Override
    public void onClick(int pos) {
       iOnClick.onClick(pos);
    }
    public interface IOnClick{
        void onClick(int pos);
    }
}

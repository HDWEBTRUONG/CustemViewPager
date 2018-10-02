package vn.com.phamvantruongit.custemviewpager.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.phamvantruongit.custemviewpager.CartActivity;
import vn.com.phamvantruongit.custemviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    IOnClickDetail clickDetail;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickDetail= (IOnClickDetail) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        clickDetail= (IOnClickDetail) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_detail, container, false);
      int pos=getArguments().getInt("pos",-1);
      view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             clickDetail.iOnClick();
          }
      });
      return view;
    }
    public interface IOnClickDetail{
        void iOnClick();
    }

}

package vn.com.phamvantruongit.custemviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.com.phamvantruongit.custemviewpager.Fragment.FragmentFive;
import vn.com.phamvantruongit.custemviewpager.Fragment.FragmentFour;
import vn.com.phamvantruongit.custemviewpager.Fragment.FragmentOne;
import vn.com.phamvantruongit.custemviewpager.Fragment.FragmentThree;
import vn.com.phamvantruongit.custemviewpager.Fragment.FragmentTwo;

public class AdapterView extends FragmentPagerAdapter {
    public AdapterView(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0 : fragment=new FragmentOne();break;
            case 1 : fragment=new FragmentTwo();break;
            case 2: fragment=new FragmentThree();break;
            case 3 :fragment=new FragmentFour() ;break;
            case 4 : fragment=new FragmentFive();break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}

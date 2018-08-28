package vn.com.phamvantruongit.mylibrary;

import android.support.v4.view.ViewPager;

public interface IndicatorInterface {
    void setViewPager(ViewPager viewPager) throws PagerLessException;
    void setRadiusSelected(int radius);
    void setRadiusUnSelected(int radius);
    void setDistanceDot(int radius);
}

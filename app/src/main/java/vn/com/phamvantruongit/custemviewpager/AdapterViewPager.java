package vn.com.phamvantruongit.custemviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class AdapterViewPager extends PagerAdapter {

    private Context mContext;
    OnClickPager onClickPager;

    public AdapterViewPager(Context context, OnClickPager onClickPager) {
        mContext = context;
        this.onClickPager=onClickPager;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.layout, container, false);

        if (position == 0) {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if (position == 1) {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else if (position == 2) {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if (position == 3) {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else if (position == 4) {
            layout.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        }
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPager.onClick(position);
            }
        });

        container.addView(layout);
        return layout;
    }
    public interface OnClickPager{
        void onClick(int pos);
    }
}

package vn.com.phamvantruongit.custemviewpager;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AdapterViewPager.OnClickPager{
    ViewPager viewPager;
    AdapterViewPager adapter;
    IndicatorView indicatorView;
    AdapterView adapterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewPager);
        indicatorView =findViewById(R.id.indicator);
        adapter=new AdapterViewPager(this ,this);
       // adapterView=new AdapterView(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        try {
            indicatorView.setViewPager(viewPager);
        } catch (PagerLessException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(int pos) {
        Intent intent=new Intent(this,ActivityDetail.class);
        startActivity(intent);
    }
}

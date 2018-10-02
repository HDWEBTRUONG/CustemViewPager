package vn.com.phamvantruongit.custemviewpager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import vn.com.phamvantruongit.custemviewpager.Fragment.AddFragment;
import vn.com.phamvantruongit.custemviewpager.Fragment.DetailFragment;
import vn.com.phamvantruongit.custemviewpager.Fragment.FragmentOne;


public class MainActivity extends AppCompatActivity implements AdapterViewPager.OnClickPager, AddFragment.IOnClick ,DetailFragment.IOnClickDetail{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main, new AddFragment()).commit();
    }

    @Override
    public void onClick(int pos) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, detailFragment).addToBackStack(null).commit();

    }

    @Override
    public void onBackPressed(){
        FragmentManager fm=getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void iOnClick() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, new FragmentOne()).addToBackStack(null).commit();
    }
}

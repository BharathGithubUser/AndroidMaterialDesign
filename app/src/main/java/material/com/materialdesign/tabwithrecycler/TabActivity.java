package material.com.materialdesign.tabwithrecycler;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import material.com.materialdesignexample.R;

public class TabActivity extends AppCompatActivity implements TabDataFromApi.SetTabData {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    int tabPosition;
    String getRecyclerTappeddata;
    int getRecyclerTappedPosition;
    List<Fragment> fragments;
    List<Fragment> currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initViews();
    }

    private void initViews() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        fragments = getFragments();
        tabLayout.addTab(tabLayout.newTab().setText("Data from api"));
        tabLayout.addTab(tabLayout.newTab().setText("Tapped Data"));
        tabLayout.addTab(tabLayout.newTab().setText("WebView"));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        Log.e("Position", String.valueOf(tabPosition));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("Postion tab", String.valueOf(tab.getPosition()));
                viewPager.setCurrentItem(tab.getPosition());
                tabPosition = tab.getPosition();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("", "on tab unselected team");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("", "on tab reselected team ");
            }
        });

        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    public void setTappedData(int position, String data) {
        this.getRecyclerTappeddata = data;
        this.getRecyclerTappedPosition = position;
        if (getRecyclerTappeddata != null && getRecyclerTappedPosition != 0) {
            currentFragment.add(TappedDataInFirstTab.createInstance(getRecyclerTappedPosition,getRecyclerTappeddata));
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
            viewPagerAdapter.notifyDataSetChanged();
            ViewGroup parent = (ViewGroup) viewPager.getParent();
            if (parent != null) {
                parent.removeView(viewPager);
            }
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setCurrentItem(1);

        }
    }

    public List<Fragment> getFragments() {
        currentFragment = new ArrayList<Fragment>();
        currentFragment.add(TabDataFromApi.createInstance());
        currentFragment.add(TabWebView.createInstance());
        return currentFragment;
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragment;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragment = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return this.fragment.get(position);
        }

        @Override
        public int getCount() {
            return this.fragment.size();
        }
    }
}

package material.com.materialdesign.tabwithrecycler;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import material.com.materialdesignexample.R;

public class TabActivity extends AppCompatActivity implements TabDataFromApi.setTabData {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    int tabPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initViews();
    }

    private void initViews() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("DataFromAPI"));
        tabLayout.addTab(tabLayout.newTab().setText("Tapped Data In FirstTab"));
        tabLayout.addTab(tabLayout.newTab().setText("WebViewTab"));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        Log.e("Position", String.valueOf(tabPosition));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("Postion tab", String.valueOf(tab.getPosition()));
                viewPager.setCurrentItem(tab.getPosition());
                tabPosition=tab.getPosition();


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
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    public void setTappedData(int position, Bundle bundle) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        int tabCount;

        public ViewPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TabDataFromApi();
                case 1:
                    return new TappedDataInFirstTab();
                case 2:
                    return new TabDataFromApi();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }
}

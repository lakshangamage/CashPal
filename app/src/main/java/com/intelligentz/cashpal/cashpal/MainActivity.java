package com.intelligentz.cashpal.cashpal;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_vertical_ntb);
        NavigationViewPageAdaptor navigationViewPageAdaptor = new NavigationViewPageAdaptor(getSupportFragmentManager());
        navigationViewPageAdaptor.addFragments(new CustomerTopUp(),"TopUp");
        navigationViewPageAdaptor.addFragments(new CustomerTopUp(),"DownBelow");
        navigationViewPageAdaptor.addFragments(new CustomerTopUp(),"DownUP");
        navigationViewPageAdaptor.addFragments(new CustomerTopUp(),"Downside");
        navigationViewPageAdaptor.addFragments(new CustomerTopUp(),"DownBlww");

        viewPager.setAdapter(navigationViewPageAdaptor);

        final String[] colors = getResources().getStringArray(R.array.vertical_ntb);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_vertical);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.top_up),
                        Color.parseColor(colors[0]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.top_up),
                        Color.parseColor(colors[0])).title("Top Up")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.top_up),
                        Color.parseColor(colors[0]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.top_up),
                        Color.parseColor(colors[0]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.top_up),
                        Color.parseColor(colors[0]))
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
    }
}

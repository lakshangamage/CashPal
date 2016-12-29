package com.intelligentz.cashpal.cashpal.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.gson.Gson;
import com.intelligentz.cashpal.cashpal.adaptor.AccountsRecyclerAdaptor;
import com.intelligentz.cashpal.cashpal.adaptor.NavigationViewPageAdaptor;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.model.Account;
import com.intelligentz.cashpal.cashpal.model.AccountDetail;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    View headerView;
    private RecyclerView accountsRecyclerView;
    private RecyclerView.LayoutManager accountslayoutManager;
    private Context context = this;
    private RecyclerView.Adapter accountAdapter;
    private CircularImageView imageView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView headeraccountname;
    private TextView headersubaccountid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        loadAccounts();
        configureDrawer();
        update();
    }

    private void loadAccounts() {
        for (int i=0; i<Account.accountDetailList.size(); i++) {
            AccountDetail acc = Account.accountDetailList.get(i);
            SharedPreferences mPrefs = context.getSharedPreferences(acc.getAccount_id(), Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString(Account.SUB_ACCOUNT_IDENTIFIER, "");
            if (json != null && !json.isEmpty()) {
                ArrayList<String> persistedList = gson.fromJson(json, ArrayList.class);
                if (persistedList != null && !persistedList.isEmpty()) {
                    acc.getSubAccoutList().clear();
                    for (String id : persistedList) {
                        acc.addSubAccountToList(id);
                    }
                }
            }
            Account.setCurrentAccount(acc);
        }
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
        viewPager.setOffscreenPageLimit(0);

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

    public void configureDrawer(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navogation_view);
        headerView = navigationView.inflateHeaderView(R.layout.drawerheader);
        imageView = (CircularImageView) headerView.findViewById(R.id.headerimage);
        headeraccountname = (TextView) headerView.findViewById(R.id.headeraccountname);
        headersubaccountid = (TextView) headerView.findViewById(R.id.headersubaccountid);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        configureAccountRecyclerView();
    }

    private void configureAccountRecyclerView(){
        accountsRecyclerView = (RecyclerView) headerView.findViewById(R.id.accountsrecyclervirew);
        accountslayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        accountsRecyclerView.setLayoutManager(accountslayoutManager);
        accountAdapter = new AccountsRecyclerAdaptor(Account.accountDetailList, context, this);
        accountsRecyclerView.setAdapter(accountAdapter);
    }

    public void update() {
        if (Account.getCurrentAccount() != null) {
            AccountDetail currentAccountDetail = Account.getCurrentAccount();
            imageView.setBackgroundResource(currentAccountDetail.getAccountIcon());
            headeraccountname.setText(currentAccountDetail.getAccountName());
            currentAccountDetail.getSubAccoutList();
            if (currentAccountDetail.getSubAccoutList() != null && !currentAccountDetail.getSubAccoutList().isEmpty()) {
                headersubaccountid.setText(currentAccountDetail.getSubAccoutList().get(
                        Account.getCurrentSubAccountIndex()));
            } else {

            }
        }
    }
}

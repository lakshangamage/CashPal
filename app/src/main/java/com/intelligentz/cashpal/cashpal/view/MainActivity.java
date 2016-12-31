package com.intelligentz.cashpal.cashpal.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
    private Menu drawerMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        configureDrawer();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_tool_bar, null);
        actionBar.setCustomView(v);
        actionBar.setTitle("Cash Pal");
    }



    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_vertical_ntb);
        NavigationViewPageAdaptor navigationViewPageAdaptor = new NavigationViewPageAdaptor(getSupportFragmentManager());
        navigationViewPageAdaptor.addFragments(new CustomerTopUp(),"TopUp");
        navigationViewPageAdaptor.addFragments(new CashWithdrawalFragment(),"DownBelow");
        navigationViewPageAdaptor.addFragments(new BalanceCheckFragment(),"DownUP");
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
                        getResources().getDrawable(R.drawable.cash_withdrawal_icon),
                        Color.parseColor(colors[0])).title("Top Up")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.payment_icon),
                        Color.parseColor(colors[0]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.balance_icon),
                        Color.parseColor(colors[0]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.summary),
                        Color.parseColor(colors[0]))
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
    }

    public void configureDrawer(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navogation_view);
        headerView = navigationView.inflateHeaderView(R.layout.drawerheader);
        imageView = (CircularImageView) headerView.findViewById(R.id.headerimage);
        headeraccountname = (TextView) headerView.findViewById(R.id.headeraccountname);
        headersubaccountid = (TextView) headerView.findViewById(R.id.headersubaccountid);
        navigationView.setItemIconTintList(null);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        configureAccountRecyclerView();

        if (Account.getCurrentAccount() != null) {
            AccountDetail currentAccountDetail = Account.getCurrentAccount();
            imageView.setImageResource(currentAccountDetail.getAccountIcon());
            headeraccountname.setText(currentAccountDetail.getAccountName());
            ArrayList<String> subAccounts = currentAccountDetail.getSubAccoutList();
            if (subAccounts != null && !subAccounts.isEmpty()) {
                headersubaccountid.setText(subAccounts.get(
                        Account.getCurrentSubAccountIndex()));
                drawerMenu = navigationView.getMenu();
                SubMenu accountMenu = drawerMenu.addSubMenu(Menu.NONE, 99, 0, "Accounts");
                for (int i = 0; i< subAccounts.size(); i++) {
                    accountMenu.add(Menu.NONE, i, i, subAccounts.get(i));
                }
            }
        }


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
            imageView.setImageResource(currentAccountDetail.getAccountIcon());
            headeraccountname.setText(currentAccountDetail.getAccountName());
            currentAccountDetail.getSubAccoutList();
            if (currentAccountDetail.getSubAccoutList() != null && !currentAccountDetail.getSubAccoutList().isEmpty()) {
                headersubaccountid.setText(currentAccountDetail.getSubAccoutList().get(
                        Account.getCurrentSubAccountIndex()));
            } else {

            }
        }
    }

    public void jumptolanguagechange(MenuItem item){
        Intent intent = new Intent(this, LanguageChangeActivity.class);
        startActivity(intent);
        finish();
    }

    public void logout(MenuItem item) {

    }

    public void jumptoaddaccout(MenuItem item) {

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}

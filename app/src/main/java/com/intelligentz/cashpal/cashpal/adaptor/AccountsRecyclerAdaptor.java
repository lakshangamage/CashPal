package com.intelligentz.cashpal.cashpal.adaptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;
import com.google.gson.Gson;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.model.Account;
import com.intelligentz.cashpal.cashpal.model.AccountDetail;
import com.intelligentz.cashpal.cashpal.model.Strings;
import com.intelligentz.cashpal.cashpal.view.MainActivity;

import java.util.ArrayList;

/**
 * Created by lakshan on 11/5/16.
 */
public class AccountsRecyclerAdaptor extends RecyclerView.Adapter<AccountsRecyclerAdaptor.RecyclerViewHolder> implements View.OnClickListener{
    ArrayList<AccountDetail> accountList = null;
    Context context = null;
    MainActivity activity;
    public AccountsRecyclerAdaptor(ArrayList<AccountDetail> accountList, Context context, MainActivity activity) {
        this.context = context;
        this.accountList = accountList;
        this.activity = activity;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accout_circle_view_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context, accountList);
        return recyclerViewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.accountImageView.setImageResource(accountList.get(position).getAccountIcon());
    }
    @Override
    public int getItemCount() {
        return accountList.size()-1;
    }
    @Override
    public void onClick(View view) {

    }

    public void removeItem(int position){
        AccountDetail newAccount = accountList.get(position);

        if (newAccount.getSubAccoutList() != null && !newAccount.getSubAccoutList().isEmpty()) {
            ArrayList<String> subAccountList = newAccount.getSubAccoutList();
                Account.setCurrentAccount(newAccount);
                Account.setCurrentSubAccountIndex(0);
                activity.switchAccount();
                return;
        } else {
            String msg = String.format(Strings.getNoAccountRegisteredMessage(),
                    newAccount.getAccountName());
            NiftyNotificationView.build(activity, msg, Effects.thumbSlider,R.id.mLyout)
                    .setIcon(R.drawable.cashpal_icon).show();
        }
    }

    public void changeAccount() {
        int position = accountList.indexOf(Account.getCurrentAccount());
        accountList.remove(Account.getCurrentAccount());
        accountList.add(Account.getCurrentAccount());
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,accountList.size()-1);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircularImageView accountImageView = null;
        ArrayList<AccountDetail> accountList = null;
        Context context = null;
        public RecyclerViewHolder(View itemView, Context context, ArrayList<AccountDetail> accountList) {
            super(itemView);
            this.context =context;
            this.accountList = accountList;
            accountImageView = (CircularImageView) itemView.findViewById(R.id.photoView);
            accountImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            removeItem(getAdapterPosition());
        }
    }
}

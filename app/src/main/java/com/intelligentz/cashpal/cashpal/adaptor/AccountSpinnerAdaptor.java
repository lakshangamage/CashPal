package com.intelligentz.cashpal.cashpal.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.intelligentz.cashpal.cashpal.R;
import com.intelligentz.cashpal.cashpal.model.Account;

import java.util.ArrayList;

/**
 * Created by lakshan on 12/30/16.
 */
public class AccountSpinnerAdaptor extends BaseAdapter {
    private Context context;
    public AccountSpinnerAdaptor(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return Account.accountDetailList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        ViewHolder viewHolder;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            itemView = inflater.inflate(R.layout.account_spinner_row, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (CircularImageView) itemView.findViewById(R.id.photoView);
            viewHolder.accountNameTxt = (TextView) itemView.findViewById(R.id.spinner_row_name);
            itemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) itemView.getTag();
        }
        viewHolder.imageView.setImageResource(Account.accountDetailList.get(i).getAccountIcon());
        viewHolder.accountNameTxt.setText(Account.accountDetailList.get(i).getAccountName());
        return itemView;
    }

    public static class ViewHolder {
        CircularImageView imageView;
        TextView accountNameTxt;
    }
}

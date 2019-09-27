package com.anjukakoralage.layoutindexdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anjukakoralage.layoutindexdemo.R;
import com.anjukakoralage.layoutindexdemo.model.UserDetails;
import com.anjukakoralage.layoutindexdemo.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anjukakoralage on 26,September,2019
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {

    private List<UserDetails> dataList;
    private Context context;

    public UserAdapter(Context context, List<UserDetails> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private final View mView;
        private TextView tvID;
        private TextView tvName;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvName = mView.findViewById(R.id.tvID);
            tvName = mView.findViewById(R.id.tvName);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.tvID.setText(dataList.get(position).getId());
        holder.tvName.setText(dataList.get(position).getFirst_name() + " " + dataList.get(position).getLast_name());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}


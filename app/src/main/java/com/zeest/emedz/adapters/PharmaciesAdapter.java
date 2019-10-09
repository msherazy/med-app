package com.zeest.emedz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeest.emedz.R;
import com.zeest.emedz.models.PharmaciesModel;

import java.util.List;

public class PharmaciesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PharmaciesModel> data;
    private Context mContext;

    public PharmaciesAdapter(List<PharmaciesModel> d, Context context) {
        this.mContext = context;
        this.data = d;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_pharmacies, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        PharmaciesModel current = data.get(i);

        myHolder.tvPharmacies.setText(current.getPharmacyName());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvPharmacies;

        MyHolder(View itemView) {
            super(itemView);
            tvPharmacies = itemView.findViewById(R.id.tvPharmacies);
        }

    }
}
package com.zeest.emedz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zeest.emedz.R;
import com.zeest.emedz.models.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context mContext;
    private List<Medicine> medicineListFiltered;
    private List<Medicine> medicineList;
    private MedicineAdapterListener medicineAdapterListener;

    public MedicineAdapter(List<Medicine> d, Context context, MedicineAdapterListener listener) {
        this.mContext = context;
        this.medicineAdapterListener = listener;
        this.medicineList = d;
        this.medicineListFiltered = d;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        return new MedicineAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myViewHolder, int i) {
        final MedicineAdapter.MyHolder myHolder = (MyHolder) myViewHolder;
        Medicine current = medicineListFiltered.get(i);

        myHolder.name.setText(current.getName());

        //setting image of the medicine
        Glide.with(mContext)
                .load(current.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(myHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return medicineListFiltered.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView thumbnail;

        MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            thumbnail = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    medicineAdapterListener.onMedicineSelected(medicineListFiltered.get(getAdapterPosition()));
                }
            });
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    medicineListFiltered = medicineList;
                } else {
                    List<Medicine> filteredList = new ArrayList<>();
                    for (Medicine row : medicineList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    medicineListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = medicineListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                medicineListFiltered = (ArrayList<Medicine>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface MedicineAdapterListener {
        void onMedicineSelected(Medicine medicine);
    }
}

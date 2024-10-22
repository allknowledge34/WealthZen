package com.sachin.wealthzen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.sachin.wealthzen.Activity.DetailActivity;
import com.sachin.wealthzen.Domain.DoctorsModel;
import com.sachin.wealthzen.databinding.ViewholderTopDoctorBinding;

import java.util.List;

public class TopDoctorAdapter extends RecyclerView.Adapter<TopDoctorAdapter.ViewHolder> {

    private List<DoctorsModel> items;
    private Context context;

    public TopDoctorAdapter(List<DoctorsModel> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewholderTopDoctorBinding binding;

        public ViewHolder(ViewholderTopDoctorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public TopDoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderTopDoctorBinding binding = ViewholderTopDoctorBinding.inflate(
                LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopDoctorAdapter.ViewHolder holder, int position) {
        holder.binding.nameTxt.setText(items.get(position).getName());
        holder.binding.specialTxt.setText(items.get(position).getSpecial());
        holder.binding.scoreTxt.setText(String.valueOf(items.get(position).getRating()));
        holder.binding.yearTxt.setText(String.valueOf(items.get(position).getExperience()) + " Years");

        Glide.with(holder.itemView.getContext())
                .load(items.get(position).getPicture())
                .apply(new RequestOptions().transform(new CenterCrop()))
                .centerCrop()
                .into(holder.binding.img);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", items.get(position));
            if (context != null){
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

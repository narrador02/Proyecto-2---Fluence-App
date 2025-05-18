package com.example.fluenceapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fluenceapp.R;
import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private final List<Company> companies;
    private final String userType;

    public ExploreAdapter(List<Company> companies, String userType) {
        this.companies = companies;
        this.userType = userType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.name.setText(company.name);
        holder.offersIndicator.setVisibility(company.hasOffers ? View.VISIBLE : View.GONE);

        if ("influencer".equalsIgnoreCase(userType)) {
            holder.followButton.setVisibility(View.VISIBLE);
            holder.notifyButton.setVisibility(View.VISIBLE);
        } else {
            holder.followButton.setVisibility(View.GONE);
            holder.notifyButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView offersIndicator;
        Button followButton, notifyButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.company_name);
            offersIndicator = itemView.findViewById(R.id.offers_indicator);
            followButton = itemView.findViewById(R.id.follow_button);
            notifyButton = itemView.findViewById(R.id.notify_button);
        }
    }

    public static class Company {
        public final String name;
        public final boolean hasOffers;

        public Company(String name, boolean hasOffers) {
            this.name = name;
            this.hasOffers = hasOffers;
        }
    }
}
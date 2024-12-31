package com.pim.planta.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pim.planta.R;

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.YearViewHolder> {

    private int currentYear;
    private OnYearSelectedListener listener;
    private int minimumYear;
    private int holderWidth;
    public static final int EMPTY_VIEW_TYPE = 0;
    public static final int YEAR_VIEW_TYPE = 1;

    public YearAdapter(int currentYear, OnYearSelectedListener listener, int minimumYear) {
        this.currentYear = currentYear;
        this.listener = listener;
        this.minimumYear = minimumYear;
    }

    @NonNull
    @Override
    public YearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.year_item, parent, false);
        return new YearViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YearViewHolder holder, int position) {
        if (getItemViewType(position) == YEAR_VIEW_TYPE) {
            int year = minimumYear + position - 2; // Adjust for empty spaces
            holder.yearTextView.setText(String.valueOf(year));}
        else {
            // Handle empty spaces (e.g., make the TextView invisible)
            holder.yearTextView.setVisibility(View.INVISIBLE);
            if (holderWidth > 0) { // Check if holderWidth is set
                holder.yearTextView.setWidth(holderWidth); // Use holderWidth from YearSelectorButton
            } else {
                // Handle case where holderWidth is not yet set (e.g., set a default width)
                holder.yearTextView.setWidth(holder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.year_selector_empty_space_width));
            }
        }
    }
    public void setHolderWidth(int holderWidth) {
        this.holderWidth = holderWidth;
        notifyDataSetChanged(); // Refresh the view
    }
    @Override
    public int getItemCount() {
        return (currentYear - minimumYear + 1) + 4; // Total items including 4 empty spaces
    }
    @Override
    public int getItemViewType(int position) {
        if (position < 2 || position >= getItemCount() - 2) { // Check for 2 empty spaces on each side
            return EMPTY_VIEW_TYPE; // Empty space view type
        } else {
            return YEAR_VIEW_TYPE; // Year view type
        }
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    static class YearViewHolder extends RecyclerView.ViewHolder {
        TextView yearTextView;

        YearViewHolder(View itemView) {
            super(itemView);
            yearTextView = itemView.findViewById(R.id.year_text_view);
        }
    }

    public interface OnYearSelectedListener {
        void onYearSelected(int year);
    }
}
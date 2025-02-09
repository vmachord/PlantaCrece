package com.pim.planta.models;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pim.planta.R;

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.YearViewHolder> {

    public int currentYear;
    private int minimumYear;
    public int holderWidth;
    public static final int EMPTY_VIEW_TYPE = 0;
    public static final int YEAR_VIEW_TYPE = 1;

    public YearAdapter(int currentYear, int minimumYear) {
        this.currentYear = currentYear;
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
            int year = minimumYear + position - 4; // Adjust for empty spaces
            holder.yearTextView.setText(String.valueOf(year));
            if (year == currentYear) {
                // Selected year
                holder.yearTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24); // Larger text size
                holder.yearTextView.setTextColor(Color.WHITE); // White text color
            } else {
                // Other years
                holder.yearTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16); // Smaller text size
                holder.yearTextView.setTextColor(Color.LTGRAY); // Light gray text color
            }
        }
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
        return (currentYear - minimumYear + 1) + 8; // Total items including 4 empty spaces
    }
    @Override
    public int getItemViewType(int position) {
        if (position < 4 || position >= getItemCount() - 4) { // Check for 2 empty spaces on each side
            return EMPTY_VIEW_TYPE; // Empty space view type
        } else {
            return YEAR_VIEW_TYPE; // Year view type
        }
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
        notifyDataSetChanged();
    }

    public static class YearViewHolder extends RecyclerView.ViewHolder {
        public TextView yearTextView;

        YearViewHolder(View itemView) {
            super(itemView);
            yearTextView = itemView.findViewById(R.id.year_text_view);
        }
    }
}
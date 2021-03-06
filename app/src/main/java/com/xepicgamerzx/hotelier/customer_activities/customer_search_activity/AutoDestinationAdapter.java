package com.xepicgamerzx.hotelier.customer_activities.customer_search_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.api.PlacesAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for destination auto complete
 */
public class AutoDestinationAdapter extends ArrayAdapter<DestinationItem> implements Filterable {
    private final OnSearchClick searchCallback;
    PlacesAPI placeApi = new PlacesAPI();
    // Search filter logic
    private final Filter destinationFilter = new Filter() {
        /**
         * Filter for places
         *
         * @param constraint Text being typed in
         * @return FilterResults filter results
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            List<DestinationItem> suggestions = new ArrayList<>();

            if (constraint != null) {
                List<DestinationItem> destinationsListFull = placeApi.autoComplete(constraint.toString());
                suggestions.addAll(destinationsListFull);
            }
            filterResults.values = suggestions;
            filterResults.count = suggestions.size();

            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);

            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

        /**
         * Convert resulting destination to character sequence
         *
         * @param resultValue the string that is placed in the search bar when selected.
         * @return CharSequence of destination
         */
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            DestinationItem destinationItem = ((DestinationItem) resultValue);
            // Sends the destination item to SearchActivity
            searchCallback.onSearch(destinationItem);

            return ((DestinationItem) resultValue).getCityStateCountry();
        }
    };
    Context context;

    public AutoDestinationAdapter(@NonNull Context context, OnSearchClick listener) {
        super(context, 0);
        this.context = context;
        this.searchCallback = listener;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return destinationFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.destination_autocomplete_row, parent, false
            );
        }
        TextView textViewCSC = convertView.findViewById(R.id.cityStateCountry);
        DestinationItem destinationItem = getItem(position);

        if (destinationItem != null) {
            textViewCSC.setText(destinationItem.getCityStateCountry());
        }

        return convertView;
    }
}

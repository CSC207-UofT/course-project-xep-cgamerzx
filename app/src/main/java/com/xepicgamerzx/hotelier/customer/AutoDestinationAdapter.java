package com.xepicgamerzx.hotelier.customer;

import android.content.Context;
import android.content.Intent;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AutoDestinationAdapter extends ArrayAdapter<DestinationItem> implements Filterable {
    private List<DestinationItem> destinationsListFull;
    private OnSearchClick searchCallback;

    PlacesAPI placeApi = new PlacesAPI();
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

    // Search filter logic
    private Filter destinationFilter = new Filter() {
        /**
         *
         * @param constraint Text being typed in
         * @return
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            List<DestinationItem> suggestions = new ArrayList<>();

            if (constraint != null) {
                destinationsListFull = placeApi.autoComplete(constraint.toString());

                suggestions.addAll(destinationsListFull);
            }
            filterResults.values = suggestions;
            filterResults.count = suggestions.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);

            if(results != null && results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

        /**
         *
         * @param resultValue the string that is placed in the search bar when selected.
         * @return
         */
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            DestinationItem destinationItem = ((DestinationItem) resultValue);
            // Sends to SearchActivity
            searchCallback.onSearch(destinationItem);

            return ((DestinationItem) resultValue).getCityStateCountry();
        }
    };
}

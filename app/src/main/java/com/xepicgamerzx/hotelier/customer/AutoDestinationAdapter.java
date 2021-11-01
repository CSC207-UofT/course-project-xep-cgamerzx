package com.xepicgamerzx.hotelier.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xepicgamerzx.hotelier.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AutoDestinationAdapter extends ArrayAdapter<DestinationItem> {
    private List<DestinationItem> destinationsListFull;

    public AutoDestinationAdapter(@NonNull Context context, @NonNull List<DestinationItem> destinationsList) {
        super(context, 0, destinationsList);

        destinationsListFull = new ArrayList<>(destinationsList); // Creates new ArrayList with DL
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

        TextView textViewCountry = convertView.findViewById(R.id.countryTxt);
        TextView textViewCity = convertView.findViewById(R.id.cityTxt);
        TextView textViewState = convertView.findViewById(R.id.stateTxt);

        DestinationItem destinationItem = getItem(position);


        if (destinationItem != null) {
            String city = destinationItem.getCity() + ", ";
            String state = destinationItem.getState() + ", ";
            textViewCountry.setText(destinationItem.getCountry());
            textViewCity.setText(city);
            textViewState.setText(state);
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
            FilterResults results = new FilterResults();
            List<DestinationItem> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(destinationsListFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                System.out.println(filter);
                for (DestinationItem item : destinationsListFull) {
                    if(item.getCity().toLowerCase().contains(filter)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll(((List) results.values));
            notifyDataSetChanged();
        }

        /**
         *
         * @param resultValue the string that is placed in the search bar when selected.
         * @return
         */
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((DestinationItem) resultValue).getDestination();
        }
    };
}

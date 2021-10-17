package com.xepicgamerzx.hotelier.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;

import java.util.ArrayList;
import java.util.List;

public class HotelListActivity extends AppCompatActivity {
    private ListView hotelListView;
    private HotelManager hotelManager;

    // Dummy data
    //    String names[] = {"Hotel1", "Hotel2", "Hotel3"};
    //    String address[] = {"123 Testing Lane", "124 Testing Lane", "125 Testing Lane"};
    //    String priceRange[] = {"$300 - $500", "$250 - $350", "$500 - $600"};
    //    String numberOfRooms[] = {"4", "3", "10"};

    List<HotelViewModel> listHotels = new ArrayList<>();
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        // Getting the HotelManager passed from ActivityMain.
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            System.out.println("HotelManager Received");
            hotelManager = (HotelManager) intent.getSerializableExtra("HotelManager");
        }


        hotelListView = findViewById(R.id.hotelListView);
        List<Hotel> hotels = hotelManager.getAllHotels();
        System.out.println(hotels);


        for (Hotel hotel : hotels) {
            HotelViewModel hotelModel = new HotelViewModel(
                    hotel.getName(), hotel.getAddress().getFullStreet(),
                    hotelManager.hotelPriceRangeString(hotel),
                    Integer.toString(hotel.getNumberOfRooms())
            );

            System.out.println(hotelModel);
            listHotels.add(hotelModel);
        }

        customAdapter = new CustomAdapter(listHotels, this);

        hotelListView.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hotel_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.searchView) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class CustomAdapter extends BaseAdapter implements Filterable {
        private List<HotelViewModel> listHotels;
        private List<HotelViewModel> listHotelsFiltered;
        private Context context;

        public CustomAdapter(List<HotelViewModel> listHotels, Context context) {
            this.listHotels = listHotels;
            this.listHotelsFiltered = listHotels;
            this.context = context;
        }

        @Override
        public int getCount() {
            return listHotelsFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.hotel_row_item, null);

            TextView hotelName = view.findViewById(R.id.listHotelName);
            TextView hotelAddress = view.findViewById(R.id.listHotelAddress);
            TextView hotelNumRooms = view.findViewById(R.id.hotelNumRooms);
            TextView hotelPriceRange = view.findViewById(R.id.listHotelPriceRange);

            hotelName.setText(listHotelsFiltered.get(position).getName());
            hotelAddress.setText(listHotelsFiltered.get(position).getAddress());
            hotelPriceRange.setText(listHotelsFiltered.get(position).getPriceRange());
            hotelNumRooms.setText("Rooms: " + listHotelsFiltered.get(position).getNumberOfRooms());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HotelListActivity.this, CustomerHotelRoomsActivity.class).putExtra("Hotel", listHotelsFiltered.get(position)));
                }
            });

            return view;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();

                    // POSSIBLY ADD THIS IN THE CUSTOMER FILTER CLASS?
                    if (constraint == null || constraint.length() == 0) {
                        filterResults.count = listHotels.size();
                        filterResults.values = listHotels;

                    } else {
                        String searchStr = constraint.toString().toLowerCase();

                        List<HotelViewModel> resultData = new ArrayList<>();

                        for (HotelViewModel hotelViewModel : listHotels) {
                            if (hotelViewModel.getName().toLowerCase().contains(searchStr) ||
                                    hotelViewModel.getAddress().toLowerCase().contains(searchStr)) {
                                resultData.add(hotelViewModel);
                            }
                        }

                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    listHotelsFiltered = (List<HotelViewModel>) results.values;

                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}
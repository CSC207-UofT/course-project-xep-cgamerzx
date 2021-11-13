package com.xepicgamerzx.hotelier.storage.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xepicgamerzx.hotelier.objects.OldObjects.HotelOld;
import com.xepicgamerzx.hotelier.objects.OldObjects.OldHotelManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelOldDAO {
    private DatabaseReference databaseReference;
    List data = new ArrayList<>();

    public HotelOldDAO() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(HotelOld.class.getSimpleName());
    }

    public Task<Void> add(HotelOld hotel) {
        // if (hotel == null) ...

        return databaseReference.push().setValue(hotel);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }

    public void read(MyCallback myCallback) {
        OldHotelManager ohm = new OldHotelManager();
        FirebaseDatabase.getInstance().getReference().child("HotelOld")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnap : snapshot.getChildren()) {
                            HotelOld hotel = dataSnap.getValue(HotelOld.class);
                            myCallback.onCallback(hotel);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}

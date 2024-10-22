package com.sachin.wealthzen.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sachin.wealthzen.Domain.CategoryModel;
import com.sachin.wealthzen.Domain.DoctorsModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private final MutableLiveData<List<CategoryModel>> _category = new MutableLiveData<>();
    public LiveData<List<CategoryModel>> getCategory() {
        return _category;
    }

    private final MutableLiveData<List<DoctorsModel>> _doctors = new MutableLiveData<>();
    public LiveData<List<DoctorsModel>> getDoctors() {
        return _doctors;
    }

    public void loadCategory() {
        firebaseDatabase.getReference("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<CategoryModel> lists = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    CategoryModel list = childSnapshot.getValue(CategoryModel.class);
                    if (list != null) {
                        lists.add(list);
                    }
                }
                _category.setValue(lists);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void loadDoctors() {
        firebaseDatabase.getReference("Doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<DoctorsModel> lists = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    DoctorsModel list = childSnapshot.getValue(DoctorsModel.class);
                    if (list != null) {
                        lists.add(list);
                    }
                }
                _doctors.setValue(lists);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("MainViewModel", "Error loading data: " + error.getMessage());
            }
        });
    }
}




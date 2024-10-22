package com.sachin.wealthzen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sachin.wealthzen.Adapter.CategoryAdapter;
import com.sachin.wealthzen.Adapter.TopDoctorAdapter;
import com.sachin.wealthzen.Domain.CategoryModel;
import com.sachin.wealthzen.Domain.DoctorsModel;
import com.sachin.wealthzen.ViewModel.MainViewModel;
import com.sachin.wealthzen.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initCategoryList();
        initTopDoctors();
    }

    private void initTopDoctors() {
        binding.progressBarTopServices.setVisibility(View.VISIBLE);
        viewModel.getDoctors().observe(this, new Observer<List<DoctorsModel>>() { // Corrected here
            @Override
            public void onChanged(List<DoctorsModel> doctors) {
                binding.recyclerViewTopServices.setLayoutManager(new LinearLayoutManager(
                        MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                binding.recyclerViewTopServices.setAdapter(new TopDoctorAdapter(doctors));
                binding.progressBarTopServices.setVisibility(View.GONE);
            }
        });
        viewModel.loadDoctors();

        binding.doctorListTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TopDoctorsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initCategoryList() {
        DatabaseReference myRef = firebaseDatabase.getReference("Category");

        binding.progressBarCategory.setVisibility(View.VISIBLE);

        ArrayList<CategoryModel> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        CategoryModel category = issue.getValue(CategoryModel.class);
                        if (category != null) {
                            list.add(category);
                        }
                    }
                    if (!list.isEmpty()) {
                        binding.viewCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        binding.viewCategory.setAdapter(new CategoryAdapter(list));
                        binding.progressBarCategory.setVisibility(View.GONE);
                    } else {
                        binding.progressBarCategory.setVisibility(View.GONE);
                    }
                } else {
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBarCategory.setVisibility(View.GONE);
            }
        });
    }

}






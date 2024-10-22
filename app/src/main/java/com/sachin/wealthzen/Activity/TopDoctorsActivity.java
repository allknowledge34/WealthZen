package com.sachin.wealthzen.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sachin.wealthzen.Adapter.TopDoctorAdapter2;
import com.sachin.wealthzen.Domain.DoctorsModel;
import com.sachin.wealthzen.ViewModel.MainViewModel;
import com.sachin.wealthzen.databinding.ActivityTopDoctorsBinding;

import java.util.List;

public class TopDoctorsActivity extends BaseActivity {
    private ActivityTopDoctorsBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopDoctorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initTopDoctors();

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTopDoctors() {
        binding.progressBarTopDoctor.setVisibility(View.VISIBLE);

        viewModel.getDoctors().observe(this, new Observer<List<DoctorsModel>>() {
            @Override
            public void onChanged(List<DoctorsModel> doctors) {
                binding.viewTopDoctorList.setLayoutManager(new LinearLayoutManager(
                        TopDoctorsActivity.this, LinearLayoutManager.VERTICAL, false));
                binding.viewTopDoctorList.setAdapter(new TopDoctorAdapter2(doctors));
                binding.progressBarTopDoctor.setVisibility(View.GONE);
            }
        });

        viewModel.loadDoctors();
    }
}


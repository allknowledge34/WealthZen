package com.sachin.wealthzen.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.sachin.wealthzen.Domain.DoctorsModel;
import com.sachin.wealthzen.R;
import com.sachin.wealthzen.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {

    private ActivityDetailBinding binding;
    private DoctorsModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
        getBundle();
    }

    private void setupListeners() {
        binding.messageBtn.setOnClickListener(v -> {
            Uri uri = Uri.parse("smsto:" + item.getMobile());
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "the SMS text");
            startActivity(intent);
        });

        binding.callBtn.setOnClickListener(v -> {
            String uri = "tel:" + item.getMobile().trim();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
            startActivity(intent);
        });


        binding.directionBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLocation()));
            startActivity(intent);
        });


        binding.shareBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, item.getName());
            intent.putExtra(Intent.EXTRA_TEXT, item.getName() + " " + item.getAddress() + " " + item.getMobile());
            startActivity(Intent.createChooser(intent, "Share via"));
        });

        binding.backBtn.setOnClickListener(v -> finish());

        binding.websiteBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(item.getSite()));
            startActivity(intent);
        });
    }

    private void getBundle() {
        item = getIntent().getParcelableExtra("object");

        binding.titleTxt.setText(item.getName());
        binding.specialTxt.setText(item.getSpecial());
        binding.patiensTxt.setText(item.getPatieng());
        binding.bioTxt.setText(item.getBiography());
        binding.addressTxt.setText(item.getAddress());
        binding.experienTxt.setText(item.getExperience() + " Years");
        binding.ratingTxt.setText(String.valueOf(item.getRating()));

        Glide.with(DetailActivity.this)
                .load(item.getPicture())
                .into(binding.img);
    }
}
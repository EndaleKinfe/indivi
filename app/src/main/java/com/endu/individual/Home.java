package com.endu.individual;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.endu.individual.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        String namef = intent.getStringExtra("fname");
        binding.lname.setText(intent.getStringExtra("lname"));
        binding.emailtv.setText(intent.getStringExtra("email"));
        binding.phonetv.setText(intent.getStringExtra("phone"));
        binding.bdtv.setText(intent.getStringExtra("bday"));
        binding.userimage.setImageURI(intent.getParcelableExtra("image"));
        binding.fname.setText(namef);
        Log.d("first name sent: ", namef);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.setting){
            startActivity(new Intent(this, Setting.class));
        } else if (item.getItemId() == R.id.account) {
            startActivity(new Intent(this, Account.class));
        }
        return false;
    }


    private void getDta(){

    }
}
package com.endu.individual;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.endu.individual.databinding.ActivityCreateBinding;

public class Create extends AppCompatActivity {
    private ActivityCreateBinding binding;
    private ActivityResultLauncher<Intent> resultLauncher;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        register();
        binding.imagePicker.setOnClickListener(v->selectImage());
        binding.btnregiter.setOnClickListener(v->{
            sendData();
        });

        binding.birthdate.setOnClickListener(v->{
            openDialog();
        });

    }


    private void selectImage(){
       Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
       resultLauncher.launch(intent);
    }

    private void register(){
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                try {
                    uri = o.getData().getData();

                } catch (Exception e) {
                    Toast.makeText(Create.this, "no image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                binding.birthdate.setText(String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(day));
            }
        }, 2025, 4, 12);
        dialog.show();
    }

    private void sendData(){
        Intent i = new Intent(Create.super.getBaseContext(), Home.class);
        i.putExtra("fname", binding.fnEt.getText().toString());
        i.putExtra("lname", binding.lnEt.getText().toString());
        i.putExtra("email", binding.emailEt.getText().toString());
        i.putExtra("phone", binding.phoneEt.getText().toString());
        i.putExtra("bday", binding.birthdate.getText().toString());
        i.putExtra("imageuri", uri);
        Log.d("first name sent: ", String.valueOf(binding.fnEt.getText()));
        startActivity(i);
    }


}
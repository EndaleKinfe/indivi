package com.endu.individual;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.endu.individual.databinding.ActivitySettingBinding;

import java.util.Locale;

public class Setting extends AppCompatActivity {
    private ActivitySettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.langugeTv.setOnClickListener(v->{
            String selected = String.valueOf(binding.langugeTv.getSelectedItem());
            if (selected.equals("Amharic")){
                setLanguage("am");
            } else if (selected.equals("English")) {
                setLanguage("en");
            }
        });
    }

    public void setLanguage(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor  editor = getSharedPreferences("LANG", MODE_PRIVATE).edit();
        editor.putString("language",language);
        editor.apply();
    }

    public void loadLanguage(){
        SharedPreferences sharedPreferences = getSharedPreferences("LANG", MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        setLanguage(language);
    }

}
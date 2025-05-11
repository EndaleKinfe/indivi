package com.endu.individual;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        binding.languageBtn.setOnClickListener(v->{
            openlanguagedialog();
        });




    }

    private void openlanguagedialog(){
        final String[] languageList = {"English", "Amharic"} ;
        SharedPreferences sharedPreferences = getSharedPreferences("LANG", MODE_PRIVATE);
        int item = sharedPreferences.getInt("item", 0);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.change));
        builder.setSingleChoiceItems(languageList, item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    setLanguage("en",0);
                    recreate();
                }
                else if (i == 1 ){
                    setLanguage("am",1);
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


//    public void languageOnClick(View view) {
////        String selected = String.valueOf(binding.langugeTv.getSelectedItem());
////        if (selected.equals("Amharic")){
//////                setLanguage("am");
////            Toast.makeText(this,"amaric",Toast.LENGTH_SHORT).show();
////        } else if (selected.equals("English")) {
//////                setLanguage("en");
////            Toast.makeText(this,"english",Toast.LENGTH_SHORT).show();
////        }
//    }
    public void setLanguage(String language, int item){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor  editor = getSharedPreferences("LANG", MODE_PRIVATE).edit();
        editor.putString("language",language);
        editor.putInt("item",item);
        editor.apply();
    }

    public void loadLanguage(){
        SharedPreferences sharedPreferences = getSharedPreferences("LANG", MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        int item = sharedPreferences.getInt("item", 0);
        setLanguage(language,item);
    }

}
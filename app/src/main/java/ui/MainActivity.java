package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;


import algonquin.cst2335.sava0184.R;
import algonquin.cst2335.sava0184.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());


        variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());

        });
        model.isSelected.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        model.editString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                variableBinding.textview.setText(" your Edit text has" + s);
            }
        });
        variableBinding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.isSelected.postValue(isChecked);
            }
        });
        variableBinding.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.isSelected.postValue(isChecked);
            }
        });
        variableBinding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.isSelected.postValue(isChecked);
            }
        });
        variableBinding.imageView.setImageResource(R.drawable.logo_algonquin);
        variableBinding.imageView.setOnClickListener(click ->
                Toast.makeText(this, "You click an Image Button", Toast.LENGTH_SHORT).show());
        Toast.makeText(this, "The width =" + variableBinding.imageView.getMeasuredWidth() + " and height =" + variableBinding.imageView.getMeasuredHeight(), Toast.LENGTH_SHORT).show();
    }

}
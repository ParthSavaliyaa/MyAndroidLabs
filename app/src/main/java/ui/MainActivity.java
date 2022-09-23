package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;



import algonquin.cst2335.sava0184.databinding.ActivityMainBinding;
import data.MainViewModel;

public  class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.myedittext.setText((CharSequence) model.editString);
        variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());

        });
        model.editString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                variableBinding.myedittext.setText( " your Edit text has" + model.editString);
            }
        });
    }

}
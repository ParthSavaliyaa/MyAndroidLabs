package algonquin.cst2335.sava0184;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import algonquin.cst2335.sava0184.databinding.ActivityMainBinding;

/**
 * @author Parth Savaliya
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    protected String cityName;
    protected RequestQueue queue=null;
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding= ActivityMainBinding.inflate( getLayoutInflater() );
        queue = Volley.newRequestQueue(this);


        binding.getForecast.setOnClickListener(click -> {
            cityName = binding.cityTextField.getText().toString();
            String stringURL="https://api.openweathermap.org/data/2.5/weather?q=cityName&appid={API key}";
            JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET,stringURL ,null,
                    (response) -> {},
                    (error) ->{ } );
            queue.add(request);


        });
    }
}
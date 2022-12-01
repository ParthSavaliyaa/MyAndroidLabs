package algonquin.cst2335.sava0184;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import algonquin.cst2335.sava0184.databinding.ActivityMainBinding;

/**
 * @author Parth Savaliya
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    protected String cityName;
    String stringURL;
    protected RequestQueue queue;
    Button getForecast;
    TextView temp;
    TextView minTemp, maxTemp, humidity, description;
    EditText cityTextField;
    ImageView icon;
    Bitmap image;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getForecast = findViewById(R.id.getForecast);
        temp = findViewById(R.id.temp);
        minTemp = findViewById(R.id.minTemp);
        maxTemp = findViewById(R.id.maxTemp);
        description = findViewById(R.id.description);
        humidity = findViewById(R.id.humidity);
        cityTextField = findViewById(R.id.cityTextField);
        icon = findViewById(R.id.icon);


        ActivityMainBinding binding;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        queue = Volley.newRequestQueue(this);


        getForecast.setOnClickListener(click -> {

            cityName = cityTextField.getText().toString();

            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                        + URLEncoder.encode(cityName, "UTF-8")
                        + "&appid=7e943c97096a9784391a981c4d878b22&units=metric";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {
                        try {
                            JSONObject coord = response.getJSONObject("coord");
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject position0 = weatherArray.getJSONObject(0);

                            String Description = position0.getString("description");
                            String iconName = position0.getString("icon");

                            String imageUrl = "https://openweathermap.org/img/w/" + iconName + ".png";
                            JSONObject mainObject = response.getJSONObject("main");
                            double current = mainObject.getDouble("temp");
                            double mintemp = mainObject.getDouble("temp_min");
                            double maxtemp = mainObject.getDouble("temp_max");
                            int Humidity = mainObject.getInt("humidity");
                            int vis = response.getInt("visibility");
                            String name = response.getString("name");
                            String pathname = getFilesDir() + "/" + iconName + ".png";
                            File file = new File(pathname);


                            if (file.exists()) {
                                image = BitmapFactory.decodeFile(pathname);
                            }
                            ImageRequest imgReq = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap bitmap) {
                                    try {
                                        //fOut = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                                        image = bitmap;
                                        image.compress(Bitmap.CompressFormat.PNG, 100,
                                                MainActivity.this.openFileOutput(iconName + ".png",
                                                        Activity.MODE_PRIVATE));


                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }, 1024, 1024, ImageView.ScaleType.CENTER, null, (error) -> {
                            });

                            queue.add(imgReq);
                            runOnUiThread(() -> {
                                temp.setText("The current temp is " + current+"Celcius");
                                temp.setVisibility(View.VISIBLE);
                                minTemp.setText("The min temperature is " + mintemp+"celcius");
                                minTemp.setVisibility(View.VISIBLE);
                                maxTemp.setText("The max temp is " + maxtemp+"celcisus");
                                maxTemp.setVisibility(View.VISIBLE);
                                humidity.setText("The humidity is " + Humidity+"%");
                                humidity.setVisibility(View.VISIBLE);
                                icon.setImageBitmap(image);
                                icon.setVisibility(View.VISIBLE);
                                description.setText(Description);
                                description.setVisibility(View.VISIBLE);
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();

                        } catch (Throwable e) {
                            e.printStackTrace();
                        }


                    },
                    (error) -> {
                    });
            queue.add(request);


        });

    }

}

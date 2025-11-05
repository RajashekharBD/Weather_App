package com.example.weather_app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView cityName;
    Button search;
    TextView show;
    String url;

    class getWeather extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                while ((line= reader.readLine()) != null){
                    result.append(line).append("\n");
                }
                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
                show.setText("Cannot able to find weather");
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject main = jsonObject.getJSONObject("main");

                double tempKelvin = main.getDouble("temp");
                double feelsLikeKelvin = main.getDouble("feels_like");
                double tempMinKelvin = main.getDouble("temp_min");
                double tempMaxKelvin = main.getDouble("temp_max");

                double tempCelsius = tempKelvin - 273.15;
                double feelsLikeCelsius = feelsLikeKelvin - 273.15;
                double tempMinCelsius = tempMinKelvin - 273.15;
                double tempMaxCelsius = tempMaxKelvin - 273.15;

                int humidity = main.getInt("humidity");

                String weatherInfo = "Temperature: " + String.format("%.2f", tempCelsius) + "°C\n" +
                                     "Feels Like: " + String.format("%.2f", feelsLikeCelsius) + "°C\n" +
                                     "Temperature_min: " + String.format("%.2f", tempMinCelsius) + "°C\n" +
                                     "Temperature_max: " + String.format("%.2f", tempMaxCelsius) + "°C\n" +
                                     "Humidity: " + humidity + "%";

                show.setText(weatherInfo);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Error parsing weather data", Toast.LENGTH_SHORT).show();
                show.setText("Could not parse weather data.");
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = findViewById(R.id.cityName);
        search = findViewById(R.id.search);
        show = findViewById(R.id.weather);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Toast.makeText(MainActivity.this,"Button Clicked! ",Toast.LENGTH_SHORT).show();
                   String city = cityName.getText().toString().trim();
                   if (!city.isEmpty()) {
                       url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=89618ec7ca1cac7054d5e841c0ee5048";
                       new getWeather().execute(url);
                   } else {
                       Toast.makeText(MainActivity.this,"Please Enter City Name",Toast.LENGTH_SHORT).show();
                   }
            }
        });

    }
}

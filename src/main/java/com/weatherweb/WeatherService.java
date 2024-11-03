package com.weatherweb;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherService {
    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org").build();
    }

    public WeatherResponse getWeather(String city) {
        String apiKey = "3bc1ad119feb8c035b95be5e32f0d0d0"; // Thay bằng API key

        try {
            // Lấy dữ liệu thời tiết hiện tại
            String currentWeatherResponse = this.webClient.get()
                    .uri("/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey + "&lang=vi")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JSONObject responseJson = new JSONObject(currentWeatherResponse);
            if (responseJson.has("cod") && responseJson.getInt("cod") != 200) {
                throw new IllegalArgumentException("City not found");
            }

            // Lấy dữ liệu dự báo
            String forecastResponse = this.webClient.get()
                    .uri("/data/2.5/forecast?q=" + city + "&units=metric&appid=" + apiKey + "&lang=vi")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            WeatherResponse weather = parseCurrentWeather(currentWeatherResponse);
            weather.setDailyForecasts(parseDailyForecast(forecastResponse));

            return weather;

        } catch (WebClientResponseException.NotFound e) {
            throw new IllegalArgumentException("City not found");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    
    private WeatherResponse parseCurrentWeather(String response) {
        JSONObject json = new JSONObject(response);
        WeatherResponse weather = new WeatherResponse();
        weather.setCityName(json.getString("name"));
        weather.setCountryCode(json.getJSONObject("sys").getString("country"));
        weather.setTemperature(json.getJSONObject("main").getDouble("temp"));
        weather.setHumidity(json.getJSONObject("main").getInt("humidity"));
        weather.setPressure(json.getJSONObject("main").getDouble("pressure"));
        weather.setWindSpeed(json.getJSONObject("wind").getDouble("speed"));
        weather.setWindDirection(json.getJSONObject("wind").getInt("deg"));
        weather.setDescription(json.getJSONArray("weather").getJSONObject(0).getString("description"));
        weather.setIcon(json.getJSONArray("weather").getJSONObject(0).getString("icon"));
        weather.setSunrise(json.getJSONObject("sys").getLong("sunrise"));
        weather.setSunset(json.getJSONObject("sys").getLong("sunset"));
        weather.setVisibility(json.getInt("visibility")/ 1000);
        return weather;
    }

    private List<WeatherResponse.DailyForecast> parseDailyForecast(String response) {
        List<WeatherResponse.DailyForecast> dailyForecasts = new ArrayList<>();
        JSONObject json = new JSONObject(response);
        JSONArray list = json.getJSONArray("list");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.length(); i += 8) {
            JSONObject dailyData = list.getJSONObject(i);
            WeatherResponse.DailyForecast dailyForecast = new WeatherResponse.DailyForecast();
            dailyForecast.setDate(sdf.format(new Date(dailyData.getLong("dt") * 1000)));
            dailyForecast.setMaxTemp(dailyData.getJSONObject("main").getDouble("temp_max"));
            dailyForecast.setMinTemp(dailyData.getJSONObject("main").getDouble("temp_min"));
            dailyForecast.setAverageHumidity(dailyData.getJSONObject("main").getInt("humidity"));
            dailyForecast.setDailyDescription(dailyData.getJSONArray("weather").getJSONObject(0).getString("description"));
            dailyForecast.setDailyIcon(dailyData.getJSONArray("weather").getJSONObject(0).getString("icon"));
            dailyForecast.setVisibility(dailyData.optDouble("visibility", 0) / 1000.0);
            
            dailyForecasts.add(dailyForecast);
        }

        return dailyForecasts;
    }
}

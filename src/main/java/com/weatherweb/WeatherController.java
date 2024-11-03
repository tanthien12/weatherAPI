package com.weatherweb;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam(defaultValue = "Hanoi") String city, Model model) {
        try {
            WeatherResponse weather = weatherService.getWeather(city);
            model.addAttribute("cityName", weather.getCityName());
            model.addAttribute("countryCode", weather.getCountryCode());
            model.addAttribute("temperature", weather.getTemperature());
            model.addAttribute("humidity", weather.getHumidity());
            model.addAttribute("pressure", weather.getPressure());
            model.addAttribute("windSpeed", weather.getWindSpeed());
            model.addAttribute("windDirection", weather.getWindDirection());
            model.addAttribute("description", weather.getDescription());
            model.addAttribute("icon", weather.getIcon());
            model.addAttribute("sunrise", weather.getSunrise());
            model.addAttribute("sunset", weather.getSunset());
            model.addAttribute("visibility", weather.getVisibility());
            model.addAttribute("dailyForecasts", weather.getDailyForecasts());
        } catch (IllegalArgumentException e) {
        	// Thông báo lỗi khi nhập sai dữ liệu tìm kiếm, và reset cách giá trị về 0
        	model.addAttribute("cityName", city);
        	model.addAttribute("countryCode", "N/A");
            model.addAttribute("temperature", 0);
            model.addAttribute("humidity", 0);
            model.addAttribute("pressure", 0);
            model.addAttribute("windSpeed", 0);
            model.addAttribute("windDirection", 0);
            model.addAttribute("description", "N/A");
            model.addAttribute("icon", "01d"); // Mặc định một biểu tượng thời tiết nếu không tìm thấy
            model.addAttribute("sunrise", 0);
            model.addAttribute("sunset", 0);
            model.addAttribute("visibility", 0);
            model.addAttribute("dailyForecasts", Collections.emptyList());
            model.addAttribute("error", "Không tìm thấy tên thành phố. Vui lòng kiểm tra và nhập lại."); // Thông báo lỗi cho Thymeleaf
        } catch (Exception e) {
        	model.addAttribute("error", "Không thể lấy dữ liệu thời tiết do lỗi hệ thống.");
            e.printStackTrace();
        }
        return "weather";
    }
    

}


package com.weatherweb;

import java.util.List;

public class WeatherResponse {
    private String cityName;
    private String countryCode;
    private double temperature;
    private int humidity;
    private double pressure;
    private double windSpeed;
    private double visibility;
    private int windDirection;
    private String description;
    private String icon; 
    private List<DailyForecast> dailyForecasts; 
    private long sunrise; 
    private long sunset; 

    // Getters và Setters

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public double getVisibility() {
		return visibility;
	}

	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}

	public static class DailyForecast {
        private String date;
        private double maxTemp;
        private double minTemp;
        private int averageHumidity;
        private String dailyDescription;
        private String dailyIcon;
        private double visibility;

        // Getters và Setters

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(double maxTemp) {
            this.maxTemp = maxTemp;
        }

        public double getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(double minTemp) {
            this.minTemp = minTemp;
        }

        public int getAverageHumidity() {
            return averageHumidity;
        }

        public void setAverageHumidity(int averageHumidity) {
            this.averageHumidity = averageHumidity;
        }

        public String getDailyDescription() {
            return dailyDescription;
        }

        public void setDailyDescription(String dailyDescription) {
            this.dailyDescription = dailyDescription;
        }

        public String getDailyIcon() {
            return dailyIcon;
        }

        public void setDailyIcon(String dailyIcon) {
            this.dailyIcon = dailyIcon;
        }

		public double getVisibility() {
			return visibility;
		}

		public void setVisibility(double visibility) {
			this.visibility = visibility;
		}
    }
}


package com.finalproject.aimage.weather;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {

	private final WeatherInfo weatherInfo;
	private WeatherVo weatherVo;
	
	public WeatherService(WeatherInfo weatherInfo)
	{
		this.weatherInfo = weatherInfo;
	}
	
	public void requestWeatherInfo(double latitude, double longitude)
	{
		while(true)
		{
			weatherVo = weatherInfo.getWeatherInfo(latitude, longitude);

			if(!weatherVo.isSucceed())
				continue;
			else
				return;
		}
	}
	
	public WeatherVo getWeatherVo()
	{
		return weatherVo;
	}
}

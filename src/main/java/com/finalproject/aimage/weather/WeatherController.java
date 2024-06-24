package com.finalproject.aimage.weather;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {
	
	private final WeatherService weatherService;
	
	public WeatherController(WeatherService weatherService)
	{
		this.weatherService = weatherService;
	}
	
	@RequestMapping(value = "/getLoc", method = RequestMethod.POST)
	@ResponseBody
	public String getWeatherInfo(@RequestParam String lati, @RequestParam String longi)
	{
		double latitude = Double.parseDouble(lati);
		double longitude = Double.parseDouble(longi);
		
		weatherService.requestWeatherInfo(latitude, longitude);
		WeatherVo vo = weatherService.getWeatherVo();
		
		//System.out.println(lati + ", " + longi);
		
		System.out.println(vo.getTodayWeather());
		
		return vo.getWeather().toString();
	}

}

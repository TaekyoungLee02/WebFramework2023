package com.finalproject.aimage.weather;

import java.util.HashSet;
import java.util.Set;

public class WeatherVo {
	
	private boolean succeed;
	private String todayWeather;
	private Set<String> strs;
	private Weather weather;
	private String season;
	
	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public String getTodayWeather() {
		return todayWeather;
	}

	public void setTodayWeather(String todayWeather) {
		this.todayWeather = todayWeather;
	}

	public Set<String> getStrs() {
		return strs;
	}

	public void setStrs(Set<String> strs) {
		this.strs = strs;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public WeatherVo()
	{
		strs = new HashSet<String>();
		succeed = true;
	}
}

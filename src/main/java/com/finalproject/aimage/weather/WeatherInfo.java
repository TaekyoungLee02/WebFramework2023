package com.finalproject.aimage.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.finalproject.aimage.HomeController;

@Component
public class WeatherInfo {
	
	private LocalDateTime now;
	private JSONParser jsonParser;
	
	private JSONObject weaInfo;
	private JSONObject[] items;
	
	private String baseDate;
	private String baseTime; 
	private String fcstTime;
	
	private String nx;
	private String ny;
	
	Set<String> strs;
	Weather weather;
	
	public WeatherInfo()
	{
		strs = new HashSet<String>();
		jsonParser = new JSONParser();
	}
	
	public WeatherVo getWeatherInfo(double latitude, double longitude)
	{
		WeatherVo vo = new WeatherVo();
		
		try
		{
			now = LocalDateTime.now();
			setTime();
			
			dfToxy(latitude, longitude);
			
			URL url = urlBuild("D5%2FHNUzmp5SEckP45BKuDYS%2FziVHdxE5h9XaE5Iw31Md6OzQJoNwqLBs2Kimy3mfSHiT9IFrEm5u61aFoTW9dA%3D%3D",
					baseDate, baseTime, nx, ny);
			
			weaInfo = request(url);
			items = weatherItems();
		}
		catch(Exception e)
		{
			HomeController.logger.error(e.getMessage());
			
			vo.setSucceed(false);
			return vo;
		}
		
		vo.setSeason(setSeason());
		vo.setTodayWeather(nowWeather());
		vo.setStrs(strs);
		vo.setWeather(weather);
		
		return vo;
	}
	
	private URL urlBuild(String serviceKey, String nowDate, String nowHour, String nx, String ny) throws IOException 
	{
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(nowDate, "UTF-8")); /*년월일*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(nowHour, "UTF-8")); /*정시단위*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        
        return url;
	}
	
	private void setTime()
	{
		if(now.getMinute() < 40)
		{		
			if(now.getHour() == 0)
			{
				now = now.minusDays(1);
				now = now.withHour(23);
			}
			else
			{
				now = now.minusHours(1);
			}
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		baseDate = now.format(formatter);
		
		formatter = DateTimeFormatter.ofPattern("HH");
		baseTime = now.format(formatter) + "30";
		
		now = now.plusHours(1);
		formatter = DateTimeFormatter.ofPattern("HH");
		fcstTime = now.format(formatter) + "00";
	}
	
	private JSONObject request(URL url) throws IOException, ParseException
	{
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        //System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        Object obj = jsonParser.parse(sb.toString());
        
        rd.close();
        conn.disconnect();
        
        return (JSONObject)obj;
	}
	
	private void dfToxy(double latitude, double longitude) {
		double RE = 6371.00877;     // 지구 반경(km)
		double GRID = 5.0;          // 격자 간격(km)
		double SLAT1 = 30.0;        // 투영 위도1(degree)
		double SLAT2 = 60.0;        // 투영 위도2(degree)
		double OLON = 126.0;        // 기준점 경도(degree)
		double OLAT = 38.0;         // 기준점 위도(degree)
		double XO = 43;             // 기준점 X좌표(GRID)
		double YO = 136;            // 기준점 Y좌표(GRID)
		double DEGRAD = Math.PI / 180.0;
		double re = RE / GRID;
		double slat1 = SLAT1 * DEGRAD;
		double slat2 = SLAT2 * DEGRAD;
		double olon = OLON * DEGRAD;
		double olat = OLAT * DEGRAD;
	
	    double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
	    
		double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
	    
	    double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
	    ro = re * sf / Math.pow(ro, sn);
	
	    var ra = Math.tan(Math.PI * 0.25 + (latitude) * DEGRAD * 0.5);
	    ra = re * sf / Math.pow(ra, sn);
	    var theta = longitude * DEGRAD - olon;
	    if (theta > Math.PI) theta -= 2.0 * Math.PI;
	    if (theta < -Math.PI) theta += 2.0 * Math.PI;
	    theta *= sn;
	
	    int x = (int) (ra * Math.sin(theta) + XO + 0.5);
	    int y = (int) (ro - ra * Math.cos(theta) + YO + 0.5);
	
	    nx = Integer.toString(x);
	    ny = Integer.toString(y);
	}
	
	private JSONObject[] weatherItems()
	{
		
		JSONObject jobj = (JSONObject) weaInfo.get("response");
				   jobj = (JSONObject) jobj.get("body");
				   jobj = (JSONObject) jobj.get("items");
				   
		JSONArray jarr = (JSONArray) jobj.get("item");
		
		@SuppressWarnings("unchecked")
		JSONObject[] arr = (JSONObject[]) jarr.toArray(JSONObject[]::new);
		
		return arr;
	}
	
	private String nowWeather()
	{
		Set<String> strs = new HashSet<String>();
		
		for(JSONObject j : items)
		{
			if(j.get("fcstTime").equals(fcstTime))
				if(j.get("category").equals("PTY"))
				{
					int pty = Integer.parseInt((String) j.get("fcstValue"));
					
					switch(pty)
					{
					
						case 0 : 
						strs.add("sunny");
						weather = Weather.NONE;
						break;
					
						case 2:
							case 1:
								strs.add("rainy");
								weather = Weather.RAINY;
								if(pty == 1) break;
								
							case 3:
								strs.add("snowy");
								weather = Weather.SNOWY;
						break;
								
						case 6:
							case 5:
								strs.add("drizzle");
								weather = Weather.RAINY;
								if(pty == 5) break;
								
							case 7:
								strs.add("flurries");
								weather = Weather.SNOWY;
						break;
					
					}
				}
			
				else if(j.get("category").equals("SKY"))
				{
					int sky = Integer.parseInt((String) j.get("fcstValue"));
					
					if(sky < 3 && sky >= 0) strs.add("clear");
					else if (sky < 6) strs.add("little cloudy");
					else if (sky < 9 ) strs.add("cloudy");
					else strs.add("foggy");
				}
			
				else if(j.get("category").equals("REH"))
				{
					if(Integer.parseInt((String) j.get("fcstValue")) > 60) strs.add("humid");
				}
		}
		
		if(strs.isEmpty()) strs.add("ordinary");
		
		String output = "";
		
		Iterator<String> iter = strs.iterator();
		
		do
		{		
			output += iter.next();
			
			if(iter.hasNext())
				output += ", ";
			else
				output += ".";

		}while(iter.hasNext());
		
		return "Today's weather is " + output;
	}
	
	private String setSeason()
	{
		int month = now.getMonth().getValue();
		
		if (month >= 3 && month < 6) return "Spring";
		else if (month >= 6 && month < 9) return "Summer";
		else if (month >= 9 && month < 11) return "Autumn";
		else return "Winter";
	}
}
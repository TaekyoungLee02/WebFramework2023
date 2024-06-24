package com.finalproject.aimage.image;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.finalproject.aimage.HomeController;

import reactor.util.retry.Retry;

@Component
public class ImageRequestWebClient
{
	private final String api_key = "5c515697d04966df3dd7d7166c6d1abb";
	
	private WebClient webClient;
	
	private JSONParser jsonParser;
	private JSONObject jsonResponse;
	
	private String response;
	private Map<String, Object> imageLinks;
	
	public ImageRequestWebClient()
	{
		buildWebClient();
		jsonParser = new JSONParser();
		imageLinks = new HashMap<>();
	}
	
	public void buildWebClient()
	{
		webClient = WebClient.builder()
				.baseUrl("https://api.kakaobrain.com/v2/inference/karlo/t2i")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + api_key)
				.build();
	}
	
	public void makeImageRequest(JSONObject body)
	{
		try
		{
			response = webClient.post()
					.bodyValue(body.toJSONString())
					.retrieve()
					.bodyToMono(String.class)
					.retryWhen(Retry.backoff(3, Duration.ofSeconds(1)))
					.block();
		}
		catch (Exception e)
		{
			HomeController.logger.error("Image Request Error", e);
			return;
		}
		
		try
		{
			jsonResponse = (JSONObject) jsonParser.parse(response);
		}
		catch (ParseException e)
		{
			HomeController.logger.error("Response Parsing Error", e);
			return;
		}
	}
	
	public JSONObject getJsonResponse() {
		return jsonResponse;
	}
	public String getResponse() {
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getImageLinks()
	{
		if(jsonResponse != null)
		{
			JSONArray images = (JSONArray) jsonResponse.get("images");
			
			int i = 0;
			
			for(JSONObject img : (JSONObject[])images.toArray(JSONObject[]::new))
			{
				imageLinks.put("img_" + i++, (String) img.get("image"));
			}
			return new JSONObject(imageLinks);
		}
		else
		{
			HomeController.logger.error("jsonRespnse is NULL");
			return null;
		}	
	}
}

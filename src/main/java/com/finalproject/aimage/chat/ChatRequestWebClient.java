package com.finalproject.aimage.chat;

import java.time.Duration;

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
public class ChatRequestWebClient
{
	private final String api_key = "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";
	
	private WebClient webClient;
	
	private JSONParser jsonParser;
	private JSONObject jsonResponse;
	
	private String response;
	private String responseMessage;
	
	public ChatRequestWebClient()
	{
		buildWebClient();
		jsonParser = new JSONParser();
	}
	
	public void buildWebClient()
	{
		webClient = WebClient.builder()
				.baseUrl("https://api.openai.com/v1/chat/completions")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + api_key)
				.build();
	}
	
	public void makeChatRequest(JSONObject body)
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
			HomeController.logger.error("Chat Request Error", e);
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
	public String getResponseMessage()
	{
		if(jsonResponse != null)
		{
			JSONArray choices = (JSONArray) jsonResponse.get("choices");
			JSONObject choice = (JSONObject) choices.get(0);
			JSONObject message = (JSONObject) choice.get("message");
			responseMessage = (String) message.get("content");
			return responseMessage;
		}
		else
		{
			HomeController.logger.error("jsonRespnse is NULL");
			return null;
		}	
	}
}

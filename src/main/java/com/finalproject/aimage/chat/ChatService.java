package com.finalproject.aimage.chat;

import org.springframework.stereotype.Service;

import com.finalproject.aimage.weather.WeatherService;
import com.finalproject.aimage.weather.WeatherVo;

@Service
public class ChatService
{
	private final ChatRequestBody chatRequestBody;
	private final ChatRequestWebClient chatRequestWebClient;
	private final WeatherService weatherService;
	
	private final String chat_model = "ft:gpt-3.5-turbo-0613:personal::8SpvNWHD";
	private final double temperature = 0.7;
	private final int maximumLength = 256;
	
	private boolean isBodySet;
	private boolean isChatEnd;
	private String systemMessage;
	private String response;
	private String keyWord;

	public ChatService(ChatRequestBody chatRequestBody, ChatRequestWebClient chatRequestWebClient, WeatherService weatherService)
	{
		this.chatRequestBody = chatRequestBody;
		this.chatRequestWebClient = chatRequestWebClient;
		this.weatherService = weatherService;
		
		isBodySet = false;
		isChatEnd = false;
	}
	
	private void bodySet()
	{
		chatRequestBody.setModel(chat_model);
		chatRequestBody.setTemperature(temperature);
		chatRequestBody.setMaximumLength(maximumLength);
		
		WeatherVo vo = weatherService.getWeatherVo();
		
		systemMessage = "Assistant is a kind street painter who is selling paintings on the spot. "
		+ "User is a customer and user wants to buy assistant's painting. "
		+ "Assistant is also usually talk about weather with his customer. "
		+ "When user speaks, assistant speak briefly, in about 20 words. "
		+ "If user speaks five times, assistant finishes painting and shows customer his picture with appending \"End\""
		+ "It's " + vo.getSeason() + " now and " + vo.getTodayWeather();
		
		isBodySet = true;
	}
	public void bodySet(String chat_model, double temperature, int maximumLength)
	{
		chatRequestBody.setModel(chat_model);
		chatRequestBody.setTemperature(temperature);
		chatRequestBody.setMaximumLength(maximumLength);
		isBodySet = true;
	}
	
	public void appendMessage(Role role, String content)
	{
		chatRequestBody.appendMessage(role, content);
	}
	public void clearMessages()
	{
		chatRequestBody.clearMessages();
	}
	
	public String makeChatRequest()
	{
		if(!isBodySet)
		{
			bodySet();
		}
		
		chatRequestWebClient.makeChatRequest(chatRequestBody.getRequestBody());
		response = chatRequestWebClient.getResponseMessage();
		chatRequestBody.appendMessage(Role.ASSISTANT, response);
		
		return response;
	}
	public String makeFirstChatRequest()
	{
		if(!isBodySet)
		{
			bodySet();
		}
		
		isChatEnd = false;
		chatRequestBody.clearMessages();
		
		chatRequestBody.appendMessage(Role.SYSTEM, systemMessage);
		chatRequestBody.appendMessage(Role.USER, "그림을 좀 그려 줄래?");
		
		chatRequestWebClient.makeChatRequest(chatRequestBody.getRequestBody());
		response = chatRequestWebClient.getResponseMessage();
		chatRequestBody.appendMessage(Role.ASSISTANT, response);
		
		return response;
	}
	public String getKeyWordRequest()
	{
		if(!isBodySet)
		{
			bodySet();
		}
		
		chatRequestBody.appendMessage(Role.USER, "Summarize our talk in 10 keywords in english in string.");
		
		chatRequestWebClient.makeChatRequest(chatRequestBody.getRequestBody());
		keyWord = chatRequestWebClient.getResponseMessage();
		chatRequestBody.clearMessages();
		
		keyWord = "painting, " + keyWord;
		isChatEnd = true;
		
		return keyWord;
	}
	public boolean isChatEnd()
	{
		return isChatEnd;
	}
	public String getKeyWord()
	{
		return keyWord;
	}
}

package com.finalproject.aimage.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ChatRequestBody
{
	private String model;
	private double temperature;
	private int maximumLength;
	private List<Map<String, Object>> messages;
	private Map<String, Object> requestBody;
	
	public ChatRequestBody()
	{
		messages = new ArrayList<>();
		model = "";
		temperature = 0;
		maximumLength = 0;
		
		requestBody = new HashMap<>();
	}

	public JSONObject getRequestBody()
	{
		requestBody.put("max_tokens", maximumLength);
		requestBody.put("temperature", temperature);
		requestBody.put("messages", messages);
		requestBody.put("model", model);

		return new JSONObject(requestBody);
	}
	public void clearRequest()
	{
		requestBody.clear();
	}
	
	public List<Map<String, Object>> getMessages()
	{
		return messages;
	}
	public void appendMessage(Role role, String content)
	{
		Map<String, Object> msg = new HashMap<>();
		
		msg.put("role", role.toString());
		msg.put("content", content);
		
		messages.add(msg);
	}
	public void clearMessages()
	{
		messages.clear();
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getMaximumLength() {
		return maximumLength;
	}
	public void setMaximumLength(int maximumLength) {
		this.maximumLength = maximumLength;
	}
}
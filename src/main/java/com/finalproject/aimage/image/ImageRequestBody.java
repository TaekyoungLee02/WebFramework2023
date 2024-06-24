package com.finalproject.aimage.image;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ImageRequestBody
{
	private int width;
	private int height;
	private int samples;
	
	private String prompt;
	private String image_format;
	
	private Map<String, Object> requestBody;
	
	public ImageRequestBody()
	{
		width = 0;
		height = 0;
		samples = 0;
		prompt = "";
		image_format = "";
		
		requestBody = new HashMap<>();
	}

	public JSONObject getRequestBody()
	{
		requestBody.put("width", width);
		requestBody.put("height", height);
		requestBody.put("samples", samples);
		requestBody.put("prompt", prompt);
		requestBody.put("image_format", image_format);

		return new JSONObject(requestBody);
	}
	public void clearRequest()
	{
		requestBody.clear();
	}

	public void clear()
	{
		width = 0;
		height = 0;
		samples = 0;
		prompt = "";
		image_format = "";
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSamples() {
		return samples;
	}
	public void setSamples(int samples) {
		this.samples = samples;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getImage_format() {
		return image_format;
	}
	public void setImage_format(String image_format) {
		this.image_format = image_format;
	}
}

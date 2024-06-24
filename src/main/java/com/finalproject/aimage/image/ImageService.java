package com.finalproject.aimage.image;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private final ImageRequestBody imageRequestBody;
	private final ImageRequestWebClient imageRequestWebClient;
	
	private boolean isBodySet;
	private JSONObject imageLinks;
	
	public ImageService(ImageRequestBody imageRequestBody, ImageRequestWebClient imageRequestWebClient)
	{
		this.imageRequestBody = imageRequestBody;
		this.imageRequestWebClient = imageRequestWebClient;
		
		isBodySet = false;
	}
	
	private void bodySet(String prompt)
	{
		imageRequestBody.setHeight(512);
		imageRequestBody.setWidth(512);
		imageRequestBody.setSamples(4);
		imageRequestBody.setPrompt(prompt);
		imageRequestBody.setImage_format("png");
		
		isBodySet = true;
	}
	public void bodySet(int hetght, int width, int samples, String prompt, String image_format)
	{
		imageRequestBody.setHeight(hetght);
		imageRequestBody.setWidth(width);
		imageRequestBody.setSamples(samples);
		imageRequestBody.setPrompt(prompt);
		imageRequestBody.setImage_format(image_format);
		
		isBodySet = true;
	}
	public void bodyClear()
	{
		imageRequestBody.clear();
	}
	
	public JSONObject makeImageRequest(String prompt)
	{
		if(!isBodySet)
		{
			bodySet(prompt);
		}
		
		imageRequestWebClient.makeImageRequest(imageRequestBody.getRequestBody());
		imageLinks = imageRequestWebClient.getImageLinks();
		imageRequestBody.clear();
		isBodySet = false;
		
		return imageLinks;
	}
}

package com.finalproject.aimage.image;

import java.time.LocalTime;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalproject.aimage.chat.ChatService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	private final ImageService imageService;
	private final ChatService chatService;
	private LocalTime now;
	
	public ImageController(ImageService imageService, ChatService chatService)
	{
		this.imageService = imageService;
		this.chatService = chatService;
		now = LocalTime.now();
	}

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String imagePage()
	{
		if(!chatService.isChatEnd())
			return "redirect:/";
		else
			if(now.isBefore(LocalTime.of(18, 0)) && now.isAfter(LocalTime.of(6, 0)))
				return "image/daytime";
			else
				return "image/nighttime";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getResponse", method = RequestMethod.GET)
	public JSONObject getResponse()
	{
		if(!chatService.isChatEnd())
			return null;
		
		JSONObject links = imageService.makeImageRequest(chatService.getKeyWord());
		
		return links;
	}
}

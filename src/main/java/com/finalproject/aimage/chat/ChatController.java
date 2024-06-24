package com.finalproject.aimage.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalproject.aimage.weather.WeatherService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	private final ChatService chatService;
	private final WeatherService weatherService;
	
	public ChatController(ChatService chatService, WeatherService weatherService)
	{
		this.chatService = chatService;
		this.weatherService = weatherService;
	}
    
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String chatPage(@RequestParam(required = false) boolean isDayTime)
	{
		if(weatherService.getWeatherVo() == null)
			return "redirect:/";
		else
			if(isDayTime)
				return "chat/daytime";
			else
				return "chat/nighttime";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getFirstResponse", method = RequestMethod.GET,  produces = "application/text; charset=utf8")
	public String getFirstResponse()
	{
		if(weatherService.getWeatherVo() == null)
			return "redirect:/";
		
		String str = chatService.makeFirstChatRequest();
		System.out.println(str);
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getResponse", method = RequestMethod.POST,  produces = "application/text; charset=utf8")
	public String getResponse(@RequestParam String message)
	{
		if(weatherService.getWeatherVo() == null)
			return "redirect:/";
		
		chatService.appendMessage(Role.USER, message);
		String str = chatService.makeChatRequest();
		System.out.println(str);
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getKeyWord", method = RequestMethod.GET,  produces = "application/text; charset=utf8")
	public String getKeyWord()
	{
		if(weatherService.getWeatherVo() == null)
			return "redirect:/";
		
		chatService.getKeyWordRequest();
		System.out.println(chatService.getKeyWord());
		return "success";
	}
}
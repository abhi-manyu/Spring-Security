package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Message;
import com.example.demo.service.MessageService;

@RestController()
public class MessageController {

	@Autowired
	private MessageService mserv;
	
	@GetMapping
	public Iterable<Message> getAllMessages()
	{
		return mserv.getAllMessages();
	}
	
	
	@PostMapping
	public Iterable<Message> addMessage(@RequestBody Message message)
	{
		return mserv.addMessage(message);
	}
}

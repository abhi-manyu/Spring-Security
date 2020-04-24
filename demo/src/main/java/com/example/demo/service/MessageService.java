package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Message;
import com.example.demo.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository mesrepo;
	
	public Iterable<Message> getAllMessages()
	{
		/*
		 * Message m1 = new Message(101,"Hello world","Abhimanyu"); mesrepo.save(m1);
		 */
		return mesrepo.findAll();
	}
	
	public Message getMessage(int id)
	{
		return mesrepo.findById(id).orElse(null);
	}
	
	public Iterable<Message> addMessage(Message msg)
	{
		mesrepo.save(msg);
		return mesrepo.findAll();
	}
	
	public Iterable<Message> deleteMessage(int id)
	{
		mesrepo.deleteById(id);
		return mesrepo.findAll();
	}
}

package com.techprimers.springbootsoapexample.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static final Map<String, User> users=new HashMap<>();
	@PostConstruct
public void initialize() {
	
}
}

package com.example.demo.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.demo.MaBanqueApplication;


public class WebInitializer extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(MaBanqueApplication.class);
	}
}

package com.lumatik.twiliocalltracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;

@SpringBootApplication
public class TwilioCallTrackingApplication {

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(TwilioCallTrackingApplication.class, args);
		Example.placeTestCall();
	}
}

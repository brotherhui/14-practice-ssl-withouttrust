package com.brotherhui;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

@RestController
class ClientController {
	

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);
    
    @Autowired
    private RestOperations restTemplate;
    

    @RequestMapping("/door")
    public String door(@RequestParam String uri) {
    	log.info("I am calling resource by RestTemplate");
        return restTemplate.getForObject(uri, String.class);
    }
	
    @RequestMapping("/restassure")
    public String restassure(@RequestParam String uri) throws IOException {
    	log.info("I am calling resource by RestAssured");
    	
    	//please refer to 
    	//https://github.com/rest-assured/rest-assured/wiki/Usage#ssl
    	
    	RestAssured.baseURI = uri;
        Response response = RestAssured.given().relaxedHTTPSValidation().when().get();
        return response.asString();
    }
}
package com.cpt.payments.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.cpt.payments.constant.Constant;
import com.cpt.payments.http.HttpClientUtil;
import com.cpt.payments.http.HttpRequest;
import com.cpt.payments.paypal.TokenResponse;
import com.google.gson.Gson;



@Service
public class TokenService {
	
	private static String accessToken;
	
	private Gson gson;
	
	private HttpClientUtil httpClientUtil;

	public TokenService(HttpClientUtil httpClientUtil, Gson gson) {
	    this.httpClientUtil = httpClientUtil;
	    this.gson = gson;
	}

	   @Value("${paypal.clientid}")
	    private String clientId;

	    @Value("${paypal.clientsecret}")
	    private String clientSecret;

	    @Value("${paypal.outh.url}")
	    private String oauthUrl;

	
	public String getAccessToken() {
		
		if(accessToken != null) {
			System.out.println("Returning already available accessToken");
			return accessToken;
		}

		
        System.out.println("accessToken is null , so making OAuth call to getbaccessToken");
		
		

		HttpRequest httpRequest = new HttpRequest() ;
//		String oauthUrl = "https://api-m.sandbox.paypal.com/v1/oauth2/token"; 
		httpRequest.setUrl(oauthUrl);
	
		httpRequest.setMethod(HttpMethod.POST);

		MultiValueMap<String, String> requestPayload = new LinkedMultiValueMap<>();
		requestPayload.add(Constant.OAUTH_GRANT_TYPE, Constant.OAUTH_GRANT_CLIENT_CREDENTIALS);
		httpRequest.setRequest(requestPayload);

//		String clientId = "AXyvaGcVszpvI-nGYTl4jP8WY3p_5gEjDzbSBfcDskhtjy501g-xE-HLqA2dVP4urngc67xKLFij3B3u";
//		String clientSecret = "EHNI_GbfTqR5WI7rcg9gNikjT6h_rnpwT0DzQ6uuQgLvAB-N1dmUVDJMUUKv5HoEIhKIcgXcNt4zoPnw";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth(clientId, clientSecret);
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
        httpRequest.setHttpHeaders(httpHeaders);
		
		System.out.println("TokenService::| Making HTTP Call for httpRequest: "+ httpRequest);
		
		ResponseEntity<String> outhTokenResponse = httpClientUtil.makeHttpRequest(httpRequest );
		
		String resBodyAsJson = outhTokenResponse.getBody();
		
		System.out.println("Got outhTokenResponse: "+ outhTokenResponse);
		System.out.println("Got resBodyAsJson: "+ resBodyAsJson);

		TokenResponse responseAsObj = gson.fromJson(resBodyAsJson,TokenResponse.class);
		System.out.println("TokenResponse as java Object responseAsObj: "+ responseAsObj);

	    accessToken = responseAsObj.getAccessToken();
		System.out.println("Returning accessToken: "+ accessToken);
		return accessToken;

	}
}



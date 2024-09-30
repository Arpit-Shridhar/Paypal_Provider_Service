package com.cpt.payments.service.impl;


import org.springframework.stereotype.Service;

import com.cpt.payments.controller.dto.CreateOrderReqDTO;
import com.cpt.payments.controller.dto.OrderDTO;
import com.cpt.payments.service.TokenService;
import com.cpt.payments.service.interfaces.PaymentService;



    @Service
	public class PaymentServiceImpl implements PaymentService {

	
	private TokenService tokenService;

	public PaymentServiceImpl(TokenService tokenService) {
		this.tokenService = tokenService;
	}

		

	    @Override
	    public OrderDTO createOrder(CreateOrderReqDTO createOrderReqDTO) {
	        System.out.println("Creating Order for : " + createOrderReqDTO);

	        String accessToken  = tokenService.getAccessToken();
	        System.out.println("Got access token: "+ accessToken);
	        
	        OrderDTO orderDTO = new OrderDTO();
	        orderDTO.setId("1234");
	        orderDTO.setStatus("CREATE");
	        orderDTO.setRedirectUrl("http://localhost:8083/v1/paypal/order/1234");
	        
	        System.out.println("Returning created order : " +  orderDTO);
	        
	        return orderDTO;  // Ensure this returns OrderDTO
	    }
	}


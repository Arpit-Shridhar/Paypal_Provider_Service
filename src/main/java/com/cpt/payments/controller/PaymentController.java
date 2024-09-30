package com.cpt.payments.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.controller.dto.CreateOrderReqDTO;
import com.cpt.payments.controller.dto.OrderDTO;
import com.cpt.payments.pojo.Order;
import com.cpt.payments.pojo.PaypalProviderCreateOrderReq;
import com.cpt.payments.service.interfaces.PaymentService;

@RestController
@RequestMapping("/v1/paypal/order")
public class PaymentController {
	
	private ModelMapper modelMapper;
	
	private PaymentService paymentService;
	
	public PaymentController(ModelMapper modelMapper, PaymentService paymentService) {
		this.modelMapper = modelMapper;
		this.paymentService = paymentService;
	}

	@PostMapping
	public Order createOrder( @RequestBody PaypalProviderCreateOrderReq createOrder) {
		
		System.out.println("Order createn request received POJO CreatOrder: "+ createOrder);
		
		
		 CreateOrderReqDTO reqDTO = modelMapper.map(createOrder, CreateOrderReqDTO.class);
		System.out.println("Convert req pojo to  DTO reqDTO"+ reqDTO);
		
		OrderDTO responseDTO = paymentService.createOrder(reqDTO);
		System.out.println("Received response from service responseDTO");
		
		Order order = modelMapper.map(responseDTO, Order.class);
		System.out.println("Convert service response to POJO & returning order: "+ order);
		
		
		return order;
	}
}


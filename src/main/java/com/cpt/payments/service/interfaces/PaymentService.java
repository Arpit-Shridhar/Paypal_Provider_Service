package com.cpt.payments.service.interfaces;

import com.cpt.payments.controller.dto.CreateOrderReqDTO;
import com.cpt.payments.controller.dto.OrderDTO;

public interface PaymentService {
	
	public  OrderDTO createOrder(CreateOrderReqDTO createOrderReqDTO);
	
}

package com.cpt.payments.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaypalProviderCreateOrderReq {

	private String txnRef;
	
	private String currencyCode;
	
	private String amountValue;
	
	private String brandName;
	
	private String locale;
	
	private String returnUrl;
	
	private String cancelUrl;

	
	

}


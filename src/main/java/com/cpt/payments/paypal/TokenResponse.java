package com.cpt.payments.paypal;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse {
	
	private String scope;
	
	@SerializedName("access_token")
	private String accessToken;
	
	@SerializedName("token_type")
	private String tokenType;
	
	@SerializedName("app_id")
	private String appId;
	
	@SerializedName("expires_in")
	private String expiresIn;
	
	private String nonce;

	public String getAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}


}

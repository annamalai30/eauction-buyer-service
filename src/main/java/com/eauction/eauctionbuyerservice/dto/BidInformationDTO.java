package com.eauction.eauctionbuyerservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BidInformationDTO {

	@NotEmpty
	@NotBlank(message = "firstName can't be blank")
	@Size(min = 5, max = 30)
	private String firstName;
	@NotEmpty
	@NotBlank(message = "lastName can't be blank")
	@Size(min = 5, max = 30)
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String pin;
	@NotBlank(message = "phone can't be blank")
	@Positive
	@Size(min = 10, max = 10)
	private String phone;
	@NotNull
	@Email
	private String email;
	private String productId;
	private int bidAmount;
}

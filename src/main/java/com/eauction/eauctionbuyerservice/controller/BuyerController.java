package com.eauction.eauctionbuyerservice.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;
import com.eauction.eauctionbuyerservice.dto.Response;
import com.eauction.eauctionbuyerservice.service.IBuyerService;
import com.eauction.eauctionbuyerservice.utils.Constants;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	
	static Logger logger = Logger.getLogger(BuyerController.class.getName());

	@Autowired
	IBuyerService buyerService;
	
	@PostMapping(path = "/updatebid")
	public Response updateBidController(@Valid @RequestBody BidInformationDTO bidInformationDTO,
			BindingResult bindingResult) throws Exception {
		logger.info("updateBidController Starts Here");
		Response response = new Response();
		if (null != bindingResult && bindingResult.hasErrors()) {
			logger.info(bindingResult.getAllErrors().toString());
			response.setMessage(bindingResult.getAllErrors().toString());
			response.setResponse(Constants.FAILED);
			return response;
		}
		response.setResponse(buyerService.updateBid(bidInformationDTO));
		return response;
	}
}

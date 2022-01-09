package com.eauction.eauctionbuyerservice.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eauction.eauctionbuyerservice.customException.BidDateException;
import com.eauction.eauctionbuyerservice.dto.Response;
import com.eauction.eauctionbuyerservice.service.IBuyerService;
import com.eauction.eauctionbuyerservice.utils.Constants;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

	static Logger logger = Logger.getLogger(BuyerController.class.getName());

	@Autowired
	IBuyerService buyerService;

	@GetMapping(path = "/updatebid/{productId}/{buyerEmailId}/{newBidAmount}")
	public Response updateBidController(@PathVariable String productId, @PathVariable String buyerEmailId,
			@PathVariable int newBidAmount) throws BidDateException {
		logger.info("updateBidController Starts Here");
		Response response = new Response();
		if (productId == null && buyerEmailId == null) {
			logger.info("updateBidController, ProductID, EmailID and BidAmount should not null");
			response.setMessage("ProductID, EmailID and BidAmount should not null");
			response.setResponse(Constants.FAILED);
			return response;
		}
		response.setResponse(buyerService.updateBid(productId, buyerEmailId, newBidAmount));
		logger.info("updateBidController Ends Here " + response);
		return response;
	}
}

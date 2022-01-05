package com.eauction.eauctionbuyerservice.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eauction.eauctionbuyerservice.customException.BidDateException;
import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;
import com.eauction.eauctionbuyerservice.model.ProductInfo;
import com.eauction.eauctionbuyerservice.repository.IBuyerRepository;
import com.eauction.eauctionbuyerservice.service.IBuyerService;

@Service
public class BuyerServiceImpl implements IBuyerService {

	static Logger logger = Logger.getLogger(BuyerServiceImpl.class.getName());

	@Autowired
	IBuyerRepository buyerRepository;

	public String updateBid(String productId, String buyerEmailId, int newBidAmount) throws BidDateException {
		logger.info("updateBid Service Layer Starts Here");
		ProductInfo productInfo = buyerRepository.getProductInformation(productId);
		BidInformationDTO bidInformation = buyerRepository.getBidInformation(productId, buyerEmailId);
		if (bidInformation == null) {
			logger.info("updateBid Service Layer, Bid Information Not Available");
			return "Bid Information Not Available";
		}
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		if (productInfo.getBidEndDate() != null && productInfo.getBidEndDate().compareTo(timeStamp) < 0) {
			logger.info("updateBid Service Layer, Bid Date is Closed");
			throw new BidDateException("Bid Date is Closed");
		}
		logger.info("updateBid Service Layer Ends Here");
		return buyerRepository.updateBidInformation(productId, buyerEmailId, newBidAmount);
	}

}

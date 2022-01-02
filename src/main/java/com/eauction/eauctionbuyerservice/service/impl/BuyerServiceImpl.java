package com.eauction.eauctionbuyerservice.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.eauction.eauctionbuyerservice.model.ProductInfo;
import com.eauction.eauctionbuyerservice.repository.IBuyerRepository;
import com.eauction.eauctionbuyerservice.customException.BidDateException;
import com.eauction.eauctionbuyerservice.dto.BidInformationDTO;
import com.eauction.eauctionbuyerservice.service.IBuyerService;

public class BuyerServiceImpl implements IBuyerService {

	@Autowired
	IBuyerRepository buyerRepository;
	
	public String updateBid(BidInformationDTO bidInformationDTO) throws BidDateException {
		ProductInfo productInfo = buyerRepository.getProductInformation(bidInformationDTO.getProductId());
		BidInformationDTO bidInformation = buyerRepository.getBidInformation(bidInformationDTO);
		if(bidInformation == null) {
			return "Bid Information Not Available";
		}
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		if (productInfo.getBidEndDate() != null && productInfo.getBidEndDate().compareTo(timeStamp) < 0) {
			throw new BidDateException("Bid Date is Closed");
		}
		return buyerRepository.updateBidInformation(bidInformationDTO);
	}

}

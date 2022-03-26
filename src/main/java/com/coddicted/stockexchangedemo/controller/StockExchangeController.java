package com.coddicted.stockexchangedemo.controller;

import com.coddicted.stockexchangedemo.model.Company;
import com.coddicted.stockexchangedemo.model.Message;
import com.coddicted.stockexchangedemo.model.Request;
import com.coddicted.stockexchangedemo.util.StockExchangeUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.ZonedDateTime;

@Controller
public class StockExchangeController {
    @MessageMapping("/stocks")
    @SendTo("/topic/messages")
    public Message send(Request request) throws Exception {
        Company company = StockExchangeUtil.getCompanyById(request.getCompanyId());
        int price = StockExchangeUtil.getRandomPrice();
        return new Message(company.getCompanyId(), company.getCompanyName(), price, ZonedDateTime.now());
    }
}



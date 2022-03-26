package com.coddicted.stockexchangedemo.service;

import com.coddicted.stockexchangedemo.model.Company;
import com.coddicted.stockexchangedemo.model.Message;
import com.coddicted.stockexchangedemo.util.StockExchangeUtil;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class ScheduledPushMessageService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        Company company = StockExchangeUtil.getRandomCompany();
        int price = StockExchangeUtil.getRandomPrice();
        simpMessagingTemplate
                .convertAndSend("/topic/pushmessages",
                new Message(company.getCompanyId(), company.getCompanyName(), price, ZonedDateTime.now()));
    }
}

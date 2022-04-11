package com.sg.microservices.currencyexchangeservice.controller;

import com.sg.microservices.currencyexchangeservice.CurrencyExchange;
import com.sg.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveValueExchange called with {} to {}", from, to);
//      CurrencyExchange byFromAndTo = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        CurrencyExchange byFromAndTo = currencyExchangeRepository.findByFromAndTo(from, to);
        if (byFromAndTo == null) {
            throw new RuntimeException("Unable to find data for" + from + "to" + to);
        }
        String port = environment.getProperty("local.server.port");
        byFromAndTo.setEnvironment(port);
        return byFromAndTo;
    }

}

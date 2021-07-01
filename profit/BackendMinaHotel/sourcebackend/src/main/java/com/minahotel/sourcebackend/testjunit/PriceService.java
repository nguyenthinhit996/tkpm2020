package com.minahotel.sourcebackend.testjunit;

import org.springframework.stereotype.Service;

@Service
public class PriceService {
    public int getActualPrice(Integer item){
        throw new UnsupportedOperationException("Fail is not mocked!");
    }

    public int calculatePriceForOrder(Order order){
        int orderPrice = 0;
        for (Integer item : order.getLs()){
            int a = getActualPrice(item);
            orderPrice += a;
        }
        return orderPrice;
    }
}
package com.minahotel.sourcebackend.testjunit;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;


class OrderServiceTest {
    private static final int TEST_ORDER_ID = 15;
    private static final int TEST_SHOES_PRICE = 2;
    private static final int TEST_SHIRT_PRICE = 1;

    @InjectMocks
    private OrderService testingObject;

    @Spy
    private PriceService priceService;

    @Mock
    private OrderDao orderDao;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrderService(){
        Order order = new Order(Arrays.asList(10, 20));
        Mockito.when(orderDao.getOrder(TEST_ORDER_ID)).thenReturn(order);

        //notice different Mockito syntax for spy
        Mockito.doReturn(TEST_SHIRT_PRICE).when(priceService).getActualPrice(10);
        Mockito.doReturn(TEST_SHOES_PRICE).when(priceService).getActualPrice(20);

        //call testing method
        int actualOrderPrice = testingObject.getOrderPrice(TEST_ORDER_ID);
        System.out.println(actualOrderPrice);

        Assert.assertEquals(TEST_SHIRT_PRICE + TEST_SHOES_PRICE, actualOrderPrice);
    }
}
package com.minahotel.sourcebackend.testjunit;

import org.aspectj.weaver.ast.Or;

import java.util.List;

public class Order {

    private List<Integer> ls ;

    Order(List<Integer> ls) {
        this.ls = ls;
    }

    public void setLs(List<Integer> ls) {
        this.ls = ls;
    }

    public List<Integer> getLs() {
        return ls;
    }
}

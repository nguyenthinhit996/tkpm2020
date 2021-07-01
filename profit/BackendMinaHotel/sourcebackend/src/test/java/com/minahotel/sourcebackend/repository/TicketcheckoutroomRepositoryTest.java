package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
class TicketcheckoutroomRepositoryTest {

    @Autowired
    TicketcheckoutroomRepository ticketcheckoutroomRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find ticket checkout by idcheckout find exist")
    void findByidTicketCheckout_GivenId_ThenFindExist() {

        //given
        String idCheckOut = "2021-02-24T22:31:56.176249600";

        //when
        Optional<TicketCheckOutRoomEntity> byidTicketCheckout = ticketcheckoutroomRepository.findByidTicketCheckout(idCheckOut);

        //then
        assertThat(byidTicketCheckout.isPresent()).isTrue();
        assertThat(byidTicketCheckout.get().getIdTicketCheckout()).isEqualTo(idCheckOut);
    }

    @Test
    @DisplayName("find ticket checkout by idcheckout find not exist item")
    void findByidTicketCheckout_GivenId_ThenFindNotExist() {
        //given
        String idCheckOut = "2021-02-24T22:31:56.176249600XXX";

        //when
        Optional<TicketCheckOutRoomEntity> byidTicketCheckout = ticketcheckoutroomRepository.findByidTicketCheckout(idCheckOut);

        //then
        assertThat(byidTicketCheckout.isPresent()).isFalse();
    }

    @Test
    @DisplayName("find ticket check out status clean found exist")
    public void finAllTicketCheckoutStatusClean_GivenId_ThenExistItem(){

        Iterable<TicketCheckOutRoomEntity> findAllObject = ticketcheckoutroomRepository.findAll();
        List<TicketCheckOutRoomEntity> listAll= new ArrayList<>();
        findAllObject.forEach(listAll::add);

        List<TicketCheckOutRoomEntity> listStatusClean = ticketcheckoutroomRepository.finAllTicketCheckoutStatusClean();
        if(listAll != null && listAll.size() > 0) {
             if(listStatusClean != null && listStatusClean.size() > 0){
                 listStatusClean.forEach(s -> {
                     int index = listAll.indexOf(s);
                     if(index != -1){
                        if(!"Clean".equals(listAll.get(index).getStatus())){
                            assertThat(true).isFalse();
                        }
                     }
                 });
             }
        }
    }
}
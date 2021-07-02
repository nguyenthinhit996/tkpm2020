package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.ConfigActiveTestProfilesCustomize;
import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
@ConfigActiveTestProfilesCustomize
class TicketbookingRepositoryTest {

    @Autowired
    TicketbookingRepository ticketbookingRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find ticket booking by id exist")
    void findByidTicketBooking_GivenId_ThenExist() {

        //given
        String idTicket = "2021-02-21T23:57:19.711801";

        //when
        Optional<TicketBookingEntity> byidTicketBooking = ticketbookingRepository.findByidTicketBooking(idTicket);

        //then
        assertThat(byidTicketBooking.isPresent()).isTrue();
        assertThat(byidTicketBooking.get().getIdTicketBooking()).isEqualTo(idTicket);
    }

    @Test
    @DisplayName("find ticket booking by id not exist")
    void findByidTicketBooking_GivenId_ThenNotExist() {
        //given
        String idTicket = "2021-02-21T23:57:19.711801XXX";

        //when
        Optional<TicketBookingEntity> byidTicketBooking = ticketbookingRepository.findByidTicketBooking(idTicket);

        //then
        assertThat(byidTicketBooking.isPresent()).isFalse();
    }
}
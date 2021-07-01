package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDetailsServicesEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
class DetailservicesRepositoryTest {

    @Autowired
    DetailservicesRepository detailservicesRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find detail services by composite key exist item")
    void findByidDetailsServicesEntity_GivenId_ThenExist() {

        //Given
        CompositeKeyDetailsServicesEntity key = new CompositeKeyDetailsServicesEntity();
        key.setIdTicketBooking("2021-02-21T15:46:02.867308900");
        key.setIdProduct("7");

        //when
        Optional<DetailsServicesEntity> byidDetailsServicesEntity = detailservicesRepository.findByidDetailsServicesEntity(key);

        //then
        assertThat(byidDetailsServicesEntity.isPresent()).isTrue();
        assertThat(byidDetailsServicesEntity.get().getIdDetailsServicesEntity()).isEqualTo(key);
    }

    @Test
    @DisplayName("find detail services by composite key not exist item")
    void findByidDetailsServicesEntity_GivenId_ThenNotExist() {

        //Given
        CompositeKeyDetailsServicesEntity key = new CompositeKeyDetailsServicesEntity();
        key.setIdTicketBooking("2021-02-21T15:46:02.867308900XX");
        key.setIdProduct("7");

        //when
        Optional<DetailsServicesEntity> byidDetailsServicesEntity = detailservicesRepository.findByidDetailsServicesEntity(key);

        //then
        assertThat(byidDetailsServicesEntity.isPresent()).isFalse();
    }

    @Test
    @DisplayName("find detail services by ticket booking exist item")
    void selectByidTicketBooking_GivenID_ThenExist() {
        //given
        String idTicket = "2021-02-21T15:46:02.867308900";
        String idProduct = "7";

        //when
        Optional<DetailsServicesEntity> detailsServicesEntity = detailservicesRepository.selectByidTicketBooking(idTicket, idProduct);

        //then
        assertThat(detailsServicesEntity.isPresent()).isTrue();
        assertThat(detailsServicesEntity.get().getIdDetailsServicesEntity().getIdTicketBooking()).isEqualTo(idTicket);
        assertThat(detailsServicesEntity.get().getIdDetailsServicesEntity().getIdProduct()).isEqualTo(idProduct);
    }

    @Test
    @DisplayName("find detail services by ticket booking not exist item")
    void selectByidTicketBooking_GivenId_ThenNotExist() {
        //given
        String idTicket = "2021-02-21T15:46:02.867308900XXX";
        String idProduct = "7";

        //when
        Optional<DetailsServicesEntity> detailsServicesEntity = detailservicesRepository.selectByidTicketBooking(idTicket, idProduct);

        //then
        assertThat(detailsServicesEntity.isPresent()).isFalse();
    }

    @Test
    @DisplayName("find detail services by id ticket booking exist item")
    void findByidDetailsServicesEntityIdTicketBooking_GivenId_ThenExistItem() {
        //given
        String idTicket = "2021-02-21T15:46:02.867308900";

        //when
        List<DetailsServicesEntity> byidDetailsServicesEntityIdTicketBooking = detailservicesRepository.findByidDetailsServicesEntityIdTicketBooking(idTicket);

        //then
        assertThat(byidDetailsServicesEntityIdTicketBooking).isNotNull();
        assertThat(byidDetailsServicesEntityIdTicketBooking).hasSizeGreaterThan(0);
        assertThat(byidDetailsServicesEntityIdTicketBooking).extracting(
                s->{ return s.getIdDetailsServicesEntity().getIdTicketBooking();}).containsOnly(idTicket);
    }

    @Test
    @DisplayName("find detail services by id ticket booking not exist item")
    void findByidDetailsServicesEntityIdTicketBooking_GivenId_ThenNotFoundExistItem() {
        //given
        String idTicket = "2021-02-21T15:46:02.867308900XXX";

        //when
        List<DetailsServicesEntity> byidDetailsServicesEntityIdTicketBooking = detailservicesRepository.findByidDetailsServicesEntityIdTicketBooking(idTicket);

        //then
        assertThat(byidDetailsServicesEntityIdTicketBooking).hasSize(0);
    }
}
package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.ConfigActiveTestProfilesCustomize;
import com.minahotel.sourcebackend.entities.CheckingOutRoomDamagedEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
@ConfigActiveTestProfilesCustomize
class CheckingOutRoomDamagedRepositoryTest {

    @Autowired
    private CheckingOutRoomDamagedRepository checkingOutRoomDamagedRepository;

    @Test
    @DisplayName("find room damaged by id by found exist")
    void findByidCheckoutRoomDamaged_GivenId_ThenExist() {
        //given
        String idRoomDamaged = "1614056889522";

        //when
        Optional<CheckingOutRoomDamagedEntity> result = checkingOutRoomDamagedRepository.findByidCheckoutRoomDamaged(idRoomDamaged);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("find room damaged by id not found item")
    void findByidCheckoutRoomDamaged_GivenId_ThenNotFound() {
        //given
        String idRoomDamaged = "1614056889522_xxx";

        //when
        Optional<CheckingOutRoomDamagedEntity> result = checkingOutRoomDamagedRepository.findByidCheckoutRoomDamaged(idRoomDamaged);

        // then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    @DisplayName("find room damaged by id ticket booking found exist item")
    void findByidTicketBooking_GivenId_ThenFoundExist() {

        // given
        String id="2021-02-21T16:31:01.102493100";

        // when
        Optional<CheckingOutRoomDamagedEntity> op = checkingOutRoomDamagedRepository.findByidTicketBooking(id);

        // then
        assertThat(op.isPresent()).isTrue();
    }

    @Test
    @DisplayName("find room damaged by id ticket booking not found")
    void findByidTicketBooking_GivenId_ThenNotFound() {

        // given
        String id="2021-02-24T22:31:56.176249600_xxx";

        // when
        Optional<CheckingOutRoomDamagedEntity> op = checkingOutRoomDamagedRepository.findByidTicketBooking(id);

        // then
        assertThat(op.isPresent()).isFalse();
    }
}
package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.ConfigActiveTestProfilesCustomize;
import com.minahotel.sourcebackend.entities.RoomEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
@ConfigActiveTestProfilesCustomize
class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find room by idroom exist item")
    void findByidRoom_GivenId_ThenExist() {
        //given
        Integer idRoom = 2;

        //when
        Optional<RoomEntity> byidRoom = roomRepository.findByidRoom(idRoom);

        //then
        assertThat(byidRoom.isPresent()).isTrue();
        assertThat(byidRoom.get().getIdRoom()).isEqualTo(idRoom);
    }

    @Test
    @DisplayName("find room by idroom exist item")
    void findByidRoom_GivenId_ThenNotExist() {
        //given
        Integer idRoom = 22;

        //when
        Optional<RoomEntity> byidRoom = roomRepository.findByidRoom(idRoom);

        //then
        assertThat(byidRoom.isPresent()).isFalse();
    }


    @Test
    @DisplayName("find room find All")
    void findAll_GivenNotThing_ThenSuccess() {
        //given

        //when
        List<RoomEntity> byidRoom = roomRepository.findAll();

        //then
        assertThat(byidRoom).isNotNull();
        assertThat(byidRoom).hasSizeGreaterThan(0);
    }
}
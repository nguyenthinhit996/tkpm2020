package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.entities.TypeOfRoomEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
class TypeofroomRepositoryTest {

    @Autowired
    TypeofroomRepository typeofroomRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find type of room by idNametype exist item")
    void findByidNameTypeOfRoom_GivenId_ThenExistItem() {
        //given
        String id ="double";

        //when
        Optional<TypeOfRoomEntity> byidNameTypeOfRoom = typeofroomRepository.findByidNameTypeOfRoom(id);

        //then
        assertThat(byidNameTypeOfRoom.isPresent()).isTrue();
        assertThat(byidNameTypeOfRoom.get().getIdNameTypeOfRoom()).isEqualTo(id);
    }

    @Test
    @DisplayName("find type of room by idNametype not exist item")
    void findByidNameTypeOfRoom_GivenId_ThenNotExist() {
        //given
        String id ="doubleXXX";

        //when
        Optional<TypeOfRoomEntity> byidNameTypeOfRoom = typeofroomRepository.findByidNameTypeOfRoom(id);

        //then
        assertThat(byidNameTypeOfRoom.isPresent()).isFalse();
    }
}
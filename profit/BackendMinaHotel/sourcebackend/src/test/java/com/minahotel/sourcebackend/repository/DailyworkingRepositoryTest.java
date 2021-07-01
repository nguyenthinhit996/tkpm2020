package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.entities.DailyWorkingEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDailyWorkingEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
class DailyworkingRepositoryTest {

    @Autowired
    DailyworkingRepository dailyworkingRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find daily working by composite key exist")
    void findByidDailyWorkingEntity_GivenID_ThenCompositeKey_Exist() {
        //give
        CompositeKeyDailyWorkingEntity key = new CompositeKeyDailyWorkingEntity();
        key.setIdStaffWork("staff_03");
        key.setIdToDay(LocalDate.parse("2021-01-01"));

        //when
        Optional<DailyWorkingEntity> dailyWorkingEntity = dailyworkingRepository.findByidDailyWorkingEntity(key);

        //then
        assertThat(dailyWorkingEntity.isPresent()).isTrue();
    }

    @Test
    @DisplayName("find daily working by composite key not exist")
    void findByidDailyWorkingEntity_GivenID_ThenCompositeKey_NotExit() {
        //give
        CompositeKeyDailyWorkingEntity key = new CompositeKeyDailyWorkingEntity();
        key.setIdStaffWork("staff_03_X");
        key.setIdToDay(LocalDate.parse("2021-01-01"));

        //when
        Optional<DailyWorkingEntity> dailyWorkingEntity = dailyworkingRepository.findByidDailyWorkingEntity(key);

        //then
        assertThat(dailyWorkingEntity.isPresent()).isFalse();
    }
}
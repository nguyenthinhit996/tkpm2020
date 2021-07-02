package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.ConfigActiveTestProfilesCustomize;
import com.minahotel.sourcebackend.entities.DateWorkEntity;
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
@ConfigActiveTestProfilesCustomize
class DateworkRepositoryTest {

    @Autowired
    DateworkRepository dateworkRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find data work find by id exist item")
    void findByidDateWork_GivenID_ThenFoundItem() {
        //give
        String id = "2021-01-01";

        //when
        Optional<DateWorkEntity> ds = dateworkRepository.findByidDateWork(LocalDate.parse(id));

        //then
        assertThat(ds.isPresent()).isTrue();
        LocalDate localDate = ds.get().getIdDateWork();
        assertThat(localDate).isEqualTo(id);
    }

    @Test
    @DisplayName("find data work find by id not exist")
    void findByidDateWork_GivenId_ThenNotExist() {
        //give
        String id = "2022-01-03"; // date

        //when
        Optional<DateWorkEntity> ds = dateworkRepository.findByidDateWork(LocalDate.parse(id));

        //then
        assertThat(ds.isPresent()).isFalse();
    }
}
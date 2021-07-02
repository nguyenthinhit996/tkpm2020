package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.ConfigActiveTestProfilesCustomize;
import com.minahotel.sourcebackend.entities.StaffEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
@ConfigActiveTestProfilesCustomize
class StaffRepositoryTest {

    @Autowired
    private StaffRepository staffRepository;

    @Test
    @DisplayName("check find all case success")
    void itShouldfindAll() {
        //given nothing
        // when
        List<StaffEntity> listAll = staffRepository.findAll();
        System.out.println("--------------------------------------"+listAll.get(0).toString());
        //then
        assertThat(listAll).isNotNull();
    }

    @Test
    @DisplayName("check find staff by id case success")
    void itShouldfindByidStaff() {
        //given
        String idStaff = "staff_02";
        //when
        Optional<StaffEntity> ob = staffRepository.findByidStaff(idStaff);
        assertThat(ob).isNotNull();
        assertThat(ob.get().getIdStaff()).isEqualTo(idStaff);
    }

    @Test
    @DisplayName("check find staff by id case not found")
    void itShouldfindByidStaffCaseNotFound() {
        //given
        String idStaff = "staff_023";
        //when
        Optional<StaffEntity> ob = staffRepository.findByidStaff(idStaff);
        assertThat(ob.isPresent()).isFalse(); // value null
    }

    @Test
    @Disabled
    void testUserAnnotationDisable() {
        //given
        String idStaff = "staff_023";
        //when
        Optional<StaffEntity> ob = staffRepository.findByidStaff(idStaff);
        assertThat(ob.isPresent()).isFalse(); // value null
    }
}
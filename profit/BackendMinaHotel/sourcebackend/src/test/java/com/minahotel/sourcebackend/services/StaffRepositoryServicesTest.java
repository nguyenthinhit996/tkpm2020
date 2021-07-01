package com.minahotel.sourcebackend.services;

import com.minahotel.sourcebackend.SourcebackendApplication;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.pojo.ChangePassPojo;
import com.minahotel.sourcebackend.pojo.DetailRoom;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.StaffRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class StaffRepositoryServicesTest {

    final String STAFF_EXIST = "idExist";

    @InjectMocks StaffRepositoryServices staffRepositoryServices;
    @Mock StaffRepository staffRepository;
    @Mock EntityManager entityManager;
    @Captor ArgumentCaptor<StaffEntity> staffEntity;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");
        staff.setRoleOfStaff("tieptan");
        staff.setPassWordStaff("$2a$10$OFSLRhGu7oZhapYUcU7nKeKHRWfBf5C0ADAkCZcgGFa/eEiloBdPO");

        Mockito.when(staffRepository.findByidStaff(STAFF_EXIST)).thenReturn(Optional.of(staff));
        Mockito.when(entityManager.getReference(StaffEntity.class,staff.getIdStaff())).thenReturn(staff);
        Mockito.when(entityManager.getReference(Mockito.eq(StaffEntity.class),Mockito.eq(staff.getIdStaff()))).thenReturn(staff);
        Mockito.when(entityManager.getReference(Mockito.eq(StaffEntity.class),Mockito.eq("throw"))).thenThrow(IllegalArgumentException.class);
        Mockito.when(entityManager.getReference(Mockito.eq(StaffEntity.class),Mockito.eq("null"))).thenReturn(null);

        // set value for field of
        ReflectionTestUtils.setField(staffRepositoryServices, "passwordEncoder", new BCryptPasswordEncoder());
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void throwIfGetNotFoundItemException() {
        Mockito.when(staffRepository.findAll()).thenReturn(null);
        assertThrows(NotFoundItemException.class,() -> {
            staffRepositoryServices.getAll();
        });
    }

    @Test
    void canGetAllItem() {
        //give
        StaffEntity a =new StaffEntity();
        a.setIdStaff("test");
        Mockito.when(staffRepository.findAll()).thenReturn(Arrays.asList(a));

        //when
        assertThat(staffRepositoryServices.getAll().size()).isEqualTo(1);

        //then
        verify(staffRepository,atLeastOnce()).findAll();
    }


    @Test
    void getObjectById_GivenId_ThenFindExist() {
        //when
        StaffEntity objectById = (StaffEntity) staffRepositoryServices.getObjectById(STAFF_EXIST);

        //then
        assertThat(objectById.getIdStaff()).isEqualTo(STAFF_EXIST);
        verify(staffRepository, atLeastOnce()).findByidStaff(any());
    }


    @Test
    void getObjectByIdGetThrowException() {
        //then
        assertThrows(NotFoundItemException.class, () -> {
            staffRepositoryServices.getObjectById("STAFF_EXIST");
        });
    }

    @Test
    void createObject() {

        //given
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");

        //when
        staffRepositoryServices.createObject(staff);

        //then
        verify(staffRepository).save(any());
    }

    @Test
    void createObjectThrowException() {
        //given
        DetailRoom detailRoom = new DetailRoom();
        detailRoom.setIdticketbooking("123456");

        //when and then
        assertThrows(Exception.class, () -> {
            staffRepositoryServices.createObject(detailRoom);
        });
    }

    @Test
    @DisplayName("save staff object difference")
    void saveOrUpdate() {

        //given
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setPassWordStaff("676767");
        staff.setStatus("On");

        //when
        staffRepositoryServices.saveOrUpdate(staff);

        //then
        verify(staffRepository).save(any());
    }

    @Test
    @DisplayName("save staff object new throw")
    void saveOrUpdateThrowException() {
        //given
        DetailRoom detailRoom = new DetailRoom();
        detailRoom.setIdticketbooking("123456");

        //then
        assertThrows(Exception.class, () -> {
            staffRepositoryServices.saveOrUpdate(detailRoom);
        });
    }

    @Test
    @DisplayName("save staff object new")
    void saveOrUpdateSave() {
        //given
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist 2");
        staff.setNameStaff("nameNew");
        staff.setPassWordStaff("676767");
        staff.setStatus("On");

        //when
        staffRepositoryServices.saveOrUpdate(staff);

        //then
        verify(staffRepository).save(any());
    }

    @Test
    @DisplayName("delete success")
    void deleteObjectById_ThenDeleteSucess() {
        //given
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");

        //when
        boolean result = staffRepositoryServices.deleteObjectById(STAFF_EXIST);

        //then
        assertThat(result).isTrue();
        verify(staffRepository).delete(any());
    }

    @Test
    @DisplayName("delete not success")
    void deleteObjectById_ThenDeleteIgnore() {
        //when
        boolean result = staffRepositoryServices.deleteObjectById("idExistXXX");
        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("not delete")
    void deleteObjectById_ThenNotDelete() {
        //when
        staffRepositoryServices.deleteObjectById("idExistXXXX");
    }

    @Test
    @DisplayName("load User ByUsername")
    void loadUserByUsername() {
        //given
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");
        staff.setRoleOfStaff("tieptan");

        //when
        staffRepositoryServices.loadUserByUsername(STAFF_EXIST);
    }

    @Test
    void changePassword() {
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");
        staff.setRoleOfStaff("tieptan");
        staff.setPassWordStaff("2333");

        ChangePassPojo change = new ChangePassPojo();
        change.setIdStaff(STAFF_EXIST);
        change.setPasswordNew("123");
        change.setPasswordOld("123");

        staffRepositoryServices.changePassword(change);

        //capture
        verify(staffRepository).save(staffEntity.capture());

        System.out.println(staffEntity.getValue());

        assertThat(staffEntity.getValue().getPassWordStaff()).isNotEqualTo("123");
    }

    @Test
    @DisplayName("Old Password error diff password in database")
    void changePasswordWrongOldPassword() {
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");
        staff.setRoleOfStaff("tieptan");
        staff.setPassWordStaff("2333");

        ChangePassPojo change = new ChangePassPojo();
        change.setIdStaff(STAFF_EXIST);
        change.setPasswordNew("123XXX");
        change.setPasswordOld("123XXX");

        assertThrows(BusinessException.class, ()-> {
            staffRepositoryServices.changePassword(change);
        });
    }

    @Test
    @DisplayName("Throw except when call entity management")
    void getThrowExceptionchangePasswordWrongOldPassword() {
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");
        staff.setRoleOfStaff("tieptan");
        staff.setPassWordStaff("2333");

        ChangePassPojo change = new ChangePassPojo();
        change.setIdStaff("throw");
        change.setPasswordNew("123XXX");
        change.setPasswordOld("123XXX");

        assertThrows(BusinessException.class, ()-> {
            staffRepositoryServices.changePassword(change);
        });
    }

    @Test
    @DisplayName("Not Change when not get entity in hibernate")
    void notchangePasswordWrongOldPassword() {
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("idExist");
        staff.setNameStaff("nameNew");
        staff.setStatus("On");
        staff.setRoleOfStaff("tieptan");
        staff.setPassWordStaff("2333");

        ChangePassPojo change = new ChangePassPojo();
        change.setIdStaff("null");
        change.setPasswordNew("123XXX");
        change.setPasswordOld("123XXX");

        assertThat(staffRepositoryServices.changePassword(change)).isFalse();


    }
}


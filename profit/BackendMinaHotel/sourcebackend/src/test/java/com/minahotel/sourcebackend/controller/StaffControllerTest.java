package com.minahotel.sourcebackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minahotel.sourcebackend.ConfigActiveTestProfilesCustomize;
import com.minahotel.sourcebackend.SourcebackendApplication;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.pojo.ChangePassPojo;
import com.minahotel.sourcebackend.services.StaffRepositoryServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = SourcebackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ConfigActiveTestProfilesCustomize
public class StaffControllerTest {

    private static final String STAFF_EXSIT = "1";

    @Autowired
    private MockMvc mvc;

    AutoCloseable autoCloseable;

    @InjectMocks
    StaffController StaffController;

    @MockBean
    StaffRepositoryServices staffRepositoryServices;


    @Captor
    ArgumentCaptor<StaffEntity> staffCaptor;

    List<StaffEntity> dsAll;
    StaffEntity  staffDataBuiltin;



    @BeforeEach
    void setUp() {
        dsAll = new ArrayList<StaffEntity>();
        StaffEntity staffEntity;
        for(var i =0;i< 10;i++){
            staffEntity = new StaffEntity();
            staffEntity.setIdStaff(""+i);
            staffEntity.setNameStaff("name "+ i);
            staffEntity.setStatus("On");
            dsAll.add(staffEntity);
        }

        staffDataBuiltin = new StaffEntity();
        staffDataBuiltin.setIdStaff("new");
        staffDataBuiltin.setNameStaff("name "+ "new");
        staffDataBuiltin.setStatus("On");
        autoCloseable = MockitoAnnotations.openMocks(this);

        Mockito.doReturn(dsAll).when(staffRepositoryServices).getAll();
        Mockito.doReturn(dsAll.stream().filter(s->s.getIdStaff().equals(STAFF_EXSIT)).findFirst().get()).when(staffRepositoryServices).getObjectById(Mockito.eq(STAFF_EXSIT));

        StaffEntity staff = new StaffEntity();
        Mockito.doReturn(true).when(staffRepositoryServices).createObject(AdditionalMatchers.not(Mockito.eq(staff)));
        Mockito.doThrow(CRUDExceptionCustomize.class).when(staffRepositoryServices).createObject(Mockito.eq(staff));

        ChangePassPojo changePassPojo = new ChangePassPojo();
        Mockito.doReturn(false).when(staffRepositoryServices).changePassword(Mockito.eq(changePassPojo));
        Mockito.doReturn(false).when(staffRepositoryServices).changePassword(AdditionalMatchers.not(Mockito.eq(changePassPojo)));
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getStaffById_givenId_ThenSuccess() throws Exception {
        mvc.perform(get("/staff?id={id}",STAFF_EXSIT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].idStaff",is(STAFF_EXSIT)));
    }

    @Test
    void getStaffById_givenAll_ThenSuccess() throws Exception {
        mvc.perform(get("/staff")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].nameStaff", is(dsAll.get(0).getNameStaff())));
    }

    @Test
    void newObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(staffDataBuiltin);
        MvcResult mvcResult = mvc.perform(post("/staff").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonValue))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        System.out.println("mvcResult.getResponse().getContentAsString(): "+mvcResult.getResponse().getContentAsString());

        verify(staffRepositoryServices).createObject(staffCaptor.capture());

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("true");
        assertThat(staffCaptor.getValue().getIdStaff()).isEqualTo("new");
    }

    @Test
    void newObjectThroexception() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StaffEntity staff = new StaffEntity();
        String jsonValue = mapper.writeValueAsString(staff);
        try{
            mvc.perform(post("/staff").contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonValue)).andReturn();
        }catch (NestedServletException ex){
            assertThat(ex.getCause().getClass()).isEqualTo(CRUDExceptionCustomize.class);
        }
    }

    @Test
    void saveOrUpdate() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(staffDataBuiltin);
        mvc.perform(put("/staff").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonValue)).andDo(print());
        verify(staffRepositoryServices).saveOrUpdate(staffCaptor.capture());
        assertThat(staffCaptor.getValue().getIdStaff()).isEqualTo("new");
    }

    @Test
    void deleteStaff() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue  = mapper.writeValueAsString(staffDataBuiltin);
        mvc.perform(delete("/staff").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void changePassword() throws Exception {
        ChangePassPojo objectChan = new ChangePassPojo();
        objectChan.setIdStaff("staff-02");
        objectChan.setPasswordNew("yes");
        objectChan.setPasswordOld("123");
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue  = mapper.writeValueAsString(objectChan);
        mvc.perform(post("/changepass").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> { Boolean.TRUE.equals(result);});
    }

    @Test
    void changePasswordWilFlase() throws Exception {
        ChangePassPojo objectChan = new ChangePassPojo();
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue  = mapper.writeValueAsString(objectChan);
        mvc.perform(post("/changepass").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> { Boolean.FALSE.equals(result);});
    }
}
package com.minahotel.sourcebackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import com.minahotel.sourcebackend.ConfigActiveTestProfilesCustomize;
import com.minahotel.sourcebackend.SourcebackendApplication;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.repository.StaffRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = SourcebackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ConfigActiveTestProfilesCustomize
public class StaffControllerIntegrationTest {

    private static final String STAFF_EXSIT = "staff_02";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StaffRepository staffRepository;

    private List<StaffEntity> allListStaff;

    @BeforeEach
    void setUp() {
        allListStaff=staffRepository.findAll();
    }

    @AfterEach
    void tearDown() throws Exception {

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
    void getStaffById_givenAllStatusOn_ThenSuccess() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/staff")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        int length = JsonPath
                .parse(mvcResult.getResponse().getContentAsString())
                .read("$.length()");

        System.out.println("length "+length);

        assertThat(length).isLessThanOrEqualTo(allListStaff.size());

        /*To test size of array: jsonPath("$", hasSize(4))
        To count members of object: jsonPath("$.*", hasSize(4))

        */

        /*
        View class JsonPath
        A JsonPath can be compiled and used as shown:

        JsonPath path = JsonPath.compile("$.store.book[1]"); List<Object> books = path.read(json); Or:

        List<Object> authors = JsonPath.read(json, "$.store.book[*].author")

        If the json path returns a single value (is definite): String author = JsonPath.read(json, "$.store.book[1].author"
    */
    }

    @Test
    void newObject() throws Exception {

        Faker faker = new Faker();
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff(faker.regexify("[a-b1-9]{10}"));
        staff.setNameStaff(faker.name().firstName());
        staff.setPassWordStaff(faker.numerify("#####"));
        staff.setStatus(faker.regexify("On|Off"));
        staff.setRoleOfStaff(faker.regexify("tieptan|admin|dichvu"));
        staff.setBonussalary(BigDecimal.ZERO);
        staff.setSalaryStaffByMonth(BigDecimal.ZERO);
        staff.setDateStartWork(LocalDate.now());

        System.out.println("new "+staff);

        ObjectMapper mapper = new ObjectMapper();
        // cast LocalDate to json
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String jsonValue = mapper.writeValueAsString(staff);
        MvcResult mvcResult = mvc.perform(post("/staff").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonValue))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("true");
        assertThat(staffRepository.findByidStaff(staff.getIdStaff()).isPresent()).isTrue();
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
        Faker faker = new Faker();
        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("staff_03");
        staff.setNameStaff(faker.name().firstName());
        staff.setPassWordStaff(faker.numerify("#####"));
        staff.setStatus(faker.regexify("On|Off"));
        staff.setRoleOfStaff(faker.regexify("tieptan|admin|dichvu"));
        staff.setBonussalary(BigDecimal.ZERO);
        staff.setSalaryStaffByMonth(BigDecimal.ZERO);
        staff.setDateStartWork(LocalDate.now());

        ObjectMapper mapper = new ObjectMapper();
        // cast LocalDate to json
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String jsonValue = mapper.writeValueAsString(staff);
        mvc.perform(put("/staff").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonValue))
                .andDo(print())
                .andExpect(result -> Boolean.TRUE.equals(result));

        Optional<StaffEntity> byidStaff = staffRepository.findByidStaff(staff.getIdStaff());
        assertThat(byidStaff.isPresent()).isTrue();
        assertThat(byidStaff.get().getNameStaff()).isEqualTo(staff.getNameStaff());
    }

    @Test
    void deleteStaff() throws Exception {
        Faker faker = new Faker();

        StaffEntity staff = new StaffEntity();
        staff.setIdStaff("staff_15");

        ObjectMapper mapper = new ObjectMapper();
        // cast LocalDate to json
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String jsonValue  = mapper.writeValueAsString(staff);

        mvc.perform(delete("/staff").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(staffRepository.findByidStaff(staff.getIdStaff()).isPresent()).isFalse();
    }

    @Test
    void changePassword() throws Exception {
//        ChangePassPojo objectChan = new ChangePassPojo();
//        objectChan.setIdStaff("staff-02");
//        objectChan.setPasswordNew("yes");
//        objectChan.setPasswordOld("123");
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonValue  = mapper.writeValueAsString(objectChan);
//        mvc.perform(post("/changepass").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(result -> { Boolean.TRUE.equals(result);});
    }

    @Test
    void changePasswordWilFlase() throws Exception {
//        ChangePassPojo objectChan = new ChangePassPojo();
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonValue  = mapper.writeValueAsString(objectChan);
//        mvc.perform(post("/changepass").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(result -> { Boolean.FALSE.equals(result);});
    }
}
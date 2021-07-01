package com.minahotel.sourcebackend.repository;

import com.minahotel.sourcebackend.entities.ProductionEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // not change database general use
class ProductionRepositoryTest {

    @Autowired
    ProductionRepository productionRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("find product by id exist item")
    void findByidProduct_GivenId_ThenFindExist() {
        //given
        String idProduct = "2021-03-15T07:11:31.699292600";

        //when
        Optional<ProductionEntity> byidProduct = productionRepository.findByidProduct(idProduct);

        //then
        assertThat(byidProduct.isPresent()).isTrue();
        assertThat(byidProduct.get().getIdProduct()).isEqualTo(idProduct);
    }

    @Test
    @DisplayName("find product by id not exist item")
    void findByidProduct_GivenId_NotExistItem() {
        //given
        String idProduct = "2021-02-26T19:38:49.538049400XX";

        //when
        Optional<ProductionEntity> byidProduct = productionRepository.findByidProduct(idProduct);

        //then
        assertThat(byidProduct.isPresent()).isFalse();
    }
}
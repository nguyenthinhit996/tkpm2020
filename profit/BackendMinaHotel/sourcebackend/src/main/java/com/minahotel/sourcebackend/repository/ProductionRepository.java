package com.minahotel.sourcebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.ProductionEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.ProductionRepositoryCustom;

/**
 * ProductionRepository is interface to action CrudRepository with Entity 
 * @author Peter
 *
 */
@Repository
public interface ProductionRepository extends CrudRepository<ProductionEntity, Long>, ProductionRepositoryCustom{

 public Optional<ProductionEntity> findByidProduct(String idProduct);

}

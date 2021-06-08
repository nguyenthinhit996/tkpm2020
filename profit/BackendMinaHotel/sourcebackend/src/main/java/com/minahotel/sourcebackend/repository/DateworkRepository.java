package com.minahotel.sourcebackend.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.DateWorkEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.DateworkRepositoryCustom;

@Repository
public interface DateworkRepository extends CrudRepository<DateWorkEntity, Long>, DateworkRepositoryCustom{

	public Optional<DateWorkEntity>  findByidDateWork(LocalDate localDate);
	
}

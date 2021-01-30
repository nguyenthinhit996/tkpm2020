package com.minahotel.sourcebackend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minahotel.sourcebackend.pojo.Dailyworking;


public interface DailyworkingRepository extends CrudRepository<Dailyworking, Long>{


	String queryFindObjectById = "select * from Dailyworking c where c.idtoday = :idtoday and c.idstaffwork like :idstaffwork";
	@Query(value = queryFindObjectById, nativeQuery = true )
	public List<Dailyworking> findObjectById(@Param("idtoday") String idtoday, @Param("idstaffwork") String idstaffwork );
	
	@Query(value = queryFindObjectById, nativeQuery = true )
	public Optional<Dailyworking> findObjectByIdOnlyOne(@Param("idtoday") LocalDate idtoday, @Param("idstaffwork") String idstaffwork );
	
	
}

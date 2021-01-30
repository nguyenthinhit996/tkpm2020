package com.minahotel.sourcebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minahotel.sourcebackend.pojo.CheckingOutRoomDamaged;


public interface CheckingOutRoomDamagedRepository extends CrudRepository<CheckingOutRoomDamaged, Long>{

	
	String queryFindObjectById = "select * from CheckingOutRoomDamaged c where c.idcheckingoutroomdamaded = :idcheckingoutroomdamaded";
	@Query(value = queryFindObjectById, nativeQuery = true )
	public List<CheckingOutRoomDamaged> findObjectById(@Param("idcheckingoutroomdamaded") int id);
	
	@Query(value = queryFindObjectById, nativeQuery = true )
	public Optional<CheckingOutRoomDamaged> findObjectByIdOnlyOne(@Param("idcheckingoutroomdamaded") int id);
	
}

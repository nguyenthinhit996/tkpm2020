package com.minahotel.sourcebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDetailsServicesEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.DetailservicesRepositoryCustom;

@Repository
public interface DetailservicesRepository extends CrudRepository<DetailsServicesEntity, Long>,DetailservicesRepositoryCustom {

	// cach 1
	public Optional<DetailsServicesEntity> findByidDetailsServicesEntity(CompositeKeyDetailsServicesEntity idDetailsServicesEntity);
	
	//cach 2
	@Query("select u from DetailsServicesEntity u where"
			+ " u.idDetailsServicesEntity.idTicketBooking = :idTicketBooking "
			+ "and u.idDetailsServicesEntity.idProduct = :idProduct")
	public Optional<DetailsServicesEntity> selectByidTicketBooking(
			 @Param("idTicketBooking") String idTicketBooking
			,@Param("idProduct") String idProduct);

	public List<DetailsServicesEntity> findByidDetailsServicesEntityIdTicketBooking(String idTicketBooking);
}

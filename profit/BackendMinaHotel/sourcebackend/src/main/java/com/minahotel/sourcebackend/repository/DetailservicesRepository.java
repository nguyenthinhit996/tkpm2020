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

/**
 * DetailservicesRepository is interface to action CrudRepository with Entity 
 * @author Peter
 *
 */

public interface DetailservicesRepository extends CrudRepository<DetailsServicesEntity, Long>,DetailservicesRepositoryCustom {

	// cach 1
	/**
	 * Find a DetailsServicesEntity by composite key CompositeKeyDetailsServicesEntity
	 * @param idDetailsServicesEntity
	 * @return Optional<DetailsServicesEntity>
	 */
	public Optional<DetailsServicesEntity> findByidDetailsServicesEntity(CompositeKeyDetailsServicesEntity idDetailsServicesEntity);
	
	//cach 2
	/**
	 * Find a DetailsServicesEntity by idTicketBooking and idProduct use Query native SQL
	 * @param idTicketBooking
	 * @param idProduct
	 * @return Optional<DetailsServicesEntity>
	 */
	@Query("select u from DetailsServicesEntity u where"
			+ " u.idDetailsServicesEntity.idTicketBooking = :idTicketBooking "
			+ "and u.idDetailsServicesEntity.idProduct = :idProduct")
	public Optional<DetailsServicesEntity> selectByidTicketBooking(
			 @Param("idTicketBooking") String idTicketBooking
			,@Param("idProduct") String idProduct);

	/**
	 * Find a DetailsServicesEntity from field of composite key CompositeKeyDetailsServicesEntity 
	 * CompositeKeyDetailsServicesEntity idDetailsServicesEntity , String idTicketBooking;
	 * @param idTicketBooking
	 * @return List<DetailsServicesEntity>
	 */
	public List<DetailsServicesEntity> findByidDetailsServicesEntityIdTicketBooking(String idTicketBooking);
}

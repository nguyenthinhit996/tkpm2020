package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.Detailservices;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.DetailservicesRepository;

@Service
public class DetailservicesRepositoryServices implements MinaHotelServices{

	@Autowired
	DetailservicesRepository detailservicesRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Detailservices>) detailservicesRepository.findAll();
	}

	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String... id) {
		return (List<Detailservices>)  detailservicesRepository.findObjectById(id[0], id[1]);
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			Detailservices objectConvertFromMina = (Detailservices) minapojo;
			detailservicesRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		Detailservices objectConvertFromMina = (Detailservices) minapojo;
		Detailservices result = detailservicesRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getIdticketbooking(),objectConvertFromMina.getIdproduct()).map( x ->{
			 x.setAmount(objectConvertFromMina.getAmount());
			 x.setEndrent(objectConvertFromMina.getEndrent());
			 x.setIdstaffservicesrepo(objectConvertFromMina.getIdstaffservicesrepo());
			 x.setStartrent(objectConvertFromMina.getStartrent());
			 x.setStatus(objectConvertFromMina.getStatus());
			 x.setSumaryservices(objectConvertFromMina.getSumaryservices());
			return detailservicesRepository.save(x);
		}).orElseGet(()->{
			return detailservicesRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}

	@Override
	public void deleteObject(MinaHoTelPojo minapojo) {
		Detailservices objectConvertFromMina = (Detailservices) minapojo;
		detailservicesRepository.delete(objectConvertFromMina);
	}

}

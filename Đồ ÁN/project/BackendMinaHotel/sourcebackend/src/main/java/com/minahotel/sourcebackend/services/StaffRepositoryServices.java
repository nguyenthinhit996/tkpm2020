package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.Staff;
import com.minahotel.sourcebackend.repository.StaffRepository;

@Service
public class StaffRepositoryServices  implements MinaHotelServices{
	
	// Spring Boot will create and configure DataSource and JdbcTemplate
    // To use it, just @Autowired
    @Autowired
    private StaffRepository staffRepository;
	
	public StaffRepositoryServices() {
		super();
	}
 
	 
	public boolean createObject(MinaHoTelPojo staff) {
		try {
			Staff staffConvertFromMina = (Staff) staff;
			staffRepository.save(staffConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean saveOrUpdate(MinaHoTelPojo staff) {
		Staff staffConvertFromMina = (Staff) staff;
		Staff result = staffRepository.findStaffByIdOnlyOne(staffConvertFromMina.getIdstaff()).map( x ->{
			x.setBonussalary(staffConvertFromMina.getBonussalary());
			x.setDatework(staffConvertFromMina.getDatework());
			x.setPass(staffConvertFromMina.getPass());
			x.setRole(staffConvertFromMina.getRole());
			x.setSalarymonth(staffConvertFromMina.getSalarymonth());
			x.setUsername(staffConvertFromMina.getUsername());
			return staffRepository.save(x);
		}).orElseGet(()->{
			return staffRepository.save(staffConvertFromMina);
		});
		return result != null ? true : false;
	}
	
	public void deleteObject(MinaHoTelPojo staff) {
		Staff staffConvertFromMina = (Staff) staff;
		staffRepository.delete(staffConvertFromMina);
	}


	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Staff>) staffRepository.findAll();
	}


	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String ...id) {
		return staffRepository.findStaffById(id[0]);
	}

	 
	
//	RowMapper<Staff> rowMappStaff = new RowMapper<>() {
//		public Staff mapRow(ResultSet set, int rowNum) throws SQLException {
//			Staff cus = new Staff();
//			cus.setIdstaff(set.getString(EnumStaff.IDSTAFF.getName()));
//			cus.setUsername(set.getString( EnumStaff.USERNAME.getName()));
//			cus.setPass(set.getString(EnumStaff.PASS.getName()));
//			cus.setRole(set.getString(EnumStaff.ROLE.getName()));
//			cus.setDatework(set.getDate(EnumStaff.DATEWORK.getName()).toLocalDate());
//			cus.setSalarymonth(set.getBigDecimal(EnumStaff.SALARYMONTH.getName()));
//			cus.setBonussalary(set.getBigDecimal(EnumStaff.BONUSSALARY.getName()));
//			return cus;
//		}
//	};
	
}

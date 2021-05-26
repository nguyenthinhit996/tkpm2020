package com.minahotel.sourcebackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.enums.EnumTicketAndRoom;
import com.minahotel.sourcebackend.pojo.ChangePassPojo;
import com.minahotel.sourcebackend.pojo.LoginPojo;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.Staff;
import com.minahotel.sourcebackend.pojo.UserCustomize;
import com.minahotel.sourcebackend.repository.StaffRepository;

@Service
public class StaffRepositoryServices implements MinaHotelServices, UserDetailsService {

	// Spring Boot will create and configure DataSource and JdbcTemplate
	// To use it, just @Autowired
	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	PasswordEncoder bCryptPasswordEncoder;

  

	public boolean createObject(MinaHoTelPojo staff) {
		try {
			List<Staff> ds = (List<Staff>) staffRepository.findAll();
			int max = 0;
			for (Staff in : ds) {
				String str = in.getIdstaff();
				int index = Integer.valueOf(str.split("_")[1]);
				if (index > max) {
					max = index;
				}
			}
			max += 1;
			String idStaff = "staff_" + max;
			Staff staffConvertFromMina = (Staff) staff;
			staffConvertFromMina.setPass("123"); // set default
			staffConvertFromMina.setIdstaff(idStaff);
			staffConvertFromMina.setStatus(EnumTicketAndRoom.ON.getName());
			staffConvertFromMina.setPass(bCryptPasswordEncoder.encode(staffConvertFromMina.getPass()));
			staffRepository.save(staffConvertFromMina);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean saveOrUpdate(MinaHoTelPojo staff) {
		Staff staffConvertFromMina = (Staff) staff;
		Staff result = staffRepository.findStaffByIdOnlyOne(staffConvertFromMina.getIdstaff()).map(x -> {
			x.setBonussalary(staffConvertFromMina.getBonussalary());
			x.setDatework(staffConvertFromMina.getDatework());
			x.setRole(staffConvertFromMina.getRole());
			x.setSalarymonth(staffConvertFromMina.getSalarymonth());
			x.setUsername(staffConvertFromMina.getUsername());
			x.setStatus(staffConvertFromMina.getStatus());
			return staffRepository.save(x);
		}).orElseGet(() -> {
			createObject(staff);
			return (Staff) staff;
		});
		return result != null ? true : false;
	}

	public boolean resetPassword(String idstaff) {
		Optional<Staff> result = staffRepository.findStaffByIdOnlyOne(idstaff);
		Staff staffSuccess = null;
		if (result.isPresent()) {
			Staff staff = result.get();
			staff.setPass(bCryptPasswordEncoder.encode("123"));
			staffSuccess = staffRepository.save(staff);
		}
		return staffSuccess != null ? true : false;
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
	public List<? extends MinaHoTelPojo> getObjectById(String... id) {
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

	public Boolean checkLogin(LoginPojo loginPojo) {
		Optional<Staff> staff = staffRepository.findStaffByIdOnlyOne(loginPojo.getIduser());
		if (staff.isPresent() && EnumTicketAndRoom.ON.getName().equals(staff.get().getStatus())) {
			boolean status = bCryptPasswordEncoder.matches(loginPojo.getPassword(), staff.get().getPass());
			if (status) {
				loginPojo.setRole(staff.get().getRole());
				loginPojo.setFullName(staff.get().getUsername());
				loginPojo.setAuthenticated(true);
				return true;
			}
		}
		return false;
	}

	public String changePass(ChangePassPojo object) {
		Optional<Staff> staff = staffRepository.findStaffByIdOnlyOne(object.getIdStaff());
		if (staff.isPresent()) {
			boolean status = bCryptPasswordEncoder.matches(object.getPasswordOld(), staff.get().getPass());
			if (status) {
				staff.get().setPass(bCryptPasswordEncoder.encode(object.getPasswordNew()));
				saveOrUpdate(staff.get());
				return "Update Password Success";
			} else {
				return "Password old incorrect";
			}
		} else {
			return "User not found";
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Staff> staffOption = staffRepository.findStaffIdstaff(username);
		UserCustomize user = null;
		if (staffOption.isPresent()) {

			Staff staff = staffOption.get();
			SimpleGrantedAuthority simplerole = new SimpleGrantedAuthority(staff.getRole());

			// String username, String password, boolean enabled, boolean accountNonExpired,
			// boolean credentialsNonExpired, boolean accountNonLocked,
			// Collection<? extends GrantedAuthority> authorities
			user = new UserCustomize(staff.getIdstaff(), staff.getUsername(), staff.getPass(), true, true, true, true, List.of(simplerole));
		}
		return user;

	}
}

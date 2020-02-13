package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.DoctorService;
import service.UserService;
import tools.Pager;
import entity.Doctor;
import entity.User;
import exception.ServiceException;

@Controller
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserService userService;

	@RequestMapping("/findDoctorByPage.do")
	public @ResponseBody
	HashMap<String, Object> toDoctorManage(Integer page,Integer limit, HttpServletRequest request) {
		Pager<Doctor> pager = null;
		try {
			pager = doctorService.findDoctorByPage(page, limit);
			request.setAttribute("page", page);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pager.getPageCount());
		map.put("data", pager.getContent());
		return map;
	}

	@RequestMapping("/removeDoctorById.do")
	public @ResponseBody String removeDoctorById(Integer did) {
		try {
			doctorService.removeDoctor(did);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "{\"msg\":\"2\"}";
		}
		return "{\"msg\":\"1\"}";
	}

	@RequestMapping("/addOrModifyDoctor.do")
	public @ResponseBody String addDoctor(Integer did,Integer uid,String name, String username, String password, String titel, String subject,
			String sex, String education) throws Exception {
		try {
			User user = new User();
			user.setId(uid);
			user.setUsername(username);
			user.setPassword(password);
			user.setRole(1);
			
			Doctor doctor = new Doctor();
			doctor.setDid(did);
			doctor.setEducation(education);
			doctor.setName(name);
			doctor.setSex(sex);
			doctor.setSubject(subject);
			doctor.setTitel(titel);
			doctor.setUser(user);
			
			if(did==null){
				userService.regist(user);
				doctorService.registDoctor(doctor);
			}else{
				doctorService.modifyDoctor(doctor);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			if("用户名已存在".equals(e.getMessage())){
				return "{\"msg\":\"3\"}";
			}
			return "{\"msg\":\"2\"}";
		}
		return "{\"msg\":\"1\"}";
	}
	
	@RequestMapping("/showDoctorAdd.do")
	public String toUpdateDoctor(Integer did,HttpServletRequest request){
		try {
			if(did!=null){
				Doctor doctor = doctorService.findDoctorById(did);
				request.setAttribute("doctor", doctor);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "doctor/doctorAdd";
	}
	
	@RequestMapping("getDoctorBySubject")
	public @ResponseBody List<Doctor> getDoctorBySubject(String subject){
		System.out.println(subject);
		List<Doctor> doctors = null;
		try {
			doctors = doctorService.findDoctorBySubject(subject);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doctors;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

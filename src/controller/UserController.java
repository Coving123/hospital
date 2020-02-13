package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.DoctorService;
import service.PatientService;
import service.NurseService;
import service.UserService;
import entity.Doctor;
import entity.Patient;
import entity.Nurse;
import entity.User;
import exception.DuplicateUsernameException;
import exception.ServiceException;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired 
	private NurseService nurseService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@ExceptionHandler(DuplicateUsernameException.class)
	public ModelAndView exceptionHandler(DuplicateUsernameException e){
		return new ModelAndView("regist","message",e.getMessage());
	}
	
	
	@RequestMapping("/doRegister.do")
	public @ResponseBody String regist(String username,String loginName,String password,String birth,
			String address,String phone,String sex)throws Exception{
		System.out.println("username = "+username);
		System.out.println("loginName = "+loginName);
		System.out.println("password = "+password);
		System.out.println("birth = "+birth);
		System.out.println("address = "+address);
		System.out.println("phone = "+phone);
		System.out.println("sex = "+sex);
		try {
			User user = new User();
			user.setUsername(loginName);
			user.setPassword(password);
			user.setRole(3);

			Patient patient = new Patient();
			patient.setUser(user);
			patient.setAddress(address);
			patient.setBirth(birth);
			patient.setName(username);
			patient.setPhone(phone);
			patient.setSex(sex);
			userService.regist(user);
			patientService.addPatient(patient);
			
		} catch (Exception e) {
			e.printStackTrace();
			if("用户名已存在".equals(e.getMessage())){
				return "{\"msg\":\"3\"}";
			}
			return "{\"msg\":\"2\"}";
		}
		return "{\"msg\":\"1\"}";
	}
	
	
	@RequestMapping("/ModifyPatient.do")
	public @ResponseBody String modify(Integer pid, Integer uid, String username,String password,String birth,
			String address,String phone,String sex,HttpServletRequest request)throws Exception{
		try {
			
			User user = new User();
			user.setId(uid);
			user.setPassword(password);
			user.setRole(3);

			Patient patient = new Patient();
			patient.setUser(user);
			patient.setAddress(address);
			patient.setBirth(birth);
			patient.setName(username);
			patient.setPhone(phone);
			patient.setSex(sex);
			patientService.modifyPatient(patient);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	return "";
	}
	
	
	@RequestMapping("/findPatient.do")
	public String toFindPatient(Integer pid,HttpServletRequest request){
		try {
			if(pid!=null){
				Patient patient = patientService.findPatientById(pid);
				request.setAttribute("patient", patient);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "user/patientList";
	}
	
	
	@RequestMapping(value="/doLogin.do",produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String doLogin(String username,String password,HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println(username);
		System.out.println(password);
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("doctor");
		request.getSession().removeAttribute("patient");
		request.getSession().removeAttribute("nurse");
		try {
			user = userService.login(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "{\"error\":\""+e.getMessage()+"\"}";
		}
		
		request.getSession().setAttribute("user", user);
		
		if(user.getRole()==1){//医生
			Doctor doctor = doctorService.findDoctorByUid(user.getId());
			request.getSession().setAttribute("doctor", doctor);
			return "{\"path\":\"toDoctorMain.do\"}";
		}else if(user.getRole()==2){//管理员
			return "{\"path\":\"toManagerMain.do\"}";
		}else if(user.getRole()==3){//患者
			Patient patient = patientService.findPatientByUid(user.getId());
			request.getSession().setAttribute("patient", patient);
			return "{\"path\":\"toPatientMain.do\"}";
		}else {//护士
			Nurse nurse = nurseService.findNurseByUid(user.getId());
			request.getSession().setAttribute("nurse", nurse);
			return "{\"path\":\"toNurseMain.do\"}";
		}
	}
	
	@RequestMapping(value="/doUserModify.do",produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String doModify(String password,HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			user.setPassword(password);
			userService.modifyUser(user);
			session.removeAttribute("user");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "";
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	public NurseService getNurseService() {
		return nurseService;
	}

	public void setNurseService(NurseService nurseService) {
		this.nurseService = nurseService;
	}
}










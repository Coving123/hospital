package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.AppointMent;
import entity.Doctor;
import entity.Patient;
import exception.ServiceException;

import service.AppointMentService;
import service.DoctorService;
import service.PatientService;
import tools.Pager;

@Controller
public class AppointMentController {
	
	@Autowired
	private AppointMentService appointMentService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	
	@RequestMapping("/findAppiontByPageByDoctor.do")
	public @ResponseBody
	HashMap<String, Object> findAppiontByPageByDoctor(Integer page,Integer limit, HttpServletRequest request) { 
		Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
		Pager<AppointMent> pager = null;
		try {
			pager = appointMentService.findAppointMentByDoctorAndPage(doctor.getDid(), page, limit);
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
	
	@RequestMapping("/findAppiontByPageByPatient.do")
	public @ResponseBody
	HashMap<String, Object> findAppiontByPageByPatient(Integer page,Integer limit, HttpServletRequest request) { 
		Patient patient = (Patient) request.getSession().getAttribute("patient");
		Pager<AppointMent> pager = null;
		try {
			pager = appointMentService.findAppointMentByPatientAndPage(patient.getPid(), page, limit);
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
	
	@RequestMapping("showOrderView")
	public String showOrderView(Integer aid,HttpServletRequest request){
		try {
			AppointMent appointMent = appointMentService.findAppointMentById(aid);
			request.setAttribute("appointMent", appointMent);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "order/orderView";
	}
	
	@RequestMapping("confirmOrder")
	public String confirmOrder(Integer aid){
		try {
			AppointMent appointMent = appointMentService.findAppointMentById(aid);
			appointMent.setFlag(2);
			appointMentService.modifyAppointMent(appointMent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showOrderView.do?aid="+aid;
	}
	
	@RequestMapping("toAppointMentAdd")
	public String toAppointMentAdd(Integer aid,HttpServletRequest request){
		try {
			if(aid!=null){
				AppointMent appointMent = appointMentService.findAppointMentById(aid);
				request.setAttribute("appointMent", appointMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "order/orderAdd";
	}
	
	//the mode of Appointment input
	@RequestMapping("addOrModifyAppointMent")
	public String addOrModifyAppointMent(Integer aid,Integer pid,Integer did,
			String pname,String dname,String description,String effectDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("aid = "+aid);
		try {
			if(aid!=null){
				System.out.println("修改");
				AppointMent appointMent = appointMentService.findAppointMentById(aid);
				appointMent.setEffectDate(effectDate);
				appointMent.setDescription(description);
				
				Doctor doctor = doctorService.findDoctorByDname(dname);
				if(doctor==null){
					throw new Exception("医生姓名无效");
				}
				appointMent.setDoctor(doctor);
				Patient patient = patientService.findPatientByPname(pname);
				if(patient==null){
					throw new Exception("病人姓名无效");
				}
				appointMent.setPatient(patient);
				appointMent.setCreateDate(sdf.format(new Date()));
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(effectDate));
				c.add(Calendar.HOUR, 1);
				appointMent.setExpireDate(sdf.format(c.getTime()));
				
				appointMent.setFlag(1);
				appointMentService.modifyAppointMent(appointMent);
			}else{
				System.out.println("添加");
				AppointMent appointMent = new AppointMent();
				appointMent.setEffectDate(effectDate);
				appointMent.setDescription(description);
				
				Doctor doctor = doctorService.findDoctorByDname(dname);
				if(doctor==null){
					throw new Exception("医生姓名无效");
				}
				appointMent.setDoctor(doctor);
				Patient patient = patientService.findPatientByPname(pname);
				if(patient==null){
					throw new Exception("病人姓名无效");
				}
				appointMent.setPatient(patient);
				
				appointMent.setCreateDate(sdf.format(new Date()));
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(effectDate));
				c.add(Calendar.HOUR, 1);
				appointMent.setExpireDate(sdf.format(c.getTime()));
				
				appointMent.setFlag(1);
				appointMentService.addAppointMent(appointMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "order/orderAdd";
	}
	
	@RequestMapping("removeAppointMentById")
	public @ResponseBody String removeAppointMent(Integer aid){
		try {
			appointMentService.removeAppointMent(aid);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public AppointMentService getAppointMentService() {
		return appointMentService;
	}

	public void setAppointMentService(AppointMentService appointMentService) {
		this.appointMentService = appointMentService;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
}

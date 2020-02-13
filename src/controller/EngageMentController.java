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

import entity.EngageMent;
import entity.Nurse;
import entity.Patient;
import exception.ServiceException;

import service.EngageMentService;
import service.NurseService;
import service.PatientService;
import tools.Pager;

@Controller
public class EngageMentController {
	
	@Autowired
	private EngageMentService engageMentService;
	@Autowired
	private NurseService nurseService;
	@Autowired
	private PatientService patientService;
	
	@RequestMapping("/findEngageByPageByNurse.do")
	public @ResponseBody
	HashMap<String, Object> findEngageByPageByNurse(Integer page,Integer limit, HttpServletRequest request) { 
		Nurse nurse = (Nurse) request.getSession().getAttribute("nurse");
		Pager<EngageMent> pager = null;
		try {
			pager = engageMentService.findEngageMentByNurseAndPage(nurse.getNid(), page, limit);
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
	
	@RequestMapping("/findEngageByPageByPatient.do")
	public @ResponseBody
	HashMap<String, Object> findEngageByPageByPatient(Integer page,Integer limit, HttpServletRequest request) { 
		Patient patient = (Patient) request.getSession().getAttribute("patient");
		Pager<EngageMent> pager = null;
		try {
			pager = engageMentService.findEngageMentByPatientAndPage(patient.getPid(), page, limit);
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
	
	@RequestMapping("showOrderViewN")
	public String showOrderViewN(Integer eid,HttpServletRequest request){
		try {
			EngageMent engageMent = engageMentService.findEngageMentById(eid);
			request.setAttribute("engageMent", engageMent);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "order/orderViewN";
	}
	
	@RequestMapping("confirmOrderN")
	public String confirmOrderN(Integer eid){
		try {
			EngageMent engageMent = engageMentService.findEngageMentById(eid);
			engageMent.setFlag(2);
			engageMentService.modifyEngageMent(engageMent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showOrderViewN.do?eid="+eid;
	}
	
	@RequestMapping("toEngageMentAdd")
	public String toEngageMentAdd(Integer eid,HttpServletRequest request){
		try {
			if(eid!=null){
				EngageMent engageMent = engageMentService.findEngageMentById(eid);
				request.setAttribute("engageMent", engageMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "order/orderAddN";
	}
	
	//the mode of Engagement input
	@RequestMapping("addOrModifyEngageMent")
	public String addOrModifyEngageMent(Integer eid,Integer pid,Integer nid,
			String pname,String nuname,String description,String effectDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("eid = "+eid);
		try {
			if(eid!=null){
				System.out.println("修改");
				EngageMent engageMent = engageMentService.findEngageMentById(eid);
				engageMent.setEffectDate(effectDate);
				engageMent.setDescription(description);
				
				Nurse nurse = nurseService.findNurseByNuname(nuname);
				if(nurse==null){
					throw new Exception("护士姓名无效");
				}
				engageMent.setNurse(nurse);
				Patient patient = patientService.findPatientByPname(pname);
				if(patient==null){
					throw new Exception("病人姓名无效");
				}
				engageMent.setPatient(patient);
				engageMent.setCreateDate(sdf.format(new Date()));
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(effectDate));
				c.add(Calendar.HOUR, 1);
				engageMent.setExpireDate(sdf.format(c.getTime()));
				
				engageMent.setFlag(1);
				engageMentService.modifyEngageMent(engageMent);
			}else{
				System.out.println("添加");
				EngageMent engageMent = new EngageMent();
				engageMent.setEffectDate(effectDate);
				engageMent.setDescription(description);
				
				Nurse nurse = nurseService.findNurseByNuname(nuname);
				if(nurse==null){
					throw new Exception("护士姓名无效");
				}
				engageMent.setNurse(nurse);
				Patient patient = patientService.findPatientByPname(pname);
				if(patient==null){
					throw new Exception("病人姓名无效");
				}
				engageMent.setPatient(patient);
				engageMent.setCreateDate(sdf.format(new Date()));
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(effectDate));
				c.add(Calendar.HOUR, 1);
				engageMent.setExpireDate(sdf.format(c.getTime()));
				
				engageMent.setFlag(1);
				engageMentService.addEngageMent(engageMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "order/orderAddN";
	}
	
	@RequestMapping("removeEngageMentById")
	public @ResponseBody String removeEngageMent(Integer eid){
		try {
			engageMentService.removeEngageMent(eid);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public EngageMentService getEngageMentService() {
		return engageMentService;
	}

	public void setEngageMentService(EngageMentService engageMentService) {
		this.engageMentService = engageMentService;
	}

	public NurseService getNurseService() {
		return nurseService;
	}

	public void setNurseService(NurseService nurseService) {
		this.nurseService = nurseService;
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
}
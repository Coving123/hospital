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

import entity.RequireMent;
import entity.User;
import entity.Nurse;
import entity.Patient;
import entity.Medicine;
import exception.ServiceException;

import service.RequireMentService;
import service.NurseService;
import service.PatientService;
import service.MedicineService;
import tools.Constants;
import tools.Pager;

@Controller
public class RequireMentController {
	
	@Autowired
	private RequireMentService requireMentService;
	@Autowired
	private NurseService nurseService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private MedicineService medicineService;
	
	
	@RequestMapping("/findRequireByPageByNurse.do")
	public @ResponseBody
	HashMap<String, Object> findRequireByPageByNurse(Integer page,Integer limit, HttpServletRequest request) throws ServiceException { 
		Nurse nurse = (Nurse) request.getSession().getAttribute("nurse");
		Pager<RequireMent> pager = null;
		try {
			pager = requireMentService.findRequireMentByNurseAndPage(nurse.getNid(), page, limit);
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
	
	@RequestMapping("/findRequireByPageByPatient.do")
	public @ResponseBody
	HashMap<String, Object> findRequireByPageByPatient(Integer page,Integer limit, HttpServletRequest request) throws ServiceException { 
		Patient patient = (Patient) request.getSession().getAttribute("patient");
//		Medicine medicine = (Medicine) request.getSession().getAttribute("medicine");
//		request.getSession().setAttribute("medicine", medicine);
		Pager<RequireMent> pager = null;
		try {
			pager = requireMentService.findRequireMentByPatientAndPage(patient.getPid(), page, limit);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		request.setAttribute("pager", pager);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pager.getPageCount());
		map.put("data", pager.getContent());
		return map;
	}
	
	@RequestMapping("showBookView")
	public String showOrderBook(Integer oid,HttpServletRequest request){
		try {
			RequireMent requireMent = requireMentService.findRequireMentById(oid);
			request.setAttribute("requireMent", requireMent);
			Medicine medicine = requireMent.getMedicine();
			request.setAttribute("medicine", medicine);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "book/bookView";
	}
	
	@RequestMapping("confirmBook")
	public String confirmBook(Integer oid, Integer total){
		try {
			RequireMent requireMent = requireMentService.findRequireMentById(oid);
			requireMent.setFlag(2);
			Medicine medicine = requireMent.getMedicine();
			int a = medicine.getCount();
			medicine.setCount(a-total);
			requireMentService.modifyRequireMent(requireMent);
			medicineService.modifyMedicine(medicine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showBookView.do?oid="+oid;
	}
	
	@RequestMapping("toRequireMentAdd")
	public String toRequireMentAdd(Integer oid,HttpServletRequest request){
		try {
			if(oid!=null){
				RequireMent requireMent = requireMentService.findRequireMentById(oid);
				request.setAttribute("requireMent", requireMent);
				Medicine medicine = requireMent.getMedicine();
				request.setAttribute("medicine", medicine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "book/bookAdd";
	}
	
	//the mode of Requirement input
	@RequestMapping("addOrModifyRequireMent")
	public String addOrModifyRequireMent(Integer oid,Integer mid,Integer pid,Integer nid,Integer total,String pname,String mname,
			String nuname){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("oid = "+oid);
		try {
			if(oid!=null){
				System.out.println("修改");
				RequireMent requireMent = requireMentService.findRequireMentById(oid);
				requireMent.setCreateDate(sdf.format(new Date()));
				requireMent.setTotal(total);
				
				Nurse nurse = nurseService.findNurseByNuname(nuname);
				if(nurse==null){
					throw new Exception("护士姓名无效");
				}
				requireMent.setNurse(nurse);
				
				Patient patient = patientService.findPatientByPname(pname);
				if(patient==null){
					throw new Exception("病人姓名无效");
				}
				requireMent.setPatient(patient);
				
				
				Medicine medicine = medicineService.findMedicineByMname(mname);
				if(medicine==null){
					throw new Exception("未找到药品名称");
				}
				requireMent.setMedicine(medicine);
				requireMent.setMoney(total*medicine.getPrice());
				
				requireMent.setFlag(1);
				requireMentService.modifyRequireMent(requireMent);
			}else{
				System.out.println("添加");
				
				RequireMent requireMent = new RequireMent();
				
				requireMent.setCreateDate(sdf.format(new Date()));
				requireMent.setTotal(total);
				
				Nurse nurse = nurseService.findNurseByNuname(nuname);
				if(nurse==null){
					throw new Exception("护士姓名无效");
				}
				requireMent.setNurse(nurse);
				
				Patient patient = patientService.findPatientByPname(pname);
				if(patient==null){
					throw new Exception("病人姓名无效");
				}
				requireMent.setPatient(patient);
				
				Medicine medicine = medicineService.findMedicineByMname(mname);
				if(medicine==null){
					throw new Exception("未找到药品名称");
				}
				requireMent.setMedicine(medicine);
				requireMent.setMoney(total*medicine.getPrice());
				
				requireMent.setFlag(1);
				requireMentService.addRequireMent(requireMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "book/bookAdd";
	}
	
	@RequestMapping("removeRequireMentById")
	public @ResponseBody String removeRequireMent(Integer oid){
		try {
			requireMentService.removeRequireMent(oid);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public RequireMentService getRequireMentService() {
		return requireMentService;
	}

	public void setRequireMentService(RequireMentService requireMentService) {
		this.requireMentService = requireMentService;
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

	public MedicineService getMedicineService() {
		return medicineService;
	}

	public void setMedicineService(MedicineService medicineService) {
		this.medicineService = medicineService;
	}
	
}


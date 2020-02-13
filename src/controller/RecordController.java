package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import service.DoctorService;
import service.PatientService;
import service.NurseService;
import service.RecordService;
import tools.Pager;
import entity.Doctor;
import entity.Patient;
import entity.Record;

@Controller
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private NurseService nurseService;

	@RequestMapping("/findRecordByPageByDoctor.do")
	public @ResponseBody
	HashMap<String, Object> toRecordManage(Integer page,Integer limit, HttpServletRequest request) {
		Pager<Record> pager = null;
		try {
			Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
			pager = recordService.findRecordByDoctorAndPage(doctor.getDid(), page, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pager.getPageCount());
		map.put("data", pager.getContent());
		return map;
	}
	
	@RequestMapping("/findRecordByPageByPatient.do")
	public @ResponseBody
	HashMap<String, Object> findRecordByPageByPatient(Integer page,Integer limit, HttpServletRequest request) {
		Pager<Record> pager = null;
		try {
			Patient patient = (Patient) request.getSession().getAttribute("patient");
			pager = recordService.findRecordByPatientAndPage(patient.getPid(), page, limit);
			request.setAttribute("patient", patient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pager.getPageCount());
		map.put("data", pager.getContent());
		return map;
	}
	
	@RequestMapping("/findRecordByPageByNurse.do")
	public @ResponseBody
	HashMap<String, Object> findRecordByPageByNurse(Integer page,Integer limit, HttpServletRequest request) {
		Pager<Record> pager = null;
		try {
			Patient patient = (Patient) request.getSession().getAttribute("patient");
			Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
			pager = recordService.findRecordByNurseAndPage(page, limit);
			request.setAttribute("patient", patient);
			request.setAttribute("doctor", doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pager.getPageCount());
		map.put("data", pager.getContent());
		return map;
	}
	
	@RequestMapping("/showRecordAdd.do")
	public String showRecordAdd(Integer rid,HttpServletRequest request){
		try {
			if(rid!=null){
				Record record = recordService.findRecordByRid(rid);
				request.setAttribute("record", record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "case/caseAdd";
	}
	
	@RequestMapping("/showRecordView.do")
	public String showRecordView(Integer rid,HttpServletRequest request){
		try {
			if(rid!=null){
				Record record = recordService.findRecordByRid(rid);
				request.setAttribute("record", record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "case/caseView";
	}
	
	@RequestMapping("/removeRecordById.do")
	public @ResponseBody String removeRecord(Integer rid){
		System.out.println("rid = "+rid);
		try {
			recordService.removceRecord(rid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/addOrModifyRecord.do")
	public @ResponseBody
	String addOrModifyRecord(Integer rid,Integer pid,Integer did,String dname,
			String pname,String symptom,String diagnosis,String prescription,
			String createDate){
		try {
			if(rid==null){
				Doctor doctor = doctorService.findDoctorByDname(dname);
				if(doctor==null){
					throw new Exception("医生姓名无效");
				}
				Patient patient = patientService.findPatientByPname(pname);
				if(patient==null){
					throw new Exception("患者姓名无效");
				}
				Record record = new Record();
				record.setDoctor(doctor);
				record.setPatient(patient);
				record.setSymptom(symptom);
				record.setPrescription(prescription);
				record.setDiagnosis(diagnosis);
				record.setCreateDate(createDate);
				recordService.addRecord(record);
			}else{
				Record record = new Record();
				record.setRid(rid);
				record.setDoctor(doctorService.findDoctorById(did));
				record.setPatient(patientService.findPatientById(pid));
				record.setCreateDate(createDate);
				record.setDiagnosis(diagnosis);
				record.setPrescription(prescription);
				record.setSymptom(symptom);
				recordService.modifyRecord(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
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

	public NurseService getNurseService() {
		return nurseService;
	}
	
	public void setNurseService(NurseService nurseService) {
		this.nurseService = nurseService;
	}
	
}

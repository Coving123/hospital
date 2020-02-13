package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MedicineDao;
import service.MedicineService;
import tools.Pager;
import entity.Medicine;
import exception.ServiceException;

@Controller
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	@Autowired
	private MedicineDao medicineDao;

	@RequestMapping("/findMedicineByPage.do")
	public @ResponseBody
	HashMap<String, Object> toMedicineManage(Integer page,Integer limit, HttpServletRequest request) {
		Pager<Medicine> pager = null;
		try {
			pager = medicineService.findMedicineByPage(page, limit);
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

	@RequestMapping("/removeMedicineById.do")
	public @ResponseBody String removeMedicineById(Integer mid) {
		try {
			medicineService.removeMedicine(mid);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "{\"msg\":\"2\"}";
		}
		return "{\"msg\":\"1\"}";
	}
	
	@RequestMapping("/showMedicineView.do")
	public String showMedicineView(Integer mid,HttpServletRequest request){
		try {
			if(mid!=null){
				Medicine medicine = medicineService.findMedicineById(mid);
				request.setAttribute("medicine", medicine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "medicine/medView";
	}

	@RequestMapping("/addOrModifyMedicine.do")
	public @ResponseBody String addMedicine(Integer mid, String name, String category, String description, double price,
		Integer count ) throws Exception {
		try {
			Medicine medicine = new Medicine();
			medicine.setMid(mid);
			medicine.setName(name);
			medicine.setCategory(category);
			medicine.setDescription(description);
			medicine.setPrice(price);
			medicine.setCount(count);
			
			if(mid==null){
				Medicine m = medicineDao.selectByName(medicine.getName());
				if(m!=null) {throw new ServiceException("药品已存在");}
				medicineService.addMedicine(medicine);
			}else{
				medicineService.modifyMedicine(medicine);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			if("药品已存在".equals(e.getMessage())){
				return "{\"msg\":\"3\"}";
			}
			return "{\"msg\":\"2\"}";
		}
		return "{\"msg\":\"1\"}";
	}
	
	@RequestMapping("/showMedicineAdd.do")
	public String toUpdateMedicine(Integer mid,HttpServletRequest request){
		try {
			if(mid!=null){
				Medicine medicine = medicineService.findMedicineById(mid);
				request.setAttribute("medicine", medicine);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "medicine/medAdd";
	}
	
	@RequestMapping("getMedicineByCategory")
	public @ResponseBody List<Medicine> getMedicineByCategory(String category){
		System.out.println(category);
		List<Medicine> medicines = null;
		try {
			medicines = medicineService.findMedicineByCategory(category);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medicines;
	}
	
	
//	@RequestMapping("getPriceByMedicine")
//	public @ResponseBody List<Medicine> getPriceByMedicine(String mname){
//		List<Medicine> price = null;
//		try {
//			price = medicineService.findPriceByMedicine(mname);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return price;
//	}
	
	public MedicineService getMedicineService() {
		return medicineService;
	}

	public void setMedicineService(MedicineService medicineService) {
		this.medicineService = medicineService;
	}
	
}

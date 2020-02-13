package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.NurseService;
import service.UserService;
import tools.Pager;
import entity.Nurse;
import entity.User;
import exception.ServiceException;

@Controller
public class NurseController {

	@Autowired
	private NurseService nurseService;
	@Autowired
	private UserService userService;

	@RequestMapping("/findNurseByPage.do")
	public @ResponseBody
	HashMap<String, Object> toNurseManage(Integer page,Integer limit, HttpServletRequest request) {
		Pager<Nurse> pager = null;
		try {
			pager = nurseService.findNurseByPage(page, limit);
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

	@RequestMapping("/removeNurseById.do")
	public @ResponseBody String removeNurseById(Integer nid) {
		try {
			nurseService.removeNurse(nid);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "{\"msg\":\"2\"}";
		}
		return "{\"msg\":\"1\"}";
	}

	@RequestMapping("/addOrModifyNurse.do")
	public @ResponseBody String addNurse(Integer nid,Integer uid,String name, String username, String password, String titel, String subject,
			String sex, String education) throws Exception {
		try {
			User user = new User();
			user.setId(uid);
			user.setUsername(username);
			user.setPassword(password);
			user.setRole(4);
			
			Nurse nurse = new Nurse();
			nurse.setNid(nid);
			nurse.setEducation(education);
			nurse.setName(name);
			nurse.setSex(sex);
			nurse.setSubject(subject);
			nurse.setTitel(titel);
			nurse.setUser(user);
			
			if(nid==null){
				userService.regist(user);
				nurseService.registNurse(nurse);
			}else{
				nurseService.modifyNurse(nurse);
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
	
	@RequestMapping("/showNurseAdd.do")
	public String toUpdateNurse(Integer nid,HttpServletRequest request){
		try {
			if(nid!=null){
				Nurse nurse = nurseService.findNurseById(nid);
				request.setAttribute("nurse", nurse);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "nurse/nurseAdd";
	}
	
	@RequestMapping("getNurseBySubject")
	public @ResponseBody List<Nurse> getNurseBySubject(String subject){
		System.out.println(subject);
		List<Nurse> nurses = null;
		try {
			nurses = nurseService.findNurseBySubject(subject);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nurses;
	}

	public NurseService getNurseService() {
		return nurseService;
	}

	public void setNurseService(NurseService nurseService) {
		this.nurseService = nurseService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
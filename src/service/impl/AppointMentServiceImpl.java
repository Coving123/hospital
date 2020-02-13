package service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.AppointMentService;
import tools.Pager;
import dao.AppointMentDao;
import entity.AppointMent;
import exception.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class AppointMentServiceImpl implements AppointMentService {

	@Autowired
	private AppointMentDao appointMentDao;

	public void addAppointMent(AppointMent appointMent) throws ServiceException {
		try {
			appointMentDao.insert(appointMent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加预约出错", e);
		}
	}

	public void modifyAppointMent(AppointMent appointMent) throws ServiceException {
		try {
			appointMentDao.update(appointMent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改预约出错", e);
		}
	}

	public AppointMent findAppointMentById(Integer aid) throws ServiceException {
		AppointMent appointMent = null;
		try {
			appointMent = appointMentDao.selectById(aid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取预约信息出错", e);
		}
		return appointMent;
	}

	public Pager<AppointMent> findAppointMentByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize)
			throws ServiceException {
		setTimeOut();
		Pager<AppointMent> pager = new Pager<AppointMent>();
		try {
			pager.setContent(appointMentDao.getAppointMentByPatientAndPage(pid, pageNo, pageSize));
			int totalCount = appointMentDao.getCountOfAppointMentByPatient(pid);
			pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
			pager.setPageNo(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("分页查询病历信息出错", e);
		}
		return pager;
	}
	
	public List<AppointMent> findAppointMentByPatient(Integer pid, Integer pageNo, Integer pageSize) throws Exception{
		return (appointMentDao.getAppointMentByPatientAndPage(pid, pageNo, pageSize));
	}
	
	public Pager<AppointMent> findAppointMentByDoctorAndPage(Integer did, Integer pageNo, Integer pageSize)
			throws ServiceException {
		setTimeOut();
		Pager<AppointMent> pager = new Pager<AppointMent>();
		try {
			pager.setContent(appointMentDao.getAppointMentByDoctorAndPage(did, pageNo, pageSize));
			int totalCount = appointMentDao.getCountOfAppointMentByDoctor(did);
			pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
			pager.setPageNo(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("分页查询病历信息出错", e);
		}
		return pager;
	}

	public AppointMentDao getAppointMentDao() {
		return appointMentDao;
	}

	public void setAppointMentDao(AppointMentDao appointMentDao) {
		this.appointMentDao = appointMentDao;
	}

	public void removeAppointMent(Integer aid) throws ServiceException {
		try {
			AppointMent appointMent = appointMentDao.selectById(aid);
			if(appointMent!=null){
				appointMentDao.remove(appointMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("移除预约出错", e);
		}
	}
	
	public void setTimeOut(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<AppointMent> appointMents = appointMentDao.getAllAppointMent();
			for (AppointMent appointMent : appointMents) {
				if(sdf.parse(appointMent.getExpireDate()).getTime()<new Date().getTime()&&appointMent.getFlag()==1){
					appointMent.setFlag(3);
					modifyAppointMent(appointMent);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

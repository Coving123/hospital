package service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.EngageMentService;
import tools.Pager;
import dao.EngageMentDao;
import entity.EngageMent;
import exception.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class EngageMentServiceImpl implements EngageMentService {

	@Autowired
	private EngageMentDao engageMentDao;

	public void addEngageMent(EngageMent engageMent) throws ServiceException {
		try {
			engageMentDao.insert(engageMent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加预约出错", e);
		}
	}

	public void modifyEngageMent(EngageMent engageMent) throws ServiceException {
		try {
			engageMentDao.update(engageMent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改预约出错", e);
		}
	}

	public EngageMent findEngageMentById(Integer eid) throws ServiceException {
		EngageMent engageMent = null;
		try {
			engageMent = engageMentDao.selectById(eid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取预约信息出错", e);
		}
		return engageMent;
	}

	public Pager<EngageMent> findEngageMentByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize)
			throws ServiceException {
		setTimeOut();
		Pager<EngageMent> pager = new Pager<EngageMent>();
		try {
			pager.setContent(engageMentDao.getEngageMentByPatientAndPage(pid, pageNo, pageSize));
			int totalCount = engageMentDao.getCountOfEngageMentByPatient(pid);
			pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
			pager.setPageNo(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("分页查询病历信息出错", e);
		}
		return pager;
	}

	public Pager<EngageMent> findEngageMentByNurseAndPage(Integer nid, Integer pageNo, Integer pageSize)
			throws ServiceException {
		setTimeOut();
		Pager<EngageMent> pager = new Pager<EngageMent>();
		try {
			pager.setContent(engageMentDao.getEngageMentByNurseAndPage(nid, pageNo, pageSize));
			int totalCount = engageMentDao.getCountOfEngageMentByNurse(nid);
			pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
			pager.setPageNo(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("分页查询病历信息出错", e);
		}
		return pager;
	}

	public EngageMentDao getEngageMentDao() {
		return engageMentDao;
	}

	public void setEngageMentDao(EngageMentDao engageMentDao) {
		this.engageMentDao = engageMentDao;
	}

	public void removeEngageMent(Integer eid) throws ServiceException {
		try {
			EngageMent engageMent = engageMentDao.selectById(eid);
			if(engageMent!=null){
				engageMentDao.remove(engageMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("移除预约出错", e);
		}
	}
	
	public void setTimeOut(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<EngageMent> engageMents = engageMentDao.getAllEngageMent();
			for (EngageMent engageMent : engageMents) {
				if(sdf.parse(engageMent.getExpireDate()).getTime()<new Date().getTime()&&engageMent.getFlag()==1){
					engageMent.setFlag(3);
					modifyEngageMent(engageMent);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
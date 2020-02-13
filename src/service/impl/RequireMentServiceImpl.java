package service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import entity.RequireMent;
import exception.ServiceException;
import service.RequireMentService;
import dao.RequireMentDao;
import tools.Pager;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class RequireMentServiceImpl implements RequireMentService{

	@Autowired
	private RequireMentDao requireMentDao;
	
	@Override
	public Pager<RequireMent> findRequireMentByNurseAndPage(Integer nid, Integer pageNo, Integer pageSize)
			throws ServiceException {
			setTimeOut();
			Pager<RequireMent> pager = new Pager<RequireMent>();
			try {
				pager.setContent(requireMentDao.getRequireMentByNurseAndPage(nid, pageNo, pageSize));
				int totalCount = requireMentDao.getCountOfRequireMentByNurse(nid);
				pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
				pager.setPageNo(pageNo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("分页查询信息出错", e);
			}
			return pager;
	}


	@Override
	public Pager<RequireMent> findRequireMentByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize)
			throws ServiceException {
		    setTimeOut();
		    Pager<RequireMent> pager = new Pager<RequireMent>();
		    try {
		    	pager.setContent(requireMentDao.getRequireMentByPatientAndPage(pid, pageNo, pageSize));
		    	int totalCount = requireMentDao.getCountOfRequireMentByPatient(pid);
		    	pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
		    	pager.setPageNo(pageNo);
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    		throw new ServiceException("分页查询信息出错", e);
		    		}
		    return pager;
	}
	
	@Override
	public RequireMent findRequireMentById(Integer oid) throws ServiceException {
		RequireMent requireMent = null;
		try {
			requireMent = requireMentDao.selectById(oid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取信息出错", e);
		}
		return requireMent;
	}

	@Override
	public void modifyRequireMent(RequireMent requireMent) throws ServiceException {
		try {
			requireMentDao.update(requireMent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改信息出错", e);
		}
		
	}

	@Override
	public void addRequireMent(RequireMent requireMent) throws ServiceException {
		try {
			requireMentDao.insert(requireMent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加信息出错", e);
		}
		
	}

	@Override
	public void removeRequireMent(Integer oid) throws ServiceException {
		try {
			RequireMent requireMent = requireMentDao.selectById(oid);
			if(requireMent!=null){
				requireMentDao.remove(requireMent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("移除信息出错", e);
		}
		
	}
	
	public void setOver(){
		try {
			List<RequireMent> requireMents = requireMentDao.getAllRequireMent();
			for (RequireMent requireMent : requireMents) {
				int count=1;
				int total=2;
				if(total>count){
					requireMent.setFlag(3);
					modifyRequireMent(requireMent);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setTimeOut(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<RequireMent> requireMents = requireMentDao.getAllRequireMent();
			for (RequireMent requireMent : requireMents) {
				String eff = requireMent.getCreateDate();
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(eff));
				c.add(Calendar.DAY_OF_MONTH,3);//三天后的日期
				requireMent.setExpireDate(sdf.format(c.getTime()));
				if(sdf.parse(requireMent.getExpireDate()).getTime()<new Date().getTime()&&requireMent.getFlag()==1){
					requireMent.setFlag(3);
					modifyRequireMent(requireMent);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RequireMentDao getRequireMentDao() {
		return requireMentDao;
	}
	
	public void setRequireMentDao(RequireMentDao requireMentDao) {
		this.requireMentDao = requireMentDao;
	}
	
}

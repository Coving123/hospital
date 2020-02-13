package service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.UserService;
import tools.Constants;
import dao.UserDao;
import entity.User;
import exception.DuplicateUsernameException;
import exception.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public void regist(User user) throws ServiceException {
		User u=userDao.selectByUsername(user.getUsername());
		if(u!=null){
			throw new ServiceException("用户名已存在");
		}
		userDao.insert(user);
	}
	
	public User login(User user) throws ServiceException{
		User u=userDao.selectByUsername(user.getUsername());
		if(u==null){
			throw new ServiceException(Constants.USER_IS_NOT_EXISTS);
		}else{
			if(user.getPassword().equals(u.getPassword())){
				return u;
			}else{
				throw new ServiceException(Constants.USERNAME_IS_NOT_MATCH_PASSWORD);
			}
		}
	}

	@Transactional(readOnly=true)
	public User findById(Integer id) {
		return userDao.selectById(id);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void modifyUser(User user) throws ServiceException {
		try {
			userDao.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("用户修改失败",e);
		}
	}

	@Override
	public void removeUser(Integer uid) {
		User user = findById(uid);
		if(user!=null){
			userDao.remove(user);
		}
	}
}

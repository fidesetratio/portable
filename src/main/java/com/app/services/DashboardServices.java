package com.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mapper.DashboardDao;
import com.app.model.DatabaseDatasource;
import com.app.model.DynamicQuery;
import com.app.model.Role;
import com.app.model.User;
import com.app.model.UserRegistration;
import com.app.model.UserView;

@Service
public class DashboardServices {
	@Autowired
	private SqlSession sqlSession;
	
	public List<DatabaseDatasource> getListDatasources(){
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		return dao.getListDatasources();
	}
	
	public DynamicQuery getQueryById(Integer queryId) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		return dao.getQueryById(queryId);
	}
	
	public User selectUsernameByPassword(String username, String password) {
		HashMap<String,Object> params =  new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		return dao.selectUsernameByPassword(params);	
	}

	public User selectUsernameByUsername(String username) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		return dao.selectUsernameByUsername(username);	
	
	}
	
	public Integer 	insertUser(UserRegistration user) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		
		return dao.insertUser(user);
		
	}
	
	public Integer selectTotalOfUsername(String username) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		return dao.selectTotalOfUsername(username);	
	}
	

	public Integer selectTotalOfEmail(String email) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		return dao.selectTotalOfEmail(email);	
	}
	
	
	public List<Role> selectRolesFromGroups(List<String> groupIds){
		ArrayList<Role> role = new ArrayList<Role>();
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		role = (ArrayList<Role>) dao.selectRolesFromGroups(groupIds);
		if(role.size()>0) {
			return role;
		}
		return role;
	}
	
	public void updateEmailByUserId(User user) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		 dao.updateEmailByUserId(user);	
	}
	
	public void updatePhotoByUserId(User user) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		 dao.updatePhotoByUserId(user);	
		
	}
	public List<UserView> selectAllUserNameAndGroup(){
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		return dao.selectAllUserNameAndGroup();
	}
	
	public void insertUserToGroupId(Integer userId, Integer groupId) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		map.put("group_id", groupId);
		dao.insertUserToGroupId(map);
		
	}
	
	
	public void deleteUser(User user) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		dao.deleteUser(user);
	}

	public void deleteUserGroup(User user) {
		DashboardDao dao=sqlSession.getMapper(DashboardDao.class);
		dao.deleteUserGroup(user);
	}
	
}

package com.app.mapper;

import java.util.HashMap;
import java.util.List;

import com.app.model.DatabaseDatasource;
import com.app.model.DynamicQuery;
import com.app.model.Role;
import com.app.model.User;
import com.app.model.UserRegistration;
import com.app.model.UserView;

public interface DashboardDao {
	public List<DatabaseDatasource> getListDatasources();
	public DynamicQuery getQueryById(Integer queryId);
	public User selectUsernameByPassword(HashMap<String,Object> params);
	public User selectUsernameByUsername(String username);
	public List<Role> selectRolesFromGroups(List<String> groupIds);
	public int selectTotalOfUsername(String username);
	public int selectTotalOfEmail(String email);
	public int insertUser(UserRegistration user);
	public void updateEmailByUserId(User user);
	public void updatePhotoByUserId(User user);
	
	public void insertUserToGroupId(HashMap map);
	
	public List<UserView> selectAllUserNameAndGroup();
	
	public void deleteUserGroup(User user);
	public void deleteUser(User user);
	
	
}

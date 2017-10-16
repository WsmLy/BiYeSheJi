package org.dao;

import org.model.TableUser;

public interface RegisterDao {
	public TableUser register(String userName, String password,String phone,String userImg);
}

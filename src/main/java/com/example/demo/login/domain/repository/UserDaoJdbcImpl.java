package com.example.demo.login.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;

@Repository
public class UserDaoJdbcImpl implements UserDao {

  @Autowired
  JdbcTemplate jdbc;

  @Override
  public int count() throws DataAccessException {
    int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
    return count;
  };

  @Override
  public int insertOne(User user) throws DataAccessException {
    int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
        +"password,"
        +"user_name,"
        +"birthday,"
        +"age,"
        +"marriage,"
        +"role)"
        + "VALUES(?,?,?,?,?,?,?)"
        ,user.getUserId()
        ,user.getPassword()
        ,user.getUserName()
        ,user.getBirthday()
        ,user.getAge()
        ,user.isMarriage()
        ,user.getRole());
        
        return rowNumber;
        
  };

  @Override
  public User selectOne(String userId) throws DataAccessException {
    return null;
  };

  @Override
  public List<User> selectMany(String userId) throws DataAccessException {
    List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
    List<User> useList = new ArrayList<>();
    for(Map<String, Object> map:getList) {
      User user = new User();
      
      user.setUserId((String)map.get("user_id"));
      user.setPassword((String)map.get("Password"));
    }
  };

  @Override
  public int updateOne(User user) throws DataAccessException {
    return 0;
  };

  @Override
  public int deleteOne(User user) throws DataAccessException {
    return 0;
  };

  @Override
  public void userService() throws DataAccessException {
  };
}

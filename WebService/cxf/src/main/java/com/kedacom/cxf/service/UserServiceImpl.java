package com.kedacom.cxf.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.kedacom.cxf.model.Log;
import com.kedacom.cxf.model.User;

/**
 * 用户服务实现类
 * @author lihongguang
 * @date 2018年8月23日
 */
@Service
public class UserServiceImpl implements UserService {

    private Map<Integer, User> users = new HashMap<>();
    private Map<Long, Log> logs = new HashMap<>();

    public UserServiceImpl() {
        init();
    }

    protected void init() {
        User user = new User();
        user.setId(1);
        user.setName("user1");
        users.put(1, user);

        Log log = new Log();
        log.setId(1L);
        log.setUserid(1);
        log.setDescription("Initialize");
        logs.put(1L, log);
    }

    /**
     * @see com.kedacom.cxf.service.UserService#getUser(java.lang.Integer)
     */

    @Override
    public User getUser(Integer id) {
        User u = users.get(id);
        return u;
    }

    /**
     * @see com.kedacom.cxf.service.UserService#putUser(com.kedacom.cxf.model.User)
     */

    @Override
    public Response putUser(User user) {
        Integer id = user.getId();
        User u = users.get(id);
        if (null != u && u.getName().equals(user.getName())) {
            return Response.notModified().build();
        }
        users.put(id, user);
        Long logid = System.currentTimeMillis();
        logs.put(logid, new Log(logid, id, "Update User"));
        return Response.ok().build();
    }

    /**
     * @see com.kedacom.cxf.service.UserService#deleteUser(java.lang.Integer)
     */

    @Override
    public Response deleteUser(Integer id) {
        User u = users.get(id);
        if (null == u) {
            return Response.notModified().build();
        }
        users.remove(id);
        Long logid = System.currentTimeMillis();
        logs.put(logid, new Log(logid, id, "Delete User"));
        return Response.ok().build();
    }

    /**
     * @see com.kedacom.cxf.service.UserService#getLogs()
     */

    @Override
    public Collection<Log> getLogs() {
        Collection<Log> list = logs.values();
        return list;
    }

}

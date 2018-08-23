package com.kedacom.cxf.service;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kedacom.cxf.model.Log;
import com.kedacom.cxf.model.User;

/**
 * 用户服务
 * @author lihongguang
 * @date 2018年8月23日
 */
@Path("/userSerivce")
@Produces(MediaType.TEXT_XML)
@Consumes(MediaType.TEXT_XML)
public interface UserService {

	@GET
	@Path("/getUser/{id}")
	public User getUser(@PathParam("id") Integer id);

	@POST
	@Path("/postLog")
	public Response postLog(Log log);

	@PUT
	@Path("/putUser")
	public Response putUser(User user);

	@DELETE
	@Path("/deleteUser/{id}")
	public Response deleteUser(@PathParam("id") Integer id);

	@GET
	@Path("/getLogs")
	public Collection<Log> getLogs();
}

package com.tutorialspoint;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/AAAService")
public class AAAService {
	
   radCheckBean bean = new radCheckBean();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";


   @GET
   @Path("/data")
   @Produces(MediaType.APPLICATION_XML)
   public List<Radcheckinfo> getAAAUsers(){
	   System.out.println("UserService.getAAAUsers() Entering");
      //return bean.getAllUsers();
      return bean.getAllUsers();
   }
   
   @GET
   @Path("/dataJson")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Radcheckinfo> getAAAUsersJson(){
      return bean.getAllUsers();
   }
   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createUser(
      @FormParam("username") String username,
      @FormParam("attribute") String attribute,
      @FormParam("op") String op,
      @FormParam("value") String value,
      @Context HttpServletResponse servletResponse) throws IOException{
	   System.out.println("AAAService.createUser() Username :"+username+" , attribute :"+attribute+" , op :"+op+" , value :"+value);
	   System.out.println("AAAService.createUser() Op :"+op);
      Radcheckinfo radcheck = new Radcheckinfo(username, attribute, op, value);
      int result = bean.addAAAUser(radcheck);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   
   
   @POST
   @Path("/usersJson")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createUserJson(
		   @FormParam("username") String username,
		   @FormParam("attribute") String attribute,
		   @FormParam("op") String op,
		   @FormParam("value") String value,
		   @Context HttpServletResponse servletResponse) throws IOException{
	   System.out.println("AAAService.createUserJson() Username :"+username+" , attribute :"+attribute+" , op :"+op+" , value :"+value);
	   Radcheckinfo radcheck = new Radcheckinfo(username, attribute, op, value);
	      int result = bean.addAAAUser(radcheck);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   @OPTIONS
   @Path("/data")
   @Produces(MediaType.APPLICATION_XML)
   public String getSupportedOperations(){
      return "<operations>GET, PUT, POST, DELETE</operations>";
   }
   
   
}
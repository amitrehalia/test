package com.tutorialspoint;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class UserClient  {

   private Client client;
   private String REST_SERVICE_URL = "http://localhost:8080/UserManagement/rest/UserService/users";
   
   private String REST_SERVICE_URL_JSON = "http://localhost:8080/UserManagement/rest/UserService/usersJson";
   
   private String REST_SERVICE_URL_AAA = "http://localhost:8080/AAAUserManagement/rest/AAAService/data";
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String PASS = "pass";
   private static final String FAIL = "fail";

   private void init(){
      this.client = ClientBuilder.newClient();
   }

   public static void main(String[] args){
	   UserClient tester = new UserClient();
      //initialize the tester
      tester.init();
      //test get all users Web Service Method
    // tester.testGetAllUsers();
      
      tester.testGetAllUsersAAA();
   //   tester.testGetAllUsersJson();
      
      //tester.testAddUserJSON();
      //test get user Web Service Method 
   /*   tester.testGetUser();
      //test update user Web Service Method
      tester.testUpdateUser();
      //test add user Web Service Method
      tester.testAddUser();
      //test delete user Web Service Method
   //   tester.testDeleteUser();
      tester.testGetAllUsers();*/
   }

   
   private void testGetAllUsersAAA(){
	      GenericType<List<Radcheckinfo>> list = new GenericType<List<Radcheckinfo>>() {};
	      List<Radcheckinfo> users = client
	         .target(REST_SERVICE_URL_AAA)
	         .request(MediaType.APPLICATION_XML)
	         .get(list);
	      String result = PASS;
	      if(users.isEmpty()){
	         result = FAIL;
	      }
	      System.out.println("Test case name: testGetAllUsers, Result: " + result );
	      
	      for(int i=0;i<users.size();i++){
	    	  System.out.println("UserClient.testGetAllUsers Id :"+users.get(i).getId()+" , Name :"+users.get(i).getUsername()+ ", Attribute :"+users.get(i).getAttribute());  
	      }
	      
	   }
   /*
   
   //Test: Get list of all users
   //Test: Check if list is not empty
   private void testGetAllUsers(){
      GenericType<List<User>> list = new GenericType<List<User>>() {};
      List<User> users = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .get(list);
      String result = PASS;
      if(users.isEmpty()){
         result = FAIL;
      }
      System.out.println("Test case name: testGetAllUsers, Result: " + result );
      
      for(int i=0;i<users.size();i++){
    	  System.out.println("UserClient.testGetAllUsers Id :"+users.get(i).getId()+" , Name :"+users.get(i).getName()+ ", Profession :"+users.get(i).getProfession());  
      }
      
   }
   private void testGetAllUsersJson(){
	      GenericType<List<User>> list = new GenericType<List<User>>() {};
	      List<User> users = client
	         .target(REST_SERVICE_URL_JSON)
	         .request(MediaType.APPLICATION_JSON)
	         .get(list);
	      String result = PASS;
	      if(users.isEmpty()){
	         result = FAIL;
	      }
	      System.out.println("Test case name: testGetAllUsers, Result: " + result );
	      
	      for(int i=0;i<users.size();i++){
	    	  System.out.println("UserClient.testGetAllUsers Id :"+users.get(i).getId()+" , Name :"+users.get(i).getName()+ ", Profession :"+users.get(i).getProfession());  
	      }
	      
	   }
   //Test: Get User of id 1
   //Test: Check if user is same as sample user
   private void testGetUser(){
      User sampleUser = new User();
      sampleUser.setId(1);

      User user = client
         .target(REST_SERVICE_URL)
         .path("/{userid}")
         .resolveTemplate("userid", 1)
         .request(MediaType.APPLICATION_XML)
         .get(User.class);
      String result = FAIL;
      if(sampleUser != null && sampleUser.getId() == user.getId()){
         result = PASS;
      }
      System.out.println("Test case name: testGetUser, Result: " + result );
   }
   //Test: Update User of id 1
   //Test: Check if result is success XML.
   private void testUpdateUser(){
      Form form = new Form();
      form.param("id", "1");
      form.param("name", "suresh");
      form.param("profession", "clerk");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .put(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testUpdateUser, Result: " + result );
   }
   //Test: Add User of id 2
   //Test: Check if result is success XML.
   private void testAddUser(){
      Form form = new Form();
      form.param("id", "2");
      form.param("name", "naresh");
      form.param("profession", "clerk");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
   
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testAddUser, Result: " + result );
   }
   
   private void testAddUserJSON(){
	      Form form = new Form();
	      form.param("id", "3");
	      form.param("name", "abcdjson");
	      form.param("profession", "cleaaark");

	      String callResult = client
	         .target(REST_SERVICE_URL_JSON)
	         .request(MediaType.APPLICATION_JSON)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	   
	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testAddUser, Result: " + result );
	   }
   //Test: Delete User of id 2
   //Test: Check if result is success XML.
   private void testDeleteUser(){
      String callResult = client
         .target(REST_SERVICE_URL)
         .path("/{userid}")
         .resolveTemplate("userid", 2)
         .request(MediaType.APPLICATION_XML)
         .delete(String.class);

      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testDeleteUser, Result: " + result );
   }*/
}
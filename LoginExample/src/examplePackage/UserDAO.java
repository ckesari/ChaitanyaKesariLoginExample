package examplePackage;


import java.util.ArrayList;
/*import java.text.*;
import java.sql.*;*/
import java.util.List;

   public class UserDAO 	
   {
      /*static Connection currentCon = null;
      static ResultSet rs = null;  */
	
	private static List<UserBean> userAccountArray;
	
	static{
		if(userAccountArray == null){
			userAccountArray = new ArrayList<UserBean>();
		}
		if(userAccountArray.isEmpty()){
			UserBean recruiter = new UserBean();
			recruiter.setFirstName("Skanatek");
			recruiter.setLastName("SMART TRANSPORTATION SYSTEMS");
			recruiter.setUserName("skanatek");
			recruiter.setPassword("welcome");
			userAccountArray.add(recruiter);

			UserBean submitter = new UserBean();
			submitter.setFirstName("Chaitanya");
			submitter.setLastName("Kesari");
			submitter.setUserName("ckesari");
			submitter.setPassword("welcome");
			userAccountArray.add(submitter);
		}
	}
	
      public static String login(UserBean user) {/*
	
         //preparing some objects for connection 
         Statement stmt = null;    
	
         String username = bean.getUsername();    
         String password = bean.getPassword();   
	    
         String searchQuery =
               "select * from users where username='"
                        + username
                        + "' AND password='"
                        + password
                        + "'";
	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("Your user name is " + username);          
      System.out.println("Your password is " + password);
      System.out.println("Query: "+searchQuery);
	    
      try 
      {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);	        
         boolean more = rs.next();
	       
         // if user does not exist set the isValid variable to false
         if (!more) 
         {
            System.out.println("Sorry, you are not a registered user! Please sign up first");
            bean.setValid(false);
         } 
	        
         //if user exists set the isValid variable to true
         else if (more) 
         {
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
	     	
            System.out.println("Welcome " + firstName);
            bean.setFirstName(firstName);
            bean.setLastName(lastName);
            bean.setValid(true);
         }
      } 

      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      } 
	    
      //some exception handling
      finally 
      {
         if (rs != null)	{
            try {
               rs.close();
            } catch (Exception e) {}
               rs = null;
            }
	
         if (stmt != null) {
            try {
               stmt.close();
            } catch (Exception e) {}
               stmt = null;
            }
	
         if (currentCon != null) {
            try {
               currentCon.close();
            } catch (Exception e) {
            }

            currentCon = null;
         }
      }

return bean;
	
      */
    	  String successStr="";
    	  
    	  for(UserBean bean: userAccountArray){
        	  if(bean.getUsername().equalsIgnoreCase(user.getUsername()) && bean.getPassword().equals(user.getPassword())){
        		  user.setValid(true);
        		  if(UserDAO.isStringNotEmpty(bean.getFirstName()) && UserDAO.isStringNotEmpty(bean.getLastName()))
        		  	  successStr = bean.getFirstName() + " " + bean.getLastName();
        		  else if(UserDAO.isStringNotEmpty(bean.getFirstName()) && !UserDAO.isStringNotEmpty(bean.getLastName()))
        			  successStr= bean.getFirstName();
        		  else if(!UserDAO.isStringNotEmpty(bean.getFirstName()) && UserDAO.isStringNotEmpty(bean.getLastName()))
        			  successStr = bean.getLastName(); 
        		  else 
        			  successStr= bean.getUsername();  
        		  break;
        	  }else if(bean.getUsername().equalsIgnoreCase(user.getUsername())){
        		  successStr = "Invalid Password";
        		  break;
        	  }
    	  }
          
    	  return successStr;
      
      }	 
      
      public static boolean isStringNotEmpty(String str){ //Usually we put this in Utility Class but not in DAO Class
    	  return (str != null && !str.isEmpty());
      }
      
   }
   
   
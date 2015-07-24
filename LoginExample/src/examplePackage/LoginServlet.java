package examplePackage;


//import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* Servlet implementation class LoginServlet
*/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public void loginAction(HttpServletRequest request, HttpServletResponse response) 
		       throws ServletException//, java.io.IOException 
		   {
		doGet( request,  response) ;
		   }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		       throws ServletException//, java.io.IOException 
		   {
		doGet( request,  response) ;
		   }

public void doGet(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException//, java.io.IOException 
   {
	
		String successStr="";
		
		try{
		
			UserBean user = new UserBean();
			HttpSession session = request.getSession();
			session.removeAttribute("validLogin");
			session.removeAttribute("invalidLogin");

			if(UserDAO.isStringNotEmpty(request.getParameter("un")) && UserDAO.isStringNotEmpty(request.getParameter("pw"))){
				user.setUserName(request.getParameter("un"));
				user.setPassword(request.getParameter("pw"));
			}else{
				session.setAttribute("invalidLogin", "Please make sure your Username and/or Password are not blank."); 
				response.sendRedirect("login/invalidLogin.jsp"); 
				return;
			}
			user.setValid(false);
			successStr = UserDAO.login(user);
			   
			if (user.isValid()){
				session.setAttribute("validLogin", successStr);
				response.sendRedirect("login/userLogged.jsp"); //logged-in page      		
			}else if(successStr.isEmpty()){
				session.setAttribute("invalidLogin", "Sorry, You are not a registered user! Please sign up first");
				response.sendRedirect("login/invalidLogin.jsp"); //error page 
			}else{
				session.setAttribute("invalidLogin", "Invalid Password");
				response.sendRedirect("login/invalidLogin.jsp"); 
			}
		
		}catch (Throwable theException){
			System.out.println(theException); 
		}
    }
}



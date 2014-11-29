package com.backlog.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backlog.dao.agency.UserDaoLocal;
import com.backlog.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private UserDaoLocal userDao;
       
	private void connection (User u ,  HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userDao.getAllUsers();
		for (User user : users) {
			if (user.getName().equals(u.getName())) {
				request.setAttribute("user", user);
		        request.getRequestDispatcher("AgencyList.jsp").forward(request, response);
			}
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String action = request.getParameter("action");
		 User u = new User(request.getParameter("UserName"));
		 
		 if("Add".equalsIgnoreCase(action)){
            userDao.addUser(u);
        }else if("Connect".equalsIgnoreCase(action)){
            connection(u, request , response);
        }
	}

}

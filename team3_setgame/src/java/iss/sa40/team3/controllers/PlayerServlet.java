package iss.sa40.team3.controllers;

import iss.sa40.team3.business.PlayerBean;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet{
    
    @EJB private PlayerBean playerBean;
    
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp){
        String type = req.getParameter("type");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String highscore = req.getParameter("highscore");
        
        if(type == "")
        
    }
}

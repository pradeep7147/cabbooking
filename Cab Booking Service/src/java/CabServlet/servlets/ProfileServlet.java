/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CabServlet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aaditya
 */
public class ProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static Connection con=null;
    static PreparedStatement pst=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
            HttpSession session = request.getSession();
            String name = (String)session.getAttribute("user");
             String uname = request.getParameter("username");
              String fname = request.getParameter("fullname");
               String email =  request.getParameter("email");
            String password = request.getParameter("password");
             String phone = request.getParameter("phonenumber");
             if(uname.isEmpty()||fname.isEmpty()||email.isEmpty()||password.isEmpty()||phone.isEmpty()){
                 
                 out.println("<script type=\"text/javascript\">");
                out.println("alert('Please provide all details : If you don't want to update ..Please provide previous details );");
                out.println("location='Signup.html';");
                out.println("</script>");
                 
             }
             
            try{
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "pradeep7147");
             try{
                pst = con.prepareStatement("update testdata1 set username = ?, fullname = ?, email = ?, password=?,phone=? where email = ?");
                pst.setString(1,uname);
                pst.setString(2,fname);
                pst.setString(3,email);
                pst.setString(4,password);
                pst.setString(5,phone);
                pst.setString(6,name);
                pst.executeUpdate();
             }
             catch(Exception e){
                 out.println(e);
             }
             }
            catch(Exception e){
               out.println(e);
            }
            
            out.println("<script type=\"text/javascript\">");
                out.println("alert('Profile Updated Successfully..');");
                out.println("location='Profile.html';");
                out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

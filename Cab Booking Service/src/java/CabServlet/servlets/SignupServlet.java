/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CabServlet.servlets;

import static CabServlet.servlets.HomeServlet.con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aaditya
 */
public class SignupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static Connection con;
     static PreparedStatement pst = null;
     static ResultSet rs = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignupServlet</title>");            
            out.println("</head>");
            out.println("<body>");
          //  out.println("<h1>Servlet SignupServlet at " + request.getContextPath() + "</h1>");
             String uname = request.getParameter("username");
              String fname = request.getParameter("fullname");
               String email =  request.getParameter("email");
            String password = request.getParameter("password");
             String phone = request.getParameter("mobilenumber");
             if(uname.isEmpty()||fname.isEmpty()||email.isEmpty()||password.isEmpty()||phone.isEmpty()){
                 
                 out.println("<script type=\"text/javascript\">");
                out.println("alert('Please provide all details :');");
                out.println("location='Signup.html';");
                out.println("</script>");
                 
             }
            try{
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "pradeep7147");
             try{
                pst = con.prepareStatement("Select username from testdata1 where email=?");
                pst.setString(1,email);
                rs = pst.executeQuery();
                rs.next();
                if(!rs.next()){
                    pst = con.prepareStatement("insert into testdata1 values(?,?,?,?,?)");
                
                pst.setString(1,uname);
                pst.setString(2,fname);
                pst.setString(3,email);
                pst.setString(4,password);
                pst.setString(5,phone);
                pst.executeUpdate(); 
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Your account created successful.Please Login...You will be redirected to Login Screen');");
                out.println("location='Login.html';");
                out.println("</script>");
                }
                else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User exits');");
                out.println("location='Signup.html';");
                out.println("</script>");
                }
             }
             catch(Exception e){
                 out.println(e);
             }
             }
            catch(Exception e){
               out.println(e);
            }
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

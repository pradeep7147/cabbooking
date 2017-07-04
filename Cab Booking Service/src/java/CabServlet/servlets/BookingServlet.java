/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CabServlet.servlets;

import static CabServlet.servlets.HomeServlet.pst;
import static CabServlet.servlets.SignupServlet.pst;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Scanner;
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
public class BookingServlet extends HttpServlet {

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
    static PreparedStatement pst = null;
          
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        long l = System.currentTimeMillis();
        l += 1L;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
            HttpSession session = request.getSession();
            String name = (String)session.getAttribute("user");
            String fromcity = request.getParameter("fromcity");
            String tocity = request.getParameter("tocity");
            String cabtype = request.getParameter("cabtype");
            String phonenumber = request.getParameter("phonenumber");
            String datetime = request.getParameter("datetime");
            if(fromcity.isEmpty()||tocity.isEmpty()||cabtype.isEmpty()||phonenumber.isEmpty()||datetime.isEmpty()){  
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Fields should not be empty :');");
                out.println("location='book.html';");
                out.println("</script>");
            }
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "pradeep7147");
                if(fromcity.equals(tocity)){
                    out.println("<script type=\"text/javascript\">");
                out.println("alert('To and From should not be same');");
                out.println("location='book.html';");
                out.println("</script>");
                }
                else{
                
                try{
                    pst = con.prepareStatement("insert into bookings values(?,?,?,?,?,?,?)");
            
                pst.setString(1,String.valueOf(l));
                pst.setString(2,name);
                pst.setString(3,fromcity);
                pst.setString(4,tocity);
                pst.setString(5,cabtype);
                pst.setString(6,phonenumber);
                pst.setString(7,datetime);
                pst.executeUpdate(); 
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Cab Booked Successfully..Your UID is "+l+"');");
                out.println("location='book.html';");
                out.println("</script>");
                }
             
             catch(Exception e){
                 out.println(e);
             }
                }
            }catch(Exception e){
                
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

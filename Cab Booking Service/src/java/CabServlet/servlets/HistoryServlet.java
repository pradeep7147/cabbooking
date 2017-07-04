/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CabServlet.servlets;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aaditya
 */
public class HistoryServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HistoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
           // out.println("<h1>Servlet HistoryServlet at " + request.getContextPath() + "</h1>");
             HttpSession session = request.getSession();
            String name = (String)session.getAttribute("user");
            try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "pradeep7147");
                        
                        PreparedStatement pst = con.prepareStatement("select * from bookings where username=?");
                        pst.setString(1,name);
                        ResultSet rst = pst.executeQuery();
                        out.println("<table border=1 width=50% height=50%>");
             out.println("<tr><th>UID</th><th>From</th><th>To</th><th>Cab Type</th><th>Date Time</th><tr>");
             while (rst.next()) {
                 String uid = rst.getString(1);
                 String fromcity = rst.getString(3);
                 String tocity = rst.getString(4);
                 String cabtype = rst.getString(5);
                 String datetime = rst.getString(7);
                 out.println("<tr><td>" + uid + "</td><td>" +fromcity + "</td><td>" + tocity + "</td><td>" + cabtype + "</td><td>" + datetime + "</td></tr>"); 
             }
             out.println("</table>");
                        
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

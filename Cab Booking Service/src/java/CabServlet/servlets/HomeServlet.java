/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CabServlet.servlets;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aaditya
 */
public class HomeServlet extends HttpServlet {

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
        String pass=null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
         //   out.println("<title>Servlet HomeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
           // out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            if(name.isEmpty() || password.isEmpty()){
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email or Password cannot be empty');");
                out.println("location='Login.html';");
                out.println("</script>");
                
            }
            
            
            
            
            try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "pradeep7147");
            try{     
                pst = con.prepareStatement("Select password from testdata1 where email=?");
                pst.setString(1,name);
                rs = pst.executeQuery();
                if(rs.first() == true){
                    pass = rs.getString("password");
                }
                else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Account does not exits');");
                out.println("location='Login.html';");
                out.println("</script>");     
                }
             if(pass.equals(password)){
                 RequestDispatcher rd = request.getRequestDispatcher("book.html");
                 HttpSession session = request.getSession();
                 session.setAttribute("user",name);
                rd.forward(request,response);
             }
             else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Wrong Password');");
                out.println("location='Login.html';");
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
            
            
            
           /*JdbcServiceImpl ser = new JdbcServiceImpl();
            String n = ser.getUserPassword(name);
            out.println(n);
            if(n.equals(password)){
                out.println("logged in");
            }
            else{
                out.println("oops...");
            }
           
            if(name.equals(password)){
                login = true;
                out.println("Welcome " +name+ " you are in ");
                HttpSession session = request.getSession();
                session.setAttribute("name",name);
            }
            else{
               
            } 
            if(login){
                RequestDispatcher rd = request.getRequestDispatcher("book.html");
                rd.forward(request,response);
            }
 */
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

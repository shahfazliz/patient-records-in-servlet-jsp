import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shahfazliz Shahron
 */
public class PatientRecord extends HttpServlet {
    String driverName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String dbName = "hospitalapi";
    String userId = "root";
    String password = "";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws ServletException, IOException {
        request
            .getRequestDispatcher("/patient_records.jsp")
            .forward(request, response);
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
    protected void doPost(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        try {
            Class.forName(driverName);
            
            this.connection = DriverManager.getConnection(
                this.connectionUrl + this.dbName + "?useTimezone=true&serverTimezone=UTC", 
                this.userId, 
                this.password);
            
            if (request.getParameter("id") != null) {
                switch (request.getParameter("attribute")) {
                    case "diagnosis":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patient_records SET diagnosis = ? WHERE id = ?");
                        break;
                    case "treatment":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patient_records SET treatment = ? WHERE id = ?");
                        break;
                    case "archive":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patient_records SET archive = ? WHERE id = ?");
                        break;
                }
                
                this
                    .preparedStatement
                    .setString(1, request.getParameter("value"));
                
                this
                    .preparedStatement
                    .setString(2, request.getParameter("id"));
            }
            
            else {
                this.preparedStatement = this
                    .connection
                    .prepareStatement("INSERT INTO patient_records (patient_id, doctor_id, diagnosis, treatment) VALUES (?,?,?,?)");

                this
                    .preparedStatement
                    .setString(1, request.getParameter("patient_id")); 
                this
                    .preparedStatement
                    .setString(2, request.getParameter("doctor_id"));
                this
                    .preparedStatement
                    .setString(3, request.getParameter("diagnosis"));
                this
                    .preparedStatement
                    .setString(4, request.getParameter("treatment"));
            }
            
            this
                .preparedStatement
                .executeUpdate();
            
            this.preparedStatement.close();
            this.connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request
            .getRequestDispatcher("/patient_records.jsp")
            .forward(request, response);
    }
}

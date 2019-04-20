import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Shahfazliz Shahron
 */
public class Patient extends HttpServlet {
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
            .getRequestDispatcher("/patients.jsp")
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
                    case "name":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patients SET name = ? WHERE id = ?");
                        break;
                    case "ic":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patients SET ic = ? WHERE id = ?");
                        break;
                    case "email":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patients SET email = ? WHERE id = ?");
                        break;
                    case "contact":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patients SET contact = ? WHERE id = ?");
                        break;
                    case "active":
                        this.preparedStatement = this
                            .connection
                            .prepareStatement("UPDATE patients SET active = ? WHERE id = ?");
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
                    .prepareStatement("INSERT INTO patients (name, ic, email, contact) VALUES (?,?,?,?)");

                this
                    .preparedStatement
                    .setString(1, request.getParameter("name")); 
                this
                    .preparedStatement
                    .setString(2, request.getParameter("ic"));
                this
                    .preparedStatement
                    .setString(3, request.getParameter("email"));
                this
                    .preparedStatement
                    .setString(4, request.getParameter("contact"));
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
            .getRequestDispatcher("/patients.jsp")
            .forward(request, response);
    }
}

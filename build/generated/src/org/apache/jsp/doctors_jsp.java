package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public final class doctors_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    String driverName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String dbName = "hospitalapi";
    String userId = "root";
    String password = "";

    try {
        Class.forName(driverName);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/head.jsp", out, false);
      out.write("\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function() {\n");
      out.write("                $('.delete').on('click', function() {\n");
      out.write("                    $.ajax({\n");
      out.write("                        url: '/doctor',\n");
      out.write("                        method: 'POST',\n");
      out.write("                        data: {\n");
      out.write("                            id: $(this).data('id'),\n");
      out.write("                            attribute: 'active',\n");
      out.write("                            value: 0\n");
      out.write("                        },\n");
      out.write("                        success: function() {\n");
      out.write("                            window.location.href = \"/doctor\";\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("                \n");
      out.write("                function showPencil{\n");
      out.write("                    $(this).append(' <span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\">');\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("                function removePencil() {\n");
      out.write("                    $(this)\n");
      out.write("                        .find('.glyphicon')\n");
      out.write("                        .remove();\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("                $('.editable')\n");
      out.write("                    .on('mouseover', showPencil)\n");
      out.write("                    .on('mouseout', removePencil)\n");
      out.write("                    .on('click', function() {\n");
      out.write("                        var td = $(this);\n");
      out.write("                \n");
      out.write("                        td\n");
      out.write("                            .find('.glyphicon')\n");
      out.write("                            .remove();\n");
      out.write("                    \n");
      out.write("                        td.off();\n");
      out.write("                        \n");
      out.write("                        var id = td.data('id');\n");
      out.write("                        var attribute = td.data('attribute');\n");
      out.write("                        \n");
      out.write("                        var textInput = $('<input type=\"text\" class=\"form-control\" name=\"' \n");
      out.write("                                + attribute \n");
      out.write("                                + '\" placeholder=\"' \n");
      out.write("                                + attribute.toUpperCase() \n");
      out.write("                                + '\" value=\"' \n");
      out.write("                                + $(this).html().trim()\n");
      out.write("                                + '\">')\n");
      out.write("                            .on('focusout',function() {\n");
      out.write("                                var inputValue = $(this).val();\n");
      out.write("                                $.ajax({\n");
      out.write("                                    url: '/doctor',\n");
      out.write("                                    method: 'POST',\n");
      out.write("                                    data: {\n");
      out.write("                                        id: id,\n");
      out.write("                                        attribute: attribute,\n");
      out.write("                                        value: inputValue\n");
      out.write("                                    },\n");
      out.write("                                    success: function() {\n");
      out.write("                                       td\n");
      out.write("                                        .html('')\n");
      out.write("                                        .append(inputValue)\n");
      out.write("                                        .on('mouseover', showPencil)\n");
      out.write("                                        .on('mouseout', removePencil);\n");
      out.write("                                    }\n");
      out.write("                                });\n");
      out.write("                            });\n");
      out.write("\n");
      out.write("                        $(this)\n");
      out.write("                            .html('')\n");
      out.write("                            .append(textInput);                        \n");
      out.write("                    });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body class=\"container\">\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/nav.jsp", out, false);
      out.write("\n");
      out.write("        \n");
      out.write("        <h1>Doctors</h1>\n");
      out.write("        <table class=\"table\">\n");
      out.write("            <tr>\n");
      out.write("                <th>Name</th>\n");
      out.write("                <th>IC</th>\n");
      out.write("                <th>Email</th>\n");
      out.write("                <th>Contact Number</th>\n");
      out.write("                <th>&nbsp;</th>\n");
      out.write("            </tr>\n");
      out.write("  \n");
      out.write("            ");

                try { 
                    connection = DriverManager.getConnection(
                        connectionUrl + dbName + "?useTimezone=true&serverTimezone=UTC", 
                        userId, 
                        password);
                    statement = connection.createStatement();
                    String sql = "SELECT * FROM doctors WHERE active = 1";

                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td\n");
      out.write("                    class=\"editable\" \n");
      out.write("                    data-id=\"");
      out.print(resultSet.getString("id") );
      out.write("\" \n");
      out.write("                    data-attribute=\"name\">");
      out.print(resultSet.getString("name") );
      out.write("</td>\n");
      out.write("                <td\n");
      out.write("                    class=\"editable\" \n");
      out.write("                    data-id=\"");
      out.print(resultSet.getString("id") );
      out.write("\" \n");
      out.write("                    data-attribute=\"ic\">");
      out.print(resultSet.getString("ic") );
      out.write("</td>\n");
      out.write("                <td\n");
      out.write("                    class=\"editable\" \n");
      out.write("                    data-id=\"");
      out.print(resultSet.getString("id") );
      out.write("\" \n");
      out.write("                    data-attribute=\"email\">");
      out.print(resultSet.getString("email") );
      out.write("</td>\n");
      out.write("                <td\n");
      out.write("                    class=\"editable\" \n");
      out.write("                    data-id=\"");
      out.print(resultSet.getString("id") );
      out.write("\" \n");
      out.write("                    data-attribute=\"contact\">");
      out.print(resultSet.getString("contact") );
      out.write("</td>\n");
      out.write("                <td>\n");
      out.write("                    <button \n");
      out.write("                        class=\"btn btn-default delete\" \n");
      out.write("                        data-id=\"");
      out.print(resultSet.getString("id") );
      out.write("\">\n");
      out.write("                        <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n");
      out.write("                    </button>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            ");
 
                    }
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("        \n");
      out.write("        <form action=\"/doctor\" method=\"POST\">\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label>Name</label>\n");
      out.write("                <input \n");
      out.write("                    type=\"text\" \n");
      out.write("                    class=\"form-control\" \n");
      out.write("                    name=\"name\" \n");
      out.write("                    placeholder=\"Name\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label>IC</label>\n");
      out.write("                <input \n");
      out.write("                    type=\"text\" \n");
      out.write("                    class=\"form-control\" \n");
      out.write("                    name=\"ic\" \n");
      out.write("                    placeholder=\"000000-00-0000\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label>Email</label>\n");
      out.write("                <input \n");
      out.write("                    type=\"email\" \n");
      out.write("                    class=\"form-control\" \n");
      out.write("                    name=\"email\" \n");
      out.write("                    placeholder=\"Email\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label>Contact Number</label>\n");
      out.write("                <input \n");
      out.write("                    type=\"text\" \n");
      out.write("                    class=\"form-control\" \n");
      out.write("                    name=\"contact\" \n");
      out.write("                    placeholder=\"000-0000000\">\n");
      out.write("            </div>\n");
      out.write("            <button \n");
      out.write("                type=\"submit\" \n");
      out.write("                class=\"btn btn-default\">Add New Doctor</button>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

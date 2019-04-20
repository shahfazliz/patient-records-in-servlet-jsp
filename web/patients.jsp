<%-- 
    Document   : patients
    Created on : Apr 14, 2019, 10:08:12 AM
    Author     : Shahfazliz Shahron
--%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
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
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%-- include common head elements --%>
        <jsp:include page="/head.jsp" />
        
        <script>
            function showPencil() {
                $(this).append(' <span class="glyphicon glyphicon-pencil" aria-hidden="true">');
            }

            function removePencil() {
                $(this)
                    .find('.glyphicon')
                    .remove();
            }

            function showInputField() {
                var td = $(this);

                td
                    .find('.glyphicon')
                    .remove();

                td.off();

                var id = td.data('id');
                var attribute = td.data('attribute');

                var textInput = $('<input type="text" class="form-control" name="' 
                        + attribute 
                        + '" placeholder="' 
                        + attribute.toUpperCase() 
                        + '" value="' 
                        + $(this).html().trim()
                        + '">')
                    .on('focusout',function() {
                        var inputValue = $(this).val();
                        $.ajax({
                            url: '/patient',
                            method: 'POST',
                            data: {
                                id: id,
                                attribute: attribute,
                                value: inputValue
                            },
                            success: function() {
                               td
                                .html('')
                                .append(inputValue)
                                .on('mouseover', showPencil)
                                .on('mouseout', removePencil)
                                .on('click', showInputField);
                            }
                        });
                    });

                $(this)
                    .html('')
                    .append(textInput);                        
            }
            
            $(document).ready(function() {
                $('.delete').on('click', function() {
                    $.ajax({
                        url: '/patient',
                        method: 'POST',
                        data: {
                            id: $(this).data('id'),
                            attribute: 'active',
                            value: 0
                        },
                        success: function() {
                            window.location.href = "/patient";
                        }
                    });
                });
                
                $('.editable')
                    .on('mouseover', showPencil)
                    .on('mouseout', removePencil)
                    .on('click', showInputField);
            });
        </script>
    </head>
    <body>
        <%-- include navigation --%>
        <jsp:include page="/nav.jsp" />
        
        <div class="container">
            <h1>Patients</h1>
            <table class="table">
                <tr>
                    <th>Name</th>
                    <th>IC</th>
                    <th>Email</th>
                    <th>Contact</th>
                    <th>&nbsp;</th>
                </tr>

                <%
                    try { 
                        connection = DriverManager.getConnection(
                            connectionUrl + dbName + "?useTimezone=true&serverTimezone=UTC", 
                            userId, 
                            password);
                        statement = connection.createStatement();
                        String sql = "SELECT * FROM patients WHERE active = 1";

                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                %>
                <tr>
                    <td
                        class="editable" 
                        data-id="<%=resultSet.getString("id") %>" 
                        data-attribute="name"><%=resultSet.getString("name") %></td>
                    <td
                        class="editable" 
                        data-id="<%=resultSet.getString("id") %>" 
                        data-attribute="ic"><%=resultSet.getString("ic") %></td>
                    <td
                        class="editable" 
                        data-id="<%=resultSet.getString("id") %>" 
                        data-attribute="email"><%=resultSet.getString("email") %></td>
                    <td
                        class="editable" 
                        data-id="<%=resultSet.getString("id") %>" 
                        data-attribute="contact"><%=resultSet.getString("contact") %></td>
                    <td>
                        <button 
                            class="btn btn-default delete" 
                            data-id="<%=resultSet.getString("id") %>">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </button>
                    </td>
                </tr>
                <% 
                        }
                        statement.close();
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </table>

            <form action="/patient" method="POST">
                <div class="form-group">
                    <label>Name</label>
                    <input 
                        type="text" 
                        class="form-control" 
                        name="name" 
                        placeholder="Name">
                </div>
                <div class="form-group">
                    <label>IC</label>
                    <input 
                        type="text" 
                        class="form-control" 
                        name="ic" 
                        placeholder="000000-00-0000">
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input 
                        type="email" 
                        class="form-control" 
                        name="email" 
                        placeholder="Email">
                </div>
                <div class="form-group">
                    <label>Contact Number</label>
                    <input 
                        type="text" 
                        class="form-control" 
                        name="contact" 
                        placeholder="000-0000000">
                </div>
                <button 
                    type="submit" 
                    class="btn btn-default">Add New Patient</button>
            </form>
        </div>
    </body>
</html>

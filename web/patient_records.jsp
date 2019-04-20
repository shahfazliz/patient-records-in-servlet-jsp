<%-- 
    Document   : index
    Created on : Apr 13, 2019, 6:19:58 PM
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

                var textInput = $('<textarea class="form-control" rows="5" name="' 
                        + attribute 
                        + '" placeholder="' 
                        + attribute.toUpperCase() 
                        + '">' 
                        + $(this).html().trim() 
                        + '</textarea>')
                    .on('focusout',function() {
                        var inputValue = $(this).val();
                        $.ajax({
                            url: '/patient_record',
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
                        url: '/patient_record',
                        method: 'POST',
                        data: {
                            id: $(this).data('id'),
                            attribute: 'archive',
                            value: 1
                        },
                        success: function() {
                            window.location.href = "/patient_record";
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
            <h1>Patient Records</h1>
            <table class="table">
                <tr>
                    <th>Patient</th>
                    <th>Doctor</th>
                    <th>Diagnosis</th>
                    <th>Treatment</th>
                    <th>&nbsp;</th>
                </tr>

                <%
                    try { 
                        connection = DriverManager.getConnection(
                            connectionUrl + dbName + "?useTimezone=true&serverTimezone=UTC", 
                            userId, 
                            password);
                        statement = connection.createStatement();
                        String sql = "SELECT "
                                + "pr.id, "
                                + "CASE "
                                    + "WHEN p.active = 1 "
                                    + "THEN p.name "
                                    + "ELSE CONCAT(p.name, ' (inactive)') "
                                + "END AS patient_name, "
                                + "CASE "
                                    + "WHEN d.active = 1 "
                                    + "THEN d.name "
                                    + "ELSE CONCAT(d.name, ' (inactive)') "
                                + "END AS doctor_name, "
                                + "pr.diagnosis, "
                                + "pr.treatment "
                                + "FROM patient_records pr "
                                    + "INNER JOIN patients p ON p.id = pr.patient_id "
                                    + "INNER JOIN doctors d ON d.id = pr.doctor_id "
                                + "WHERE pr.archive = 0";

                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                %>
                <tr>
                    <td><%=resultSet.getString("patient_name") %></td>
                    <td><%=resultSet.getString("doctor_name") %></td>
                    <td
                        class="editable" 
                        data-id="<%=resultSet.getString("id") %>" 
                        data-attribute="diagnosis"><%=resultSet.getString("diagnosis") %></td>
                    <td
                        class="editable" 
                        data-id="<%=resultSet.getString("id") %>" 
                        data-attribute="treatment"><%=resultSet.getString("treatment") %></td>
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

            <form action="/patient_record" method="POST">
                <div class="form-group">
                    <label>Patient</label>
                    <select class="form-control" name="patient_id">
                        <%
                            try { 
                                connection = DriverManager.getConnection(
                                    connectionUrl + dbName + "?useTimezone=true&serverTimezone=UTC", 
                                    userId, 
                                    password);
                                statement = connection.createStatement();
                                String sql = "SELECT * FROM patients";

                                resultSet = statement.executeQuery(sql);
                                while (resultSet.next()) {
                        %>
                        <option value="<%=resultSet.getString("id")%>"><%=resultSet.getString("name") %></option>
                        <% 
                                }
                                statement.close();
                                connection.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Doctor</label>
                    <select class="form-control" name="doctor_id">
                        <%
                            try { 
                                connection = DriverManager.getConnection(
                                    connectionUrl + dbName + "?useTimezone=true&serverTimezone=UTC", 
                                    userId, 
                                    password);
                                statement = connection.createStatement();
                                String sql = "SELECT * FROM doctors";

                                resultSet = statement.executeQuery(sql);
                                while (resultSet.next()) {
                        %>
                        <option value="<%=resultSet.getString("id")%>"><%=resultSet.getString("name") %></option>
                        <% 
                                }
                                statement.close();
                                connection.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Diagnosis</label>
                    <textarea 
                        class="form-control" 
                        rows="5" 
                        name="diagnosis"></textarea>
                </div>
                <div class="form-group">
                    <label>Treatment</label>
                    <textarea 
                        class="form-control" 
                        rows="5" 
                        name="treatment"></textarea>
                </div>
                <button 
                    type="submit" 
                    class="btn btn-default">Add New Patient Record</button>
            </form>
        </div>
    </body>
</html>

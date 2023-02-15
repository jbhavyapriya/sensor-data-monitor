<html>
  <head>
    <title>Display Timestamps</title>
  </head>
  <body>  

<form action="DisplaySensorDataServlet" method="post">
    <label>Choose a Timestamp:</label>
    <select id="timestamp" name="timestamp">
        <%@ page import = "java.util.List"%>
  <%
  List distinctTimestamps = (List)session.getAttribute("distinctTimestamps");
  for(int i = 0; i < distinctTimestamps.size(); i++) {
      String ts =  (String) distinctTimestamps.get(i);
  %>
      
      <option value="<%= ts %>"><%= ts %></option>
  <%
  }
  %>
    </select>
    <input type="submit">
  </form>
  
  </body>
  </html>
  
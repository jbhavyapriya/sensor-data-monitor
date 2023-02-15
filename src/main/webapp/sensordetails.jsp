<html>
  <head>
    <title>Display Sensor Details</title>
    <script src="https://cdn.anychart.com/releases/8.10.0/js/anychart-base.min.js"></script>
    <style type="text/css">      
      html, body, #container { 
        width: 100%; height: 100%; margin: 0; padding: 0; 
      } 
    </style>
  </head>
  <body>  

    <form action="DisplaySensorDataServlet" method="post">
      <label>Choose a Timestamp:</label>
      <select id="timestamp" name="timestamp">
    <%@ page import = "java.util.List"%>
    <%
    String selectedTs = (String)session.getAttribute("selectedTs");
    List distinctTimestamps = (List)session.getAttribute("distinctTimestamps");
    String selected = "";
    // Loop through the timestamp list, if the selected timestamp is equal to the timestamp in the list,
    // then select the timestamp and display in the dropdown
    for(int i = 0; i < distinctTimestamps.size(); i++) { //  "a"  "b"  "c"  "d"
        String ts =  (String) distinctTimestamps.get(i);
        
        if (ts.equals(selectedTs)) {
    %>
        
          <option value="<%= ts %>" selected><%= ts %></option>
       
    <%
        }
        else {
    %>
          <option value="<%= ts %>"><%= ts %></option>
    <%
        }
    }
    %>
      </select>
      <input type="submit">
    </form>

    <div id="container"></div>


<%@ page import = "com.bhavya.businesslogic.SensorDetails"%>

<%

List sensorDetails = (List)session.getAttribute("sensorDetails");
String result = "[";
for(int i = 0; i < sensorDetails.size(); i++) {
 SensorDetails sensorDetail = (SensorDetails) sensorDetails.get(i);
    result += "['" + sensorDetail.getSensorId() +"',"+sensorDetail.getValue()+"]";
    if(i < sensorDetails.size() - 1) {
        result += ", \n";
    }
}

result += "\n ]";
%>

<script>
  function getData() {
    return <%= result %>;
  }
  //var dataSet = anychart.data.set(getData());
  
  // map data for the line chart,
  // take x from the zero column and value from the first column
  //var seriesData = dataSet.mapAs({ x: 0, value: 1 });
  
  data=getData();
  
  // create a line chart
  var chart = anychart.line();
  chart.title('Temperatures of the sensors at a particular timestamp');

  // set the x axis title
   chart.xAxis().title('Sensor Id');
  // set the y axis title
  chart.yAxis().title('Temperature');
  var lineChart = chart.line(data);
  
  // set the container id for the line chart
  chart.container('container');
  
  // draw the line chart
  chart.draw();
  </script>
  </body>
</html>
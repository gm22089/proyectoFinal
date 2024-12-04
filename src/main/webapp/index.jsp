<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html">
<h:head>
  <title>Prueba WebSocket</title>
  <h:outputScript library="js" name="websocket.js"></h:outputScript>
</h:head>
<h:body>
  <h1>Prueba de WebSocket</h1>
  <button onclick="ws.send('Mensaje desde el botón')">Enviar Mensaje</button>
</h:body>
</html>

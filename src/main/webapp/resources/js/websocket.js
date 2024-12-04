let ws = new WebSocket("ws://localhost:90/websocket"); // Cambia el puerto según tu configuración.

ws.onopen = function() {
    console.log("Conexión establecida");
    ws.send("Hola desde el cliente");
};

ws.onmessage = function(event) {
    console.log("Mensaje recibido del servidor: " + event.data);
};

ws.onclose = function() {
    console.log("Conexión cerrada");
};

ws.onerror = function(error) {
    console.error("Error: " + error.message);
};

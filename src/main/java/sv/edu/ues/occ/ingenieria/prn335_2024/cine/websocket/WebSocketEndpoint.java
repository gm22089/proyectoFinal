package sv.edu.ues.occ.ingenieria.prn335_2024.cine.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.util.logging.Logger;

@ServerEndpoint("/websocket")
public class WebSocketEndpoint {

    private static final Logger LOGGER = Logger.getLogger(WebSocketEndpoint.class.getName());

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("Conexión abierta: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("Mensaje recibido: " + message);
        try {
            session.getBasicRemote().sendText("Respuesta del servidor: " + message);
        } catch (Exception e) {
            LOGGER.severe("Error al enviar mensaje: " + e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("Conexión cerrada: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOGGER.severe("Error en la conexión: " + throwable.getMessage());
    }
}


import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class  startServer {

    startServer()throws Exception{

        Server server = new Server(8080);
        server.setHandler(new Frontend());
        server.start();
        server.join();
    }
}

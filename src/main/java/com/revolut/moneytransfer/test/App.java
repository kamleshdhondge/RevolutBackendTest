package com.revolut.moneytransfer.test;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
/**Main app to create a server
 * @author kamlesh
 *
 */
public class App 
{
    public static final String CONTEXT_URL = "http://localhost:8090/";

    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();
        System.in.read();
        server.shutdownNow();
    }

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.revolut.moneytransfer.test.bankAccount.controller","com.revolut.moneytransfer.test.transaction.controller");
        rc.property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(CONTEXT_URL), rc);
    }
}

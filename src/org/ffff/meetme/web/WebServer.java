package org.ffff.meetme.web;

import org.apache.http.HttpResponseInterceptor;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.*;
import org.apache.log4j.Logger;
import org.ffff.meetme.Configuration;
import org.ffff.meetme.MMExecutors;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer implements Runnable {

    private static final Logger log = Logger.getLogger(WebServer.class);
    private final ServerSocket serverSocket;
    //private final ServerSocket serverSocketSSL;
    private final HttpParams httpParams;
    private final HttpService httpService;

    public WebServer() throws IOException {

        Configuration cfg = Configuration.getInstanse();
        this.serverSocket = new ServerSocket(cfg.getPort());
        this.httpParams = new SyncBasicHttpParams();
        this.httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000)
                .setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8 * 1024)
                .setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false)
                .setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);

        HttpProcessor httpProcessor = new ImmutableHttpProcessor(new HttpResponseInterceptor[]{
                new ResponseDate(),
                new ResponseServer(),
                new ResponseContent(),
                new ResponseConnControl()
        });

        HttpRequestHandlerRegistry registry = new HttpRequestHandlerRegistry();
        registry.register("/meetme/*", new RootHandler(cfg.getWebRoot()));
        //registry.register("/static/*", new StaticContentHandler(docRoot));
        //registry.register("/api/*", new ApiHandler());

        this.httpService = new HttpService(httpProcessor, new DefaultConnectionReuseStrategy(), new DefaultHttpResponseFactory(), registry, this.httpParams);

    }

    @Override
    public void run() {
        log.debug(String.format("Listening on port: %d", this.serverSocket.getLocalPort()));
        while (!Thread.interrupted()) {
            try {
                Socket socket = this.serverSocket.accept();
                DefaultHttpServerConnection connection = new DefaultHttpServerConnection();
                log.debug(String.format("Incoming connection from: %s", socket.getInetAddress().toString()));
                connection.bind(socket, this.httpParams);

                Runnable worker = new Worker(this.httpService, connection);
                MMExecutors.getInstance().getExecutor().execute(worker);

            } catch (InterruptedIOException e) {
                break;
            } catch (IOException e) {
                log.error(e, e);
                break;
            }
        }
    }


}

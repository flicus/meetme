package org.ffff.meetme.web;

import com.google.gson.Gson;
import org.apache.http.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.log4j.Logger;
import org.ffff.meetme.auth.AuthAdapterManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiHandler implements HttpRequestHandler {

    private final static Logger log = Logger.getLogger(ApiHandler.class);
    private static final Pattern pattern = Pattern.compile("/meetme/api/(.+)/?");
    private final Gson gson = new Gson();

    public ApiHandler() throws Exception {

    }

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        String target = (String) context.getAttribute("base");//request.getRequestLine().getUri();
        String method = request.getRequestLine().getMethod().toUpperCase();
        String command = null;
        Matcher m = pattern.matcher(target);
        if (m.find())
            command = m.group(1);

        switch (method) {
            case "GET":
                serveRead(command == null ? null : command.toLowerCase(), request, response, context);
                break;
            case "POST":
                serveWrite(command == null ? null : command.toLowerCase(), request, response, context);
                break;
            default:
                response.setStatusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
                response.setEntity(Util.makeMessageBody("Unsupported HTTP method"));
                log.debug("Unsupported HTTP method: " + method);
        }
    }

    private void serveWrite(String command, HttpRequest request, HttpResponse response, HttpContext context) throws IOException {
        Header[] headers = request.getHeaders("Cookie");
        if (headers != null && headers.length > 0) {
            Map<String, String> cookies = Util.parseQueryString(headers[0].getValue(), ";");
            String token = cookies.get("meetmeid");
            if (SessionManager.getInstance().checkToken(token) == null) {
                response.setStatusCode(HttpStatus.SC_FORBIDDEN);
                return;
            }
        }

        StringEntity entity;
        switch (command) {
            default:
                response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                response.setEntity(Util.makeMessageBody("Unknown api request"));
                log.debug("Unknown api request: " + command);
        }
    }

    private void serveRead(String command, HttpRequest request, HttpResponse response, HttpContext context) throws UnsupportedEncodingException {
        StringEntity entity;
        switch (command) {
            case "userinfo":
                WebUser webUser = (WebUser)context.getAttribute("webuser");
                response.setStatusCode(HttpStatus.SC_OK);
                if (webUser == null) webUser = new WebUser();
                String res = gson.toJson(webUser);
                entity = new StringEntity(res, ContentType.create("application/json", "UTF-8"));
                response.setEntity(entity);
                return;
            case "providers":
                Collection providers = AuthAdapterManager.getInstance().getAdapters();
                res = gson.toJson(providers);
                response.setStatusCode(HttpStatus.SC_OK);
                entity = new StringEntity(res, ContentType.create("application/json", "UTF-8"));
                response.setEntity(entity);
                return;
            case "callback":
                String target = (String) context.getAttribute("base");
                int pos = target.indexOf("#");
                Map<String, String> params = Util.parseQueryString(target.substring(pos), "&");
                String token = params.get("access_token");
                log.debug("access token: "+token);
                String mmsid = (String)context.getAttribute("mmsid");
                Session session = SessionManager.getInstance().checkToken(mmsid);
                session.set("token", token);
                response.setStatusCode(HttpStatus.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "/meetme/#meeting");
                return;
            default:
                response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                response.setEntity(Util.makeMessageBody("Unknown api request"));
                log.debug("Unknown api request: " + command);
        }

    }


}

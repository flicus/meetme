package org.ffff.meetme.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.log4j.Logger;
import org.ffff.meetme.auth.AuthAdapter;
import org.ffff.meetme.auth.AuthAdapterManager;
import org.ffff.meetme.model.Contact;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiHandler implements HttpRequestHandler {

    private final static Logger log = Logger.getLogger(ApiHandler.class);
    private static final Pattern pattern = Pattern.compile("/meetme/api(/.*)+");
    private static final Pattern providerPattern = Pattern.compile("/meetme/api/callback/(.*)/step1");

    private final Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE)
            .create();

    public ApiHandler() throws Exception {

    }

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        String target = (String) context.getAttribute("base");//request.getRequestLine().getUri();
        String method = request.getRequestLine().getMethod().toUpperCase();
        String command = null;
        Matcher m = pattern.matcher(target);
        if (m.find()) {
            String tmp = m.group(1).substring(1);
            int pos = tmp.indexOf("/");
            command = pos > 0 ?  tmp.substring(0, pos) : tmp;
        }

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
            case "meetings" :

                return;
            case "newmeeting" :

                return;
            case "updatemeeting" :

                return;
            case "deletemeeting":

                return;

            case "userinfo":
                String mmsid = (String)context.getAttribute("mmsid");
                Session session = SessionManager.getInstance().checkToken(mmsid);
                WebUser webUser = (WebUser)session.get("webuser");
                response.setStatusCode(HttpStatus.SC_OK);
                if (webUser == null) webUser = new WebUser();
                String res = gson.toJson(webUser);
                entity = new StringEntity(res, ContentType.create("application/json", "UTF-8"));
                response.setEntity(entity);
                return;
            case "providers":
                Collection providers = AuthAdapterManager.getInstance().getAdapterInfos();
                res = gson.toJson(providers);
                response.setStatusCode(HttpStatus.SC_OK);
                entity = new StringEntity(res, ContentType.create("application/json", "UTF-8"));
                response.setEntity(entity);
                return;
            case "callback":
                String target = request.getRequestLine().getUri();
                int pos = target.indexOf("?");
                Map<String, String> params = Util.parseQueryString(target.substring(pos+1), "&");
                String code = params.get("code");
                String state = params.get("state");
                log.debug("access code: "+code);
                mmsid = (String)context.getAttribute("mmsid");
                session = SessionManager.getInstance().checkToken(mmsid);

                //meetme/api/callback/yandex/step1
                //meetme/api/callback/vk/step1
                String providerId = null;
                Matcher m = providerPattern.matcher(target);
                if (m.find()) {
                    providerId = m.group(1);
                }
                AuthAdapter adapter = null;
                Object o = session.get(providerId);
                if (o == null) {
                    adapter = AuthAdapterManager.getInstance().makeAuthAdapter(providerId);
                    session.set(providerId, adapter);
                } else {
                    adapter = (AuthAdapter)o;
                }

                if (adapter.doStep2(code)) {
                    Contact contact = adapter.getUserInfo();
                    webUser = (WebUser)session.get("webuser");
                    if (webUser == null) {
                        webUser = new WebUser();
                        session.set("webuser", webUser);
                    }
                    webUser.setContact(contact);
                    webUser.setLoggedIn(true);
                    String json = gson.toJson(contact);
                    response.setEntity(new StringEntity(json, ContentType.create("application/json", "UTF-8")));
                    response.setStatusCode(HttpStatus.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", "/meetme/#meeting");
                    return;
                }
                response.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                response.setEntity(Util.makeMessageBody(adapter.getError()));
                return;
            default:
                response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
                response.setEntity(Util.makeMessageBody("Unknown api request"));
                log.debug("Unknown api request: " + command);
        }

    }


}

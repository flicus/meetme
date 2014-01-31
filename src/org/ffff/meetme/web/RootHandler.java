package org.ffff.meetme.web;

import org.apache.http.*;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RootHandler implements HttpRequestHandler {

    public static String newline = System.getProperty("line.separator");
    private static final Logger log = Logger.getLogger(RootHandler.class);
    private static final Pattern pattern = Pattern.compile("/meetme/(.*?)/.*");

    private StaticContentHandler staticContentHandler;
    private ApiHandler apiHandler;

    public RootHandler(String docRoot) {
        staticContentHandler = new StaticContentHandler(docRoot);
        try {
            apiHandler = new ApiHandler();
        } catch (Exception e) {
            log.error(e, e);
        }
    }

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        log.debug("Root handler");
        log.debug(requestInfo(request));
        String target = request.getRequestLine().getUri();
        String[] urlStrings = target.split("\\?");

        Header[] headers = request.getHeaders("Cookie");
        if (headers != null && headers.length > 0) {
            Map<String, String> cookies = Util.parseQueryString(headers[0].getValue(), ";");
            String token = cookies.get("mmsid");
            Session session = SessionManager.getInstance().checkToken(token);
            if (session != null) {
                context.setAttribute("mmsid", token);
            } else {
                token = SessionManager.getInstance().makeNewToken();
                context.setAttribute("mmsid", token);
                response.setHeader("Set-cookie", "mmsid="+token);
            }
        } else {
            String token = SessionManager.getInstance().makeNewToken();
            context.setAttribute("mmsid", token);
            response.setHeader("Set-cookie", "mmsid=" + token);
        }

        if ("/meetme/".equals(urlStrings[0])) {
            HttpRequest r = new BasicHttpRequest("get", "/index.html");
            context.setAttribute("base", "/index.html");
            staticContentHandler.handle(r, response, context);
            return;
        } else {
            context.setAttribute("base", urlStrings[0]);
            Matcher m = pattern.matcher(target);
            if (m.find()) {
                target = m.group(1);
            }
            switch (target) {
                case "api":
                    apiHandler.handle(request, response, context);
                    break;
                default:
                    context.setAttribute("base", urlStrings[0].replace("/meetme/", "/"));
                    staticContentHandler.handle(request, response, context);
                    break;
            }
        }
    }

    public static String requestInfo(HttpRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("RequestLine: %s", request.getRequestLine())).append(newline);
        for (Header header : request.getAllHeaders()) {
            sb.append(header.getName()).append(":").append(header.getValue()).append(newline);
        }
        return sb.toString();
    }
}

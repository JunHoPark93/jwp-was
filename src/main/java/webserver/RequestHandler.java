package webserver;

import webserver.handler.Handler;
import webserver.handler.HandlerList;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.request.RequestHeaderParser;
import webserver.view.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private static final String DEFAULT_ERROR_PATH = "./templates/error.html";

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequest httpRequest = RequestHeaderParser.parseRequest(new InputStreamReader(in, StandardCharsets.UTF_8));
            HttpResponse httpResponse = HttpResponse.of();

            Handler mappingHandler = getHandler(httpRequest);
            if (mappingHandler == null) {
                throw new RuntimeException("no mapping handler found");
            }

            ModelAndView mav = mappingHandler.handle(httpRequest, httpResponse);
            httpResponse = checkResponse(httpResponse);
            handleOutputStream(out, mav, httpResponse);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Handler getHandler(HttpRequest httpRequest) {
        for (Handler handler : HandlerList.LIST) {
            // TODO optional 을 반환할 것인가 null 리턴해도 괜찮은가
            Handler targetHandler = handler.getHandler(httpRequest.getDirectory());
            if (targetHandler != null) {
                return targetHandler;
            }
        }
        return null;
    }

    private HttpResponse checkResponse(HttpResponse httpResponse) {
        if (httpResponse.isNotInitialized()) {
            httpResponse = HttpResponse.createError();
        }
        return httpResponse;
    }

    private void handleOutputStream(OutputStream out, ModelAndView mav, HttpResponse httpResponse) throws IOException
            , URISyntaxException {
        DataOutputStream dos = new DataOutputStream(out);
        if (httpResponse.isNotInitialized() || httpResponse.hasError()) {
            byte[] body = FileIoUtils.loadFileFromClasspath(DEFAULT_ERROR_PATH);
            createResponse(httpResponse, dos, body);
            return;
        }
        byte[] body = FileIoUtils.loadFileFromClasspath(mav.getFullPath());

        createResponse(httpResponse, dos, body);
    }

    private void createResponse(HttpResponse httpResponse, DataOutputStream dos, byte[] body) {
        responseHeader(dos, body.length, httpResponse);
        responseBody(dos, body);
    }

    private void responseHeader(DataOutputStream dos, int lengthOfBodyContent, HttpResponse response) {
        try {
            dos.writeBytes("HTTP/1.1 " + response.getHttpStatusCode() + " " + response.getHttpReasonPhrase() + " \r\n");
            checkRedirect(dos, response);
            dos.writeBytes("Content-Type: " + response.getMediaType() + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void checkRedirect(DataOutputStream dos, HttpResponse response) throws IOException {
        if (response.isRedirect()) {
            dos.writeBytes("Location: " + response.getLocation() + " \r\n");
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

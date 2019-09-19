package resolver;

import model.http.HttpResponse;
import utils.FileIoUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

public class ViewResolverProxy {

    ViewResolver viewResolver;

    public ViewResolverProxy(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    public void resolve(HttpResponse httpResponse) {
        viewResolver.resolve(httpResponse);
    }

    // TODO
//    private void handleOutputStream(OutputStream out, String requestTarget, HttpResponse httpResponse, String dirPath) throws IOException, URISyntaxException {
//        DataOutputStream dos = new DataOutputStream(out);
//        byte[] body = FileIoUtils.loadFileFromClasspath("./" + dirPath + "/" + requestTarget);
//
//        responseHeader(dos, body.length, httpResponse);
//        responseBody(dos, body);
//    }
}

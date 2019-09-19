package resolver;

import handler.StaticResourcePath;
import model.http.HttpResponse;
import model.http.ModelAndView;
import utils.FileIoUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

// TODO
public class InternalResourceViewResolver implements ViewResolver {
    private ModelAndView mav;

    @Override
    public void resolve(HttpResponse httpResponse) {
//        if (httpResponse.hasError()) {
//            handleOutputStream(out, "error.html", httpResponse, TEMPLATE_PATH);
//            return;
//        }
    }

    @Override
    public ViewResolver getResolver(ModelAndView mav) {
        if (StaticResourcePath.contains(mav.getViewName())) {
            this.mav = mav;
            return this;
        }
        return null;
    }

//    private void handleOutputStream(OutputStream out, String requestTarget, HttpResponse httpResponse, String dirPath) throws IOException, URISyntaxException {
//        DataOutputStream dos = new DataOutputStream(out);
//        byte[] body = FileIoUtils.loadFileFromClasspath("./" + dirPath + "/" + requestTarget);
//
//        responseHeader(dos, body.length, httpResponse);
//        responseBody(dos, body);
//    }
}

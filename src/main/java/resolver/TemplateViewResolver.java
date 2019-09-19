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
public class TemplateViewResolver implements ViewResolver {
    @Override
    public void resolve(HttpResponse httpResponse) {
        //handleOutputStream(out, mav.getViewName(), httpResponse, TEMPLATE_PATH);
    }

    @Override
    public ViewResolver getResolver(ModelAndView viewName) {
//        if (!StaticResourcePath.contains(viewName)) {
//            return this;
//        }
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

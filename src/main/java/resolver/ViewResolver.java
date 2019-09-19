package resolver;

import model.http.HttpResponse;
import model.http.ModelAndView;

public interface ViewResolver {
    void resolve(HttpResponse httpResponse);

    ViewResolver getResolver(ModelAndView mav);
}

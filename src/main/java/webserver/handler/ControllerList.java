package webserver.handler;

import webserver.handler.controller.Controller;
import webserver.handler.controller.custom.HomeController;
import webserver.handler.controller.custom.UserController;
import webserver.handler.controller.resource.ResourceController;
import webserver.handler.controller.resource.TemplateController;
import webserver.view.ViewResolvers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ControllerList {
    private static final Controller[] CONCRETE_CONTROLLER = {
            new HomeController(ViewResolvers.TEMPLATE_RESOLVER),
            new ResourceController(ViewResolvers.STATIC_RESOLVER),
            new TemplateController(ViewResolvers.TEMPLATE_RESOLVER),
            new UserController(ViewResolvers.TEMPLATE_RESOLVER)
    };

    public static List<Controller> CONTROLLERS = Collections.unmodifiableList(Arrays.asList(CONCRETE_CONTROLLER));
}

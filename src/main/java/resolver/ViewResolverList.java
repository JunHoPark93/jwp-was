package resolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ViewResolverList {
    private static final ViewResolver[] VIEW_RESOLVERS = {new InternalResourceViewResolver(),
            new TemplateViewResolver()};
    public static final List<ViewResolver> VIEW_RESOLVER_LIST = Collections.unmodifiableList(Arrays.asList(VIEW_RESOLVERS));
}

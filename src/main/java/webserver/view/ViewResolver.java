package webserver.view;

import java.util.Map;

public interface ViewResolver {
    /**
     * 내려줄 데이터가 없는 경우 설정을 해준다.
     *
     * @param path 요청 path
     * @return ViewResolveResult
     */
    ViewResolveResult resolve(String path);

    /**
     * 내려줄 데이터가 있는 경우 설정을 해준다.
     *
     * @param path  요청 path
     * @param model 데이터
     * @return ViewResolveResult
     */
    ViewResolveResult resolve(String path, Map<String, Object> model);
}

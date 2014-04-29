package calc.view.desktop;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GREG on 28.04.2014.
 */
public class ApplicationContext {
    public final Map<String, Object> context = new HashMap<>();

    private ApplicationContext() {
    }

    public static class SingletonHolder {
        public static final ApplicationContext HOLDER_INSTANCE = new ApplicationContext();
    }

    public static ApplicationContext getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public Object getBean(String name) {
        return context.get(name);
    }

    public void setBean(String name, Object object) {
        context.put(name, object);
    }
}
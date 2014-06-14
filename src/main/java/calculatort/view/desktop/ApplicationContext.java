package calculatort.view.desktop;

import java.util.HashMap;
        import java.util.Map;

public class ApplicationContext {
    protected final Map<String, Object> context = new HashMap<>();

    private ApplicationContext() {
    }

    public static class SingletonHolder {
        public static final ApplicationContext HOLDER_INSTANCE = new ApplicationContext();
    }

    public static ApplicationContext getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public static Object getBean(String name) {
        return getInstance().context.get(name);
    }

    public static void setBean(String name, Object object) {
        getInstance().context.put(name, object);
    }
}
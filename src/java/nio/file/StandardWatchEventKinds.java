package java.nio.file;

/**
 * 定义标准的事件类型
 * @since 1.7
 */
public final class StandardWatchEventKinds {

    private StandardWatchEventKinds() {
    }

    /**
     * 一个特殊事件，表明事件可能已丢失或丢弃。
     *
     * 此事件的{@link WatchEvent#context context}是特定于实现的，可能是{@code null}。
     * 事件{@link WatchEvent#count count}可能大于{@code 1}。
     *
     * @see WatchService
     */
    public static final WatchEvent.Kind<Object> OVERFLOW =
            new StdWatchEventKind<Object>("OVERFLOW", Object.class);

    /**
     * 目录下的实体被创建
     *
     * 当目录注册到此事件时，如果发现在目录中创建了条目或者重命名为目录，则{@link WatchKey}将排队。
     * 此事件的事件{@link WatchEvent#count count}始终为{@code 1}。
     */
    public static final WatchEvent.Kind<Path> ENTRY_CREATE =
            new StdWatchEventKind<Path>("ENTRY_CREATE", Path.class);

    /**
     * 目录下的实体被删除
     *
     * 当目录注册到此事件时，如果观察到某个条目被删除或重命名为该目录，则{@link WatchKey}将排队。
     * 此事件的事件{@link WatchEvent#count count}始终为{@code 1}。
     */
    public static final WatchEvent.Kind<Path> ENTRY_DELETE =
            new StdWatchEventKind<Path>("ENTRY_DELETE", Path.class);

    /**
     * 目录下的实体被修改
     *
     * 当目录注册到此事件时，如果观察到某个条目被修改，则{@link WatchKey}将排队。
     * 此事件的事件{@link WatchEvent#count count}始终为{@code 1}。
     */
    public static final WatchEvent.Kind<Path> ENTRY_MODIFY =
            new StdWatchEventKind<Path>("ENTRY_MODIFY", Path.class);

    private static class StdWatchEventKind<T> implements WatchEvent.Kind<T> {

        private final String name;
        private final Class<T> type;

        StdWatchEventKind(String name, Class<T> type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public Class<T> type() {
            return type;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

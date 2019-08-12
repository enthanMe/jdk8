package java.nio.file;

/**
 * 使用{@link WatchService}注册的对象的事件或重复事件。
 *
 * 事件按其{@link #kind() kind}进行分类，并具有{@link #count() count}以指示事件被观察的次数。
 * 这允许重复事件的有效表示。{@link #context() context}方法返回与事件关联的任何上下文。
 * 在重复事件的情况下，所有事件的上下文都是相同的。
 *
 * 监视事件是不可变的，可供多个并发线程使用。
 *
 * @param <T> 与事件关联的上下文对象的类型
 *
 * @since 1.7
 */
public interface WatchEvent<T> {

    /**
     * 一种事件类型，用户区分标识
     *
     * @since 1.7
     * @see StandardWatchEventKinds
     */
    public static interface Kind<T> {
        /**
         * 返回事件类型的名称
         *
         * @return 事件类型的名称
         */
        String name();

        /**
         * 返回{@link WatchEvent#context context}对象的类型
         *
         *
         * @return 上下文对象的类型
         */
        Class<T> type();
    }

    /**
     * 一个事件修饰符，用于限定{@link Watchable}如何在{@link WatchService}中注册。
     *
     * 此版本未定义任何<em>标准</ em>修饰符
     *
     * @since 1.7
     * @see Watchable#register
     */
    public static interface Modifier {
        /**
         * 返回修饰符的名称.
         *
         * @return 修饰符的名称.
         */
        String name();
    }

    /**
     * 返回事件类型.
     *
     * @return  事件类型
     */
    Kind<T> kind();

    /**
     * 返回事件计数。
     * 如果事件计数大于{@code 1}，则这是重复事件
     *
     * @return 事件计数
     */
    int count();

    /**
     * 返回事件的上下文对象。
     *
     * 对于{@link StandardWatchEventKinds#ENTRY_CREATE ENTRY_CREATE}，{@link StandardWatchEventKinds#ENTRY_DELETE ENTRY_DELETE}
     * 和{@link StandardWatchEventKinds#ENTRY_MODIFY ENTRY_MODIFY}事件，上下文对象是{@code Path}，
     * 即{@link Path#relativize 注册到监视服务的目录与创建，删除或修改的条目之间的路径。
     *
     * @return 事件上下文对象; 可能为{@code null}
     */
    T context();
}

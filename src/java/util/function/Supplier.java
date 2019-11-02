package java.util.function;

/**
 * Represents a supplier of results.
 *
 * There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #get()}.
 *
 * Supplier<T>接口被称为生产型接口，指定接口的泛型是什么类型，那么接口中的get方法就会生产什么类型的数据
 *
 * @param <T> the type of results supplied by this supplier
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     * 获取一个泛型参数指定类型的对象数据。
     *
     * @return a result
     */
    T get();
}

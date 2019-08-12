package java.util.function;

import java.util.Objects;

/**
 * 表示接受一个参数并生成结果的函数.
 *
 * @param <T> 函数输入的类型
 * @param <R> 函数结果的类型
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * 将此函数应用于给定的参数.
     *
     * @param t 函数参数
     * @return 函数结果
     */
    R apply(T t);

    /**
     * 返回首先将{@code before}函数应用于其输入的组合函数，然后将此函数应用于结果。
     * 如果对任一函数的求值抛出异常，则将其转发给组合函数的调用者。
     *
     * @param <V> {@code before}函数和组合函数的输入类型
     * @param before 应用此函数之前应用的函数
     * @return 一个首先应用{@code before}函数的组合函数，然后应用此函数
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * 返回首先将此函数应用于其输入的组合函数，然后将{@code after}函数应用于结果。
     * 如果对任一函数的求值抛出异常，则将其转发给组合函数的调用者。
     *
     * @param <V> {@code after}函数和组合函数的输出类型
     * @param after 应用此函数后应用的函数
     * @return 一个首先应用此函数的组合函数，然后应用{@code after}函数
     * @throws NullPointerException if after is null
     *
     * @see #compose(Function)
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * 返回一个始终返回其输入参数的函数.
     *
     * @param <T> 函数的输入和输出对象的类型
     * @return 一个始终返回其输入参数的函数.
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}

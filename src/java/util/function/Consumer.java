package java.util.function;

import java.util.Objects;

/**
 * 表示 "接受一个参数输入且没有任何返回值的操作"。
 * 不同于其它的函数式接口，Consumer期望通过方法的实现来执行具体的操作。
 *
 * @param <T> 操作输入的类型
 * @since 1.8
 */
@FunctionalInterface
public interface Consumer<T> {

    /**
     * 可实现方法，接受一个参数且没有返回值
     *
     * @param t 输入参数
     */
    void accept(T t);

    /**
     * 默认方法，提供链式调用方式执行。执行流程：先执行本身的accept在执行传入参数after.accept方法。
     * 如果执行任一操作抛出异常，它将被中继到组合操作的调用者。
     * 如果在执行调用链时出现异常，会将异常传递给调用链功能的调用者，且发生异常后的after将不会在调用。
     *
     * @param after 操作后执行此操作
     * @return 一个组合的{@code Consumer}，按顺序执行此操作，然后执行{@code after}操作
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
}

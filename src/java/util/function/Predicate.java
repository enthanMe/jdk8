package java.util.function;

import java.util.Objects;

/**
 * 表示一个参数的谓词（布尔值函数）
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object)}.
 *
 * @param <T> 谓词输入的类型
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Predicate<T> {

    /**
     * 根据给定的参数计算此谓词.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);

    /**
     * 返回一个组合谓词，表示此谓词和另一个谓词的短路逻辑AND。
     * 在评估组合谓词时，如果此谓词是{@code false}，则不评估{@code other}谓词。
     *
     * 在评估任一谓词期间抛出的任何异常都会转发给调用者;
     * 如果对此谓词的评估引发异常，则不会评估{@code other}谓词。
     *
     * @param other 一个与该谓词进行逻辑AND运算的谓词
     * @return 一个组合谓词，表示该谓词和{@code other}谓词的短路逻辑AND
     * @throws NullPointerException if other is null
     */
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * 返回表示此谓词的逻辑否定的谓词。
     *
     * @return 表示此谓词的逻辑否定的谓词。
     */
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    /**
     * 返回一个组合谓词，表示此谓词与另一个谓词的短路逻辑OR。
     * 在评估组合谓词时，如果此谓词是{@code true}，则不评估{@code other}谓词。
     *
     * 在评估任一谓词期间抛出的任何异常都会转发给调用者;
     * 如果对此谓词的评估引发异常，则不会评估{@code other}谓词。
     *
     * @param other 与谓词逻辑或运算的谓词
     * @return 一个组合谓词，表示短路逻辑或该谓词和{@code other}谓词
     * @throws NullPointerException if other is null
     */
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * 返回一个谓词，根据{@link Objects#equals(Object，Object)}测试两个参数是否相等。
     *
     * @param <T> 谓词的参数类型
     * @param targetRef 用于比较相等性的对象引用，可能是{@code null}
     * @return 一个谓词，根据{@link Objects#equals(Object, Object)}测试两个参数是否相等
     */
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }
}

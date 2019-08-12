package java.nio.file;

import java.util.List;

/**
 * 表示使用{@link WatchService}注册{@link Watchable watchable}对象的令牌。
 *
 * 当监视服务注册可观察对象时，会创建监视令牌。令牌保留{@link #isValid 是否有效}，直到:
 * <ol>
 *  <li> 通过调用{@link #cancel cancel}方法或明确取消它</li>
 *  <li> 隐式取消，因为该对象不再可访问</li>
 *  <li> 通过{@link WatchService #close close}监视服务。</li>
 * </ol>
 *
 * 监视令牌具有状态。最初创建时，令牌状态为<em>ready</em>。
 * 检测到事件时，令牌状态<em>signalled</em>并排队，以便通过调用监视服务的{@link WatchService#poll() poll}或{@link WatchService#take() take}方法来检索它。
 * 发出信号后，令牌将保持此状态，直到调用其{@link #reset reset}方法将令牌返回到重置状态。
 * 在令牌处于signalled状态时检测到的事件被排队但不会导致令牌重新排队以从监视服务中检索。
 * 通过调用令牌的{@link #pollEvents pollEvents}方法来检索事件。
 * 此方法检索并删除为对象累积的所有事件。最初创建时，监视令牌没有待处理事件。
 * 通常，当令牌处于signalled状态时检索事件，从而引发以下语法：
 *
 * <pre>
 *     for (;;) {
 *         // retrieve key
 *         WatchKey key = watcher.take();
 *
 *         // process events
 *         for (WatchEvent<?> event: key.pollEvents()) {
 *             :
 *         }
 *
 *         // reset the key
 *         boolean valid = key.reset();
 *         if (!valid) {
 *             // object no longer registered
 *         }
 *     }
 * </pre>
 *
 * 监视令牌可以安全地由多个并发线程使用。
 * 如果有多个线程从监视服务检索已signalled的密钥，则应注意确保仅在处理了对象的事件后调用{@code reset}方法。
 * 这可确保一个线程随时处理对象的事件。
 * @since 1.7
 */
public interface WatchKey {

    /**
     * 告诉监视器此监视令牌是否有效
     *
     * 监视令牌在创建时有效，一直保留到它被取消，或其监视服务关闭
     * @return {@code true} if, and only if, this watch key is valid
     */
    boolean isValid();

    /**
     * 检索并删除此监视令牌的所有待处理事件，返回已检索事件的{@code List}。
     *
     * 请注意，如果没有待处理的事件，此方法不会等待。
     * @return 检索到的事件列表; 可能是空的
     */
    List<WatchEvent<?>> pollEvents();

    /**
     * 重置监视令牌
     *
     * 如果此监视令牌已被取消或此监视令牌已处于ready状态，则调用此方法无效。
     * 否则，如果对象存在待处理事件，则此监视令牌将立即重新排队到监视服务。
     * 如果没有待处理事件，则监视令牌处于ready状态并将保持该状态，直到检测到事件或取消监视令牌。
     *
     * @return {@code true}如果监视令牌有效且已被重置，
     * 并且{@code false}如果监视令牌无法重置，因为它不再是{@link #isValid valid}
     */
    boolean reset();

    /**
     * 取消监视服务的注册。返回时，监视密钥将无效。
     * 如果监视令牌已排队，等待从监视服务检索，则它将保留在队列中，直到将其删除。
     * 待处理事件（如果有）保持pending状态，可以在取消密钥后通过调用{@link #pollEvents pollEvents}方法来检索。
     *
     * 如果此监视令牌已被取消，则调用此方法无效。
     * 一旦取消，监视令牌将永远无效
     */
    void cancel();

    /**
     * 返回为其创建此监视令牌的对象。即使在取消令牌后，此方法仍将继续返回对象。
     *
     * 由于{@code WatchService}旨在直接映射到本机文件事件通知工具（如果可用），因此有关如何监视已注册对象的许多详细信息是高度特定于实现的。
     * 例如，当在目录中查看更改，并且在文件系统中移动或重命名目录时，无法保证监视令牌将被取消，因此此方法返回的对象可能不再是该目录的有效路径。
     *
     * @return 为其创建此监视键的对象
     */
    Watchable watchable();
}

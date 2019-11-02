package java.io;

/**
 * 实现此接口的类的实例用于过滤文件名。
 * 这些实例用于过滤类<code>File</code>的<code>list</code>方法中的目录列表，以及Abstract Window Toolkit的文件对话框组件。
 *
 * @author Arthur van Hoff
 * @author Jonathan Payne
 * @see java.awt.FileDialog#setFilenameFilter(java.io.FilenameFilter)
 * @see java.io.File
 * @see java.io.File#list(java.io.FilenameFilter)
 * @since JDK1.0
 */
@FunctionalInterface
public interface FilenameFilter {

    /**
     * 测试指定的文件是否应包含在文件列表中。
     *
     * @param dir 找到该文件的目录。
     * @param name 文件名称.
     * @return <code>true</code>包含在文件列表中; <code>false</code> 否则.
     */
    boolean accept(File dir, String name);
}

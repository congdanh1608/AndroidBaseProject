package vn.danhtran.customglide;

/**
 * Created by danhtran on 28/12/2016.
 */
public class Constants {
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();

    public static final int MAX_DISK_CACHE_SIZE = 40 * 1048576;         //MB
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 8;
}

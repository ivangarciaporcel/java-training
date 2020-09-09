package org.example.effectivejava.chapter2;

import org.example.effectivejava.chapter2.item9.CloseableClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.example.effectivejava.utils.JavaEffectiveUtils.getPathOfFile;

/**
 * User: Ivan.Garcia
 * Time: 9/8/2020 6:14 PM
 * <p>
 * Item 9: Prefer try-with-resources to try-finally
 * <p>
 * Always use try-with-resources in preference to try-finally when working with resources that must be closed.
 * The resulting code is shorter and clearer, and the exceptions that it generates are more useful.
 * The try-with-resources statement makes it easy to write correct code using resources that must be closed,
 * which was practically impossible using try-finally.
 */
public class Item9Test {

    /**
     * Resource is closed in finally block, but it has a problem, if an exception is thrown in method readLine and then
     * another exception in close method, then the first exception will be overlapped by the second one, so there will
     * not be information of first exception printed in the stacktrace.
     *
     * @throws IOException
     */
    @Test
    public void Close_ResourcesWithTryFinally_ClosedResource() throws IOException {
        String path = getPathOfFile("item9.file");
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            System.out.println(br.readLine());
        } finally {
            br.close();
        }
    }

    /**
     * Resource is closed automatically as its instantiation is enclosed by a try-with-resource block, if an exception
     * occurs in readLine method and then in close (not explicit) method, then the first exception will be displayed
     * and the second one will be annotated as {@code suppressed}, so it will not be ignored after being printed
     * in the stacktrace.
     *
     * @throws IOException
     */
    @Test
    public void Close_ResourcesWithTryWithResource_ClosedResource() throws IOException {
        String path = getPathOfFile("item9.file");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            System.out.println(br.readLine());
        }
    }

    /**
     * Instance of class {@code CloseableClass} will be closed (as implements close method) and then
     * NullPointerException will be caught.
     *
     * @throws Exception
     */
    @Test
    public void Close_ResourcesWithTryWithResourceAndCatch_CaughtException() throws Exception {
        try (CloseableClass closeableClass = new CloseableClass()) {
            System.out.println("Running closeable class");
            String path = getPathOfFile("nonExistent.file");
        } catch (NullPointerException e) {
            System.out.println("Catching NPE");
        }
    }

}

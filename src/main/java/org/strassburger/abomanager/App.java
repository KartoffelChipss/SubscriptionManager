package org.strassburger.abomanager;

import java.util.logging.LogManager;
/**
 * Hello world!
 */
public class App {
    static {
        // Disable JOOQ logging
        LogManager.getLogManager().reset();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Test 2");
    }
}

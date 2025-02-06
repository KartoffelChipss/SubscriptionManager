package org.strassburger.subscriptionmanager.model;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseManager extends DatabaseManager {
    private final DSLContext dslContext;
    public static final String DB_FILE_NAME = "subscription_manager.db";

    public SQLiteDatabaseManager() throws DatabaseInitializationException {
        try {
            File dbFile = createDatabaseFile();

            final String databaseUrl = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            Connection connection = DriverManager.getConnection(databaseUrl);

            this.dslContext = DSL.using(connection, SQLDialect.SQLITE);
        } catch (SQLException e) {
            throw new DatabaseInitializationException("Failed to initialize SQLiteDatabaseManager", e);
        } catch (IOException e) {
            throw new DatabaseInitializationException("Failed to create database file", e);
        }
    }

    /**
     * Creates the database file if it does not exist.
     * @return The database file
     * @throws IOException If an I/O error occurs
     */
    private File createDatabaseFile() throws IOException {
        File dbFile = new File(DB_FILE_NAME);

        if (dbFile.exists()) {
            return dbFile;
        }

        // Copy the database file from the resources to the current directory
        try (InputStream dbInputStream = getClass().getClassLoader().getResourceAsStream(DB_FILE_NAME)) {
            if (dbInputStream == null) {
                throw new IOException("Database file not found: " + DB_FILE_NAME);
            }

            try (FileOutputStream outputStream = new FileOutputStream(dbFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dbInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            }
        }

        return dbFile;
    }

    @Override
    public DSLContext getDslContext() {
        return dslContext;
    }

    public static class DatabaseInitializationException extends Exception {
        public DatabaseInitializationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

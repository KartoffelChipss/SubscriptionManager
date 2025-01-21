package org.strassburger.abomanager.model;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseManager extends DatabaseManager {
    private final DSLContext dslContext;

    public SQLiteDatabaseManager() {
        try {
            final String databaseUrl = "jdbc:sqlite:src/main/resources/subscription_manager.db";
            Connection connection = DriverManager.getConnection(databaseUrl);

            this.dslContext = DSL.using(connection, SQLDialect.SQLITE);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize SQLiteDatabaseManager", e);
        }
    }

    @Override
    public DSLContext getDslContext() {
        return dslContext;
    }
}

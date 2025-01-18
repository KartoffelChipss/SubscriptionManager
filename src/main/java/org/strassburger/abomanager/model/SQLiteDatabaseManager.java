package org.strassburger.abomanager.model;

import org.jooq.DSLContext;

public class SQLiteDatabaseManager extends DatabaseManager {
    private DSLContext dslContext;

    public SQLiteDatabaseManager() {
        // TODO: Implement this constructor which will initialize the DSLContext
    }

    @Override
    public DSLContext getDslContext() {
        return dslContext;
    }
}

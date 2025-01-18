package org.strassburger.abomanager.model;

import org.jooq.DSLContext;
import org.strassburger.abomanager.model.repositories.ProfileRepository;

public abstract class DatabaseManager {

    public abstract DSLContext getDslContext();

    public ProfileRepository getProfileRepository() {
        return new ProfileRepository(getDslContext());
    }
}

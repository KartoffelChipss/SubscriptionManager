package org.strassburger.subscriptionmanager.model.repositories;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.strassburger.subscriptionmanager.jooq.tables.Profiles;
import org.strassburger.subscriptionmanager.model.entity.Profile;

import java.util.List;
import java.util.Optional;

public class ProfileRepository {
    private final DSLContext dsl;

    public ProfileRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Get a list of all profiles.
     * @return A list of all profiles.
     */
    public List<Profile> getProfiles() {
        return dsl.selectFrom(Profiles.PROFILES)
                .orderBy(Profiles.PROFILES.CREATED_AT.asc())
                .fetch(this::mapRecordToProfile);
    }

    /**
     * Get a profile by its username.
     * @param username The username of the profile.
     * @return The profile with the given username or null if no profile with the given username exists.
     */
    public Optional<Profile> getProfile(String username) {
        return dsl.selectFrom(Profiles.PROFILES)
                .where(Profiles.PROFILES.NAME.eq(username))
                .fetchOptional(this::mapRecordToProfile);
    }

    /**
     * Get a profile by its id.
     * @param id The id of the profile.
     * @return The profile with the given id or null if no profile with the given id exists.
     */
    public Optional<Profile> getProfile(int id) {
        return dsl.selectFrom(Profiles.PROFILES)
                .where(Profiles.PROFILES.ID.eq(id))
                .fetchOptional(this::mapRecordToProfile);
    }

    /**
     * Create a new profile.
     * @param username The username of the profile.
     * @return True if the profile was created successfully, false otherwise.
     */
    public boolean createProfile(String username) {
        return dsl.insertInto(Profiles.PROFILES)
                .set(Profiles.PROFILES.NAME, username)
                .execute() == 1;
    }

    /**
     * Create a new profile.
     * @param username The username of the profile.
     * @param pin The pin of the profile.
     * @return True if the profile was created successfully, false otherwise.
     */
    public boolean createProfile(String username, String pin) {
        return dsl.insertInto(Profiles.PROFILES)
                .set(Profiles.PROFILES.NAME, username)
                .set(Profiles.PROFILES.PIN, pin)
                .execute() == 1;
    }

    /**
     * Delete a profile by its username.
     * @param username The username of the profile.
     * @return True if the profile was deleted successfully, false otherwise.
     */
    public boolean deleteProfile(String username) {
        return dsl.deleteFrom(Profiles.PROFILES)
                .where(Profiles.PROFILES.NAME.eq(username))
                .execute() == 1;
    }

    /**
     * Map a database record to a profile.
     * @param record The database record.
     * @return The profile.
     */
    private Profile mapRecordToProfile(Record record) {
        return new Profile(
                record.get(Profiles.PROFILES.ID),
                record.get(Profiles.PROFILES.NAME)
        );
    }
}

package org.strassburger.abomanager.model.repositories;

import org.jooq.DSLContext;
import org.strassburger.abomanager.model.entity.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {
    private final DSLContext dsl;

    public ProfileRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Get a list of all profiles.
     * @return A list of all profiles.
     */
    List<Profile> getProfiles() {
        // TODO: This is not a real implementation, but a placeholder
        return new ArrayList<>(
                List.of(
                        new Profile(0, "Alice"),
                        new Profile(1, "Bob"),
                        new Profile(2, "Charlie")
                )
        );
    }

    /**
     * Get a profile by its username.
     * @param username The username of the profile.
     * @return The profile with the given username or null if no profile with the given username exists.
     */
    Profile getProfile(String username) {
        // TODO: This is not a real implementation, but a placeholder too
        if (username.equals("Alice")) {
            return new Profile(0, "Alice");
        } else if (username.equals("Bob")) {
            return new Profile(1, "Bob");
        } else if (username.equals("Charlie")) {
            return new Profile(2, "Charlie");
        } else {
            return null;
        }
    }

    /**
     * Get a profile by its id.
     * @param id The id of the profile.
     * @return The profile with the given id or null if no profile with the given id exists.
     */
    Profile getProfile(int id) {
        // TODO: This is not a real implementation, but a placeholder too
        if (id == 0) {
            return new Profile(0, "Alice");
        } else if (id == 1) {
            return new Profile(1, "Bob");
        } else if (id == 2) {
            return new Profile(2, "Charlie");
        } else {
            return null;
        }
    }
}

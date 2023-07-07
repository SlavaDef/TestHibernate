package org.example.Storage;

import org.flywaydb.core.Flyway; // implementation in build.gradle

public class DatabaseInitService { // class for migration
    public void initDb() {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway
                .configure()
                .dataSource(StorageConstants.CONNECTION_URL, null, null)
                .load();

        // Start the migration
        flyway.migrate();
    }
}

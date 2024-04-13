CREATE TABLE if not exists "user" (
                       id UUID PRIMARY KEY, -- Generates a new UUID v4 for each record
                       username VARCHAR(255) UNIQUE NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       phone VARCHAR(255) UNIQUE, -- Can be adjusted based on data type preference for phone numbers
                       password VARCHAR(255),  -- Optional for phone number login
                       created_at timestamp WITH TIME ZONE,
                       updated_at timestamp WITH TIME ZONE
);

CREATE TABLE if not exists user_credential (
                                  id UUID PRIMARY KEY, -- Generates a new UUID v4 for each record
                                  user_id UUID NOT NULL REFERENCES "user"(id),
                                  provider VARCHAR(255) NOT NULL,
                                  provider_id VARCHAR(255) NOT NULL,
                                  access_token VARCHAR(255), -- Optional, store securely if used
                                  refresh_token VARCHAR(255), -- Optional, store securely if used
                                  created_at timestamp WITH TIME ZONE,
                                  updated_at timestamp WITH TIME ZONE,
                                  CONSTRAINT unique_provider_user UNIQUE (user_id, provider) -- Ensures a user can only have one record per provider
);
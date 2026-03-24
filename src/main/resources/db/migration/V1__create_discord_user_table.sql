CREATE TABLE discord_user
(
    discord_id        VARCHAR(255) NOT NULL,
    discord_user_name VARCHAR(255),
    discord_email     VARCHAR(255),
    CONSTRAINT pk_discord_user PRIMARY KEY (discord_id)
);
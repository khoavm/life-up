alter table if exists life_up.user
    ADD CONSTRAINT phone_uni UNIQUE NULLS NOT DISTINCT (phone)


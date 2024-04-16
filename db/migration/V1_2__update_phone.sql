alter table if exists life_up.user
    ADD CONSTRAINT phone_uni UNIQUE NULLS DISTINCT (phone)


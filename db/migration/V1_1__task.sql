create table if not exists life_up.task
(
    id              uuid
        constraint task_pk
            primary key,
    name            text,
    description     text,
    coin_reward     integer,
    is_repeat       boolean,
    repeat_duration int,
    repeat_times    integer,
    start_time      timestamp WITH TIME ZONE,
    deadline        timestamp WITH TIME ZONE,
    importance      integer,
    difficulty      integer,
    status          text,
    created_at      timestamp WITH TIME ZONE,
    updated_at      timestamp with time zone,
    user_id         uuid
        constraint task_user_id_fk
            references life_up."user"
);

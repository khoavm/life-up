alter table life_up."user"
alter column created_at type timestamp WITH TIME ZONE using created_at::timestamp WITH TIME ZONE;

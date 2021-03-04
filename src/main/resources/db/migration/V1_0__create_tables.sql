create table incomes  (
  id serial primary key not null,
  user_id integer not null,
  income numeric
);
create table spend  (
  id serial primary key not null,
  user_id integer not null,
  spend numeric
);
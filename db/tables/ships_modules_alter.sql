alter table ships_modules add index ship_modules_fk_ships_types (ow_werft), add constraint ship_modules_fk_ships_types foreign key (ow_werft) references ship_types (id);
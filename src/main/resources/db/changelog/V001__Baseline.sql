create table if not exists high_low
(
    id      bigserial primary key,
    account varchar(255),
    player  varchar(255)
);

create table high_low_players
(
    id       bigserial primary key,
    player   bigint,
    opponent varchar(255),
    amount   bigint,
    unique (player, opponent)
);

create table if not exists deathroll
(
    id        bigserial primary key,
    timestamp bigint,
    winner    varchar(255),
    loser     varchar(255),
    amount    bigint,
    unique (timestamp, winner, loser)
);

create table if not exists alias
(
    id     bigserial primary key,
    player varchar(255),
    alias  varchar(255),
    unique (player, alias)
);

insert into alias
values (DEFAULT, 'Karizev', 'Wahfarming');

do
$$
    declare
        ids bigint;
    begin
        insert into high_low values (DEFAULT, '120157093#1', 'Karizev') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -454);
        insert into high_low_players
        values (DEFAULT, ids, 'Immuned', -8637);

        insert into high_low values (DEFAULT, '120157093#1', 'Immuned') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Karizev', 8637);
        insert into high_low_players
        values (DEFAULT, ids, 'Dexterel', 7712);

        insert into high_low values (DEFAULT, '120157093#1', 'Plimplom') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Karizev', 454);

        insert into high_low values (DEFAULT, '120157093#1', 'Dexterel') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Immuned', -7712);

        insert into high_low values (DEFAULT, '109731789#1', 'Bosaap') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Tamalewl', 144200);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', 105392);
        insert into high_low_players
        values (DEFAULT, ids, 'Wahfarming', -7526);

        insert into high_low values (DEFAULT, '109731789#1', 'Mashiso') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Teikys', -10114);

        insert into high_low values (DEFAULT, '109731789#1', 'Myotosis') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Karizev', -12400);

        insert into high_low values (DEFAULT, '109731789#1', 'Teikys') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Mashiso', 10114);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -13098);

        insert into high_low values (DEFAULT, '109731789#1', 'Räydiance') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', 64292);
        insert into high_low_players
        values (DEFAULT, ids, 'Tamalewl', 60117);
        insert into high_low_players
        values (DEFAULT, ids, 'Tenthiv', 14396);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', 10128);

        insert into high_low values (DEFAULT, '109731789#1', 'Samtyler') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', 171457);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -51095);

        insert into high_low values (DEFAULT, '109731789#1', 'Moxw') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', -15797);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -19131);
        insert into high_low_players
        values (DEFAULT, ids, 'Karizev', -15032);

        insert into high_low values (DEFAULT, '109731789#1', 'Tamalewl') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', 84183);
        insert into high_low_players
        values (DEFAULT, ids, 'Bosaap', -144200);
        insert into high_low_players
        values (DEFAULT, ids, 'Räydiance', -60117);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', 58600);

        insert into high_low values (DEFAULT, '109731789#1', 'Vaxks') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', -67001);
        insert into high_low_players
        values (DEFAULT, ids, 'Immuned', 50620);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -50665);
        insert into high_low_players
        values (DEFAULT, ids, 'Karizev', -28191);

        insert into high_low values (DEFAULT, '109731789#1', 'Tenthiv') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', 9861);
        insert into high_low_players
        values (DEFAULT, ids, 'Lorod', 17728);
        insert into high_low_players
        values (DEFAULT, ids, 'Räydiance', -14396);
        insert into high_low_players
        values (DEFAULT, ids, 'Immuned', 2235);

        insert into high_low values (DEFAULT, '109731789#1', 'Immuned') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', -4147);
        insert into high_low_players
        values (DEFAULT, ids, 'Vaxks', -50620);
        insert into high_low_players
        values (DEFAULT, ids, 'Tenthiv', -2235);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -36732);

        insert into high_low values (DEFAULT, '109731789#1', 'Karizev') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Myotosis', 12400);
        insert into high_low_players
        values (DEFAULT, ids, 'Moxw', 15032);
        insert into high_low_players
        values (DEFAULT, ids, 'Vaxks', 28191);

        insert into high_low values (DEFAULT, '109731789#1', 'Lorod') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Tenthiv', -17728);

        insert into high_low values (DEFAULT, '109731789#1', 'Jardex') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Moxw', 15797);
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -30155);
        insert into high_low_players
        values (DEFAULT, ids, 'Immuned', 4147);
        insert into high_low_players
        values (DEFAULT, ids, 'Tenthiv', -9861);
        insert into high_low_players
        values (DEFAULT, ids, 'Vaxks', 67001);
        insert into high_low_players
        values (DEFAULT, ids, 'Samtyler', -171457);
        insert into high_low_players
        values (DEFAULT, ids, 'Tamalewl', -84183);
        insert into high_low_players
        values (DEFAULT, ids, 'Räydiance', -64292);
        insert into high_low_players
        values (DEFAULT, ids, 'Aurã', 60696);

        insert into high_low values (DEFAULT, '109731789#1', 'Wahfarming') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Plimplom', -49387);
        insert into high_low_players
        values (DEFAULT, ids, 'Bosaap', 7526);

        insert into high_low values (DEFAULT, '109731789#1', 'Plimplom') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Moxw', 19131);
        insert into high_low_players
        values (DEFAULT, ids, 'Wahfarming', 49387);
        insert into high_low_players
        values (DEFAULT, ids, 'Tamalewl', -58600);
        insert into high_low_players
        values (DEFAULT, ids, 'Immuned', 36732);
        insert into high_low_players
        values (DEFAULT, ids, 'Räydiance', -10128);
        insert into high_low_players
        values (DEFAULT, ids, 'Bosaap', 105392);
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', 30155);
        insert into high_low_players
        values (DEFAULT, ids, 'Teikys', 13098);
        insert into high_low_players
        values (DEFAULT, ids, 'Samtyler', 51095);
        insert into high_low_players
        values (DEFAULT, ids, 'Vaxks', 50665);

        insert into high_low values (DEFAULT, '109731789#1', 'Aurã') returning id into ids;
        insert into high_low_players
        values (DEFAULT, ids, 'Jardex', -60696);

        insert into deathroll values (DEFAULT, 1745081100, 'Immuned', 'Plimplom', 20000);
        insert into deathroll values (DEFAULT, 1745081100, 'Plimplom', 'Karizev', 15000);
        insert into deathroll values (DEFAULT, 1745081100, 'Räydiance', 'Plimplom', 10000);
        insert into deathroll values (DEFAULT, 1745081100, 'Jardex', 'Plimplom', 11100);
        insert into deathroll values (DEFAULT, 1745081100, 'Plimplom', 'Moxw', 6500);
    end
$$;
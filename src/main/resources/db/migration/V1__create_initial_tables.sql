
CREATE TABLE t_discovery_source
(
    id                 serial primary key ,
    establishment_date TIMESTAMP(6),
    name               VARCHAR(255),
    state_owner        VARCHAR(255),
    type               VARCHAR(255)
);

CREATE TABLE t_star_object
(
    id                  serial primary key ,
    discovery_date      TIMESTAMP(6),
    discovery_source_id bigint,
    equatorial_diameter bigint,
    mass                bigint,
    name                VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS Tool (
    id serial NOT NULL,
    title varchar(50) NOT NULL,
    link varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    status varchar(10) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT tool_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Tag(
    id serial NOT NULL,
    description VARCHAR(100),
    CONSTRAINT tag_pk PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS ToolTags(
   tool_id int NOT NULL,
   tag_id int NOT NULL,
   FOREIGN KEY(tool_id) REFERENCES Tool (id),
   FOREIGN KEY(tag_id) REFERENCES Tag (id),
   CONSTRAINT tool_tags_id PRIMARY KEY(tool_id, tag_id)
);


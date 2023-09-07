CREATE TABLE persons (
                         id Int32,
                         name String,
                         surName String,
                         email String,
                         age Int32,
                         city String
) ENGINE = MergeTree()
ORDER BY id;
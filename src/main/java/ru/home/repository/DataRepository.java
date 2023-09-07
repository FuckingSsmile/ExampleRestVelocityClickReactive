package ru.home.repository;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.home.dto.PersonDto;
import ru.home.utils.SqlUtils;

import java.util.List;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class DataRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseClient reactiveClient;

    public List<PersonDto> loadPersonsByQuery(VelocityContext velocityContext, Template velocityTemplate) {
        String sqlRequest = SqlUtils.getSql(velocityContext, velocityTemplate);
        BeanPropertyRowMapper<PersonDto> rowMapper = new BeanPropertyRowMapper<>(PersonDto.class);
        return jdbcTemplate.query(sqlRequest, rowMapper);
    }

    public PersonDto loadPersons(VelocityContext velocityContext, Template velocityTemplate) {
        String sqlRequest = SqlUtils.getSql(velocityContext, velocityTemplate);
        BeanPropertyRowMapper<PersonDto> rowMapper = new BeanPropertyRowMapper<>(PersonDto.class);
        return jdbcTemplate.queryForObject(sqlRequest, rowMapper);
    }

    public Stream<PersonDto> loadPersonsByQueryStream(Template velocityTemplate) {
        String sqlRequest = SqlUtils.getSql(velocityTemplate);
        BeanPropertyRowMapper<PersonDto> rowMapper = new BeanPropertyRowMapper<>(PersonDto.class);
        return jdbcTemplate.queryForStream(sqlRequest, rowMapper);
    }

    public Flux<List<PersonDto>> loadPersonsByReactive(VelocityContext velocityContext, Template velocityTemplate) {
        String sqlRequest = SqlUtils.getSql(velocityContext, velocityTemplate);
        return reactiveClient.sql(sqlRequest).map((row, meta) -> {
            PersonDto personDto = new PersonDto();
            personDto.setId(row.get("id", Integer.class));
            personDto.setName(row.get("name", String.class));
            personDto.setSurName(row.get("surName", String.class));
            personDto.setEmail(row.get("email", String.class));
            personDto.setAge(row.get("age", Integer.class));
            personDto.setCity(row.get("city", String.class));
            return personDto;
        }).all().collectList().flux();
    }

    public Long loadCountRecordsFromTable(Template velocityTemplate){
        String sqlRequest = SqlUtils.getSql(velocityTemplate);
        return jdbcTemplate.queryForObject(sqlRequest, Long.class);
    }

    public List<PersonDto> loadListPersonsById(VelocityContext velocityContext, Template velocityTemplate){
        String sqlRequest = SqlUtils.getSql(velocityContext, velocityTemplate);
        BeanPropertyRowMapper<PersonDto> rowMapper = new BeanPropertyRowMapper<>(PersonDto.class);

        return jdbcTemplate.query(sqlRequest, rowMapper);
    }

//    public List<PersonDto> loadListPersonsById(Template velocityTemplate) {
//        String sqlRequest = SqlUtils.getSql(velocityTemplate);
//        BeanPropertyRowMapper<PersonDto> rowMapper = new BeanPropertyRowMapper<>(PersonDto.class);
//
//        return jdbcTemplate.query(sqlRequest, (rs, rowNum) -> {
//            PersonDto personDto = rowMapper.mapRow(rs, rowNum);
//            return personDto;
//        });
//    }
}

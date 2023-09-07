package ru.home.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.home.dto.PersonDto;
import ru.home.dto.request.PersonRequestDto;
import ru.home.repository.DataRepository;
import ru.home.service.PersonService;
import ru.home.utils.SqlUtils;
import ru.home.velocity.TemplateType;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final DataRepository dataRepository;

    @Override
    public List<PersonDto> getPersonsByQuery(PersonRequestDto requestDto) {
        VelocityContext velocityContext = SqlUtils.getVelocityContext(requestDto);
        Template velocityTemplate = SqlUtils.getVelocityTemplate(TemplateType.PERSON);
        return dataRepository.loadPersonsByQuery(velocityContext, velocityTemplate);
    }

    @Override
    public List<PersonDto> getPersonsByStream() {
        Template velocityTemplate = SqlUtils.getVelocityTemplate(TemplateType.ALL_PERSONS);

        Stream<PersonDto> personDtoStream = dataRepository.loadPersonsByQueryStream(velocityTemplate);
        return personDtoStream.toList();
    }

    @Override
    public Flux<List<PersonDto>> getPersonsByReactive(PersonRequestDto requestDto) {
        VelocityContext velocityContext = SqlUtils.getVelocityContext(requestDto);
        Template velocityTemplate = SqlUtils.getVelocityTemplate(TemplateType.PERSON);
        return dataRepository.loadPersonsByReactive(velocityContext, velocityTemplate);
    }

    @Override
    public PersonDto getPerson(PersonRequestDto requestDto) {
        VelocityContext velocityContext = SqlUtils.getVelocityContext(requestDto);
        Template velocityTemplate = SqlUtils.getVelocityTemplate(TemplateType.PERSON);
        return dataRepository.loadPersons(velocityContext, velocityTemplate);
    }

    @Override
    public Long countOfPersonsByQuery() {
        Template velocityTemplate = SqlUtils.getVelocityTemplate(TemplateType.COUNT);
        return dataRepository.loadCountRecordsFromTable(velocityTemplate);
    }

    @Override
    public List<PersonDto> getListPersons(PersonRequestDto requestDto) {
        VelocityContext velocityContext = SqlUtils.getVelocityContext(requestDto);
        Template velocityTemplate = SqlUtils.getVelocityTemplate(TemplateType.SOME_PERSONS);


        return dataRepository.loadListPersonsById(velocityContext, velocityTemplate);
    }
}

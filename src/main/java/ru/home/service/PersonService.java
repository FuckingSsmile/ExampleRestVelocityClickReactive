package ru.home.service;

import reactor.core.publisher.Flux;
import ru.home.dto.PersonDto;
import ru.home.dto.request.PersonRequestDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getPersonsByQuery(PersonRequestDto requestDto);
    List<PersonDto> getPersonsByStream();
    Flux<List<PersonDto>> getPersonsByReactive(PersonRequestDto requestDto);
    PersonDto getPerson(PersonRequestDto requestDto);
    Long countOfPersonsByQuery();
    List<PersonDto> getListPersons(PersonRequestDto requestDto);
}

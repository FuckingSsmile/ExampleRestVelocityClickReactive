package ru.home.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.home.dto.PersonDto;
import ru.home.dto.request.PersonRequestDto;
import ru.home.service.PersonService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/query/persons")
    public List<PersonDto> getPersonByQuery(@RequestBody @NonNull  PersonRequestDto requestDto) {
        return personService.getPersonsByQuery(requestDto);
    }

    @GetMapping("/stream/getAllPerson")
    public List<PersonDto> getPersonByStream() {
        return personService.getPersonsByStream();
    }

    @PostMapping("/reactive/getPerson")
    public Flux<List<PersonDto>> getPersonByReactive(@RequestBody @NonNull PersonRequestDto requestDto) {
        return personService.getPersonsByReactive(requestDto);
    }

    @PostMapping("/getPerson")
    public PersonDto getPerson(@RequestBody @NonNull  PersonRequestDto requestDto) {
        return personService.getPerson(requestDto);
    }

    @GetMapping("/countPersons")
    public Long getPerson() {
        return personService.countOfPersonsByQuery();
    }

    @PostMapping("/somePersons")
    public List<PersonDto> getSomePerson(@RequestBody @NonNull  PersonRequestDto requestDto) {
        List<Integer> id = requestDto.id();
        return personService.getListPersons(requestDto);
    }
}

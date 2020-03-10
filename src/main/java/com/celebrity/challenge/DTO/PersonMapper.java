package com.celebrity.challenge.DTO;

import com.celebrity.challenge.domain.Person;

import java.util.List;
import java.util.Map;

public final class PersonMapper {
    /**
     * This method convert a domain entity into a DTO Object in order to
     * hide votes business logic and publis the information in REST API
     * @param person
     * @param votesMap
     * @return
     */
    public static PersonDTO mapToDTO(Person person, Map<Integer,Long> votesMap){
        return PersonDTO.builder()
                .id(person.getId())
                .image(person.getImage())
                .job(person.getJob())
                .name(person.getName())
                .votes((votesMap.containsKey(person.getId())) ? votesMap.get(person.getId()) : 0L)
                .build();
    }

    public static PersonDTO mapToDTO(Person person, Long recognizersNumber) {
        return PersonDTO.builder()
                .id(person.getId())
                .image(person.getImage())
                .job(person.getJob())
                .name(person.getName())
                .followers(recognizersNumber)
                .build();
    }
}

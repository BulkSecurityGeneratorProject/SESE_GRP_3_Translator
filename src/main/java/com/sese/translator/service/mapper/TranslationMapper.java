package com.sese.translator.service.mapper;

import com.sese.translator.domain.*;
import com.sese.translator.service.dto.TranslationDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Translation and its DTO TranslationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface TranslationMapper {

    @Mapping(source = "translator.id", target = "translatorId")
    @Mapping(source = "language.id", target = "languageId")
    @Mapping(source = "definition.id", target = "definitionId")
    TranslationDTO translationToTranslationDTO(Translation translation);

    List<TranslationDTO> translationsToTranslationDTOs(List<Translation> translations);

    @Mapping(source = "translatorId", target = "translator")
    @Mapping(source = "languageId", target = "language")
    @Mapping(source = "definitionId", target = "definition")
    Translation translationDTOToTranslation(TranslationDTO translationDTO);

    List<Translation> translationDTOsToTranslations(List<TranslationDTO> translationDTOs);

    default Language languageFromId(Long id) {
        if (id == null) {
            return null;
        }
        Language language = new Language();
        language.setId(id);
        return language;
    }

    default Definition definitionFromId(Long id) {
        if (id == null) {
            return null;
        }
        Definition definition = new Definition();
        definition.setId(id);
        return definition;
    }
}

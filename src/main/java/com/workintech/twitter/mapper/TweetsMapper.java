package com.workintech.twitter.mapper;

import com.workintech.twitter.dto.request.TweetsRequestDto;
import com.workintech.twitter.dto.response.TweetsResponseDto;
import com.workintech.twitter.entity.Tweets;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TweetsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true) // service set edecek (now)
    @Mapping(target = "users", ignore = true)        // service user entity set edecek
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "retweets", ignore = true)
    Tweets toEntity(TweetsRequestDto dto);


    @Mapping(target = "userId", source = "users.id") // User nesnesinin tamamı yerine sadece ID'sini al
    TweetsResponseDto toResponse(Tweets tweet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true) // update’te de dokunmayalım
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "retweets", ignore = true)
    void updateEntityFromDto(TweetsRequestDto dto, @MappingTarget Tweets tweet);
}

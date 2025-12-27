package com.workintech.twitter.mapper;

import com.workintech.twitter.dto.request.CommentsRequestDto;
import com.workintech.twitter.dto.response.CommentsResponseDto;
import com.workintech.twitter.entity.Comments;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tweets", ignore = true) // service set edecek
    @Mapping(target = "users", ignore = true)  // service set edecek
    Comments toEntity(CommentsRequestDto dto);

    @Mapping(target = "tweetId", source = "tweets.id")
    CommentsResponseDto toResponse(Comments comments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tweets", ignore = true)
    @Mapping(target = "users", ignore = true)
    void updateEntityFromDto(CommentsRequestDto dto, @MappingTarget Comments comments);
}

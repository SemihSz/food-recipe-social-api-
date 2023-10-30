package com.food.recipe.api.mapper.post;

import com.food.recipe.api.model.document.DocumentInfoDTO;
import com.food.recipe.api.model.response.post.PostImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostImageMapper {

    PostImageMapper INSTANCE = Mappers.getMapper(PostImageMapper.class);

    PostImage convert(DocumentInfoDTO documentInfoDTO);
}

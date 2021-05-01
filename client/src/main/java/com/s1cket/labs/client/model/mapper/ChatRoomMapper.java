package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.ChatRoomEntity;
import com.s1cket.labs.client.model.dto.ChatRoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ChatRoomMapper.class)
public interface  ChatRoomMapper {

    ChatRoomEntity chatRoomDtoToChatRoomEntity(ChatRoomDto messageDto);

    ChatRoomDto chatRoomEntityToChatRoomDto(ChatRoomEntity messageEntity);

}

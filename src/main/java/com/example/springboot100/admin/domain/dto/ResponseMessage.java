package com.example.springboot100.admin.domain.dto;


import com.example.springboot100.user.domain.dto.UserDto;
import com.example.springboot100.user.domain.entity.User;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    ResponseMessageHeader responseMessageHeader;

    Object data;

    public static ResponseMessage of(User user) {

        return ResponseMessage.builder()
                .responseMessageHeader(ResponseMessageHeader.builder()
                        .result(true)
                        .resultCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .status(HttpStatus.OK.name())
                        .build())
                .data(UserDto.from(user))
                .build();
    }
}
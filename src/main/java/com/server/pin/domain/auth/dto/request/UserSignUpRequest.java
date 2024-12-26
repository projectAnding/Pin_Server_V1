package com.server.pin.domain.auth.dto.request;

import com.server.pin.global.common.validator.match.valid.NoSpecialValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserSignUpRequest(

        String profileImageURL,

        // 이름, 아이디, 소속, 이메일, 비번, 전번
        // TODO: 특수문자 체크 필요
        @NoSpecialValid
        @NotNull @NotBlank(message = "id is empty")
        @Size(min = 4, max = 12, message = "userid length issue")
        String userId,

        @NotNull @NotBlank(message = "username is empty")
        String userName,

        @NotNull @NotBlank(message = "detaildepartment is empty")
        String detailDepartment,

        @NotNull @NotBlank(message = "email is empty")
        @Email(message = "enter email")
        String email,

        @NotNull @NotBlank(message = "password is empty")
        @Size(min = 5, max = 20, message = "pw length issue")
        String password,

        @NotNull @NotBlank(message = "phonenumber is empty")
        @Size(min = 11, max = 11, message = "phoneNum length issue")
        String phoneNumber
) {

}

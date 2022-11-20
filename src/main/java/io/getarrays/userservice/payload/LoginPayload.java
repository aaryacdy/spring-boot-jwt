package io.getarrays.userservice.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginPayload {

    private String username;
    private String password;

}

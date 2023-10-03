package in.reqres.models;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email, password;
}

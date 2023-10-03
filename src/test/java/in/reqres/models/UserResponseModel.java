package in.reqres.models;

import lombok.Data;

@Data
public class UserResponseModel {
    private int id;
    private String email, first_name, last_name, avatar;
}

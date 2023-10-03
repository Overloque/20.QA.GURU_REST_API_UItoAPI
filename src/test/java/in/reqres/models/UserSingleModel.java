package in.reqres.models;

import lombok.Data;

@Data
public class UserSingleModel {
    private UserSupportModel support;
    private UserResponseModel data;
}

package in.reqres.models;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    private int id;
    private String name, job, createdAt;
}

package qa.demo.models.authorization;

import lombok.Data;

@Data
public class RegisterErrorResponseModel {
    private String code;
    private String message;
}

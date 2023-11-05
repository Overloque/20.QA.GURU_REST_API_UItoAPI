package qa.demo.models.authorization;

import lombok.Data;

@Data
public class GenerateTokenResponseModel {
    private String expires, result, status, token;
}

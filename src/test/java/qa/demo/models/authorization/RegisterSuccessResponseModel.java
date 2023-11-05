package qa.demo.models.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RegisterSuccessResponseModel {
    @JsonProperty("userID")
    private String userId;
    private List<String> books;
    private String username;
}

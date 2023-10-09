package qa.demo.models.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialsModel {
    private String userName, password;
}

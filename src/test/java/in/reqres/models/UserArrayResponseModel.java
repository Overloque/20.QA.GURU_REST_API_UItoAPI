package in.reqres.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserArrayResponseModel {
    private int page, total;

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("total_pages")
    private int totalPages;

    private ArrayList<UserResponseModel> data;
    private UserSupportModel support;
}

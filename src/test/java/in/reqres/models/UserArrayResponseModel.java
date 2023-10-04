package in.reqres.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserArrayResponseModel {
    private int page, total;

    @JsonProperty("perPage")
    private int per_page;

    @JsonProperty("totalPages")
    private int total_pages;

    private ArrayList<UserResponseModel> data;
    private UserSupportModel support;
}

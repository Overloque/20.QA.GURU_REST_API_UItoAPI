package in.reqres.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserArrayResponseModel {
    private int page, per_page, total, total_pages;
    private ArrayList<UserResponseModel> data;
    private UserSupportModel support;
}

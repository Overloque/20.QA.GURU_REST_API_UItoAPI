package qa.demo.models;

import lombok.Data;

import java.util.List;

@Data
public class AddingBookToListModel {
    private String userId;
    private List<IsbnModel> collection;
}

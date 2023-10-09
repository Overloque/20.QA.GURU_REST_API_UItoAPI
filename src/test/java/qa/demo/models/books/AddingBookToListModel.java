package qa.demo.models.books;

import lombok.Data;

import java.util.List;

@Data
public class AddingBookToListModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}

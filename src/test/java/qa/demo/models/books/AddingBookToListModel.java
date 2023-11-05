package qa.demo.models.books;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class AddingBookToListModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}

package qa.demo.models.books;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class RemovingBookFromListModel {
    private String isbn, userId;
}

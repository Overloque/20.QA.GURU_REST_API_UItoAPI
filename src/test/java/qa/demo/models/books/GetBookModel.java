package qa.demo.models.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBookModel {
    @JsonProperty("publish_date")
    private String publishDate;
    private String author, description, isbn, publisher, subTitle, title, website;
}

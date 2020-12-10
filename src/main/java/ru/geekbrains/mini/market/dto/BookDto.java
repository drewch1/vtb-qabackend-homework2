package ru.geekbrains.mini.market.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mini.market.entites.Book;

@Data
@NoArgsConstructor
@ApiModel(description = "Product dto in the application.")
public class BookDto {
    @ApiModelProperty(notes = "Unique identifier of the book. No two products can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Title of the book.", example = "Book1", required = true, position = 1)
    private String title;

    @ApiModelProperty(notes = "Price of the book.", example = "200", required = true, position = 2)
    private int price;

    @ApiModelProperty(notes = "Author name of the book.", example = "Author", required = true, position = 3)
    private String authorName;

    public BookDto(Book p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.authorName = p.getAuthor().getName();
    }
}
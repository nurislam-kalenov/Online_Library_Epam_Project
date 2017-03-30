package nuris.epam.entity;

import java.sql.Date;

/**
 * Created by User on 14.03.2017.
 */
public class Book extends BaseEntity {
    private Author author;
    private Genre genre;
    private String name;
    private String isbn;
    private Date date;
    private String description;

    public Book(){
        author = new Author();
        genre = new Genre();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getId() + "/" + name + "/" + date  + "/" + isbn +"/"+description+"/"+ genre+"/"+author;
    }
}

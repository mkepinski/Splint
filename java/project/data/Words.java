package project.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String front;

    @NotNull
    private String back;

    @ManyToOne
    private Category category;


    public Words(int id, @NotNull String front, @NotNull String back, Category category) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.category = category;
    }

    public Words() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

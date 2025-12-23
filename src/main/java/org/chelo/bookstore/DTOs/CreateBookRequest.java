package org.chelo.bookstore.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String author;
    private double price;
    private int quantity;

    public CreateBookRequest(String title, String author, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }
}

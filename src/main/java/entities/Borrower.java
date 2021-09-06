package entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Borrower {
    private String id;
    private String name;

    public Borrower(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}

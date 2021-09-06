package entities;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
public class Bank {
    private String id;
    private String name;

    public Bank(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}

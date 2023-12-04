package Data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@Builder
public class Game {
    private Long id;
    private String type;
    private String namegame;
    private Double price;

    public Game(Long id, String type, String namegame, Double price) {
        this.id = id;
        this.type = type;
        this.namegame = namegame;
        this.price = price;
    }
}

package Data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UUID {
    private Long id_standard;
    private Long id_user;
    private String UUID;

    public UUID (Long id_standard, Long id_user, String UUID) {
        this.id_standard = id_standard;
        this.id_user = id_user;
        this.UUID = UUID;
    }
}

package animatch.app.service.Midia.dto;

import animatch.app.domain.midia.Midia;

public class MidiaInfoDTO {
    private Midia midia;

    public MidiaInfoDTO(Midia midia) {
        this.midia = midia;
    }

    public Midia getMidia() {
        return midia;
    }
}

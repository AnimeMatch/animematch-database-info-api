package animatch.app.service.Midia.dto;

public class MidiaDadosComplementaresDto {
    private int like;
    private int deslike;
    private int view;

    public MidiaDadosComplementaresDto() {
    }

    public MidiaDadosComplementaresDto(int like, int deslike, int view) {
        this.like = like;
        this.deslike = deslike;
        this.view = view;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDeslike() {
        return deslike;
    }

    public void setDeslike(int deslike) {
        this.deslike = deslike;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}


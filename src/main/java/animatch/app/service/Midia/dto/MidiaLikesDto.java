package animatch.app.service.Midia.dto;

public class MidiaLikesDto {
    private int likes;

    public MidiaLikesDto() {
    }

    public MidiaLikesDto(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

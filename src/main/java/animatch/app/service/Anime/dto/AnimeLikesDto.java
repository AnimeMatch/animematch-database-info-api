package animatch.app.service.Anime.dto;

public class AnimeLikesDto {
    private int likes;

    public AnimeLikesDto() {
    }

    public AnimeLikesDto(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

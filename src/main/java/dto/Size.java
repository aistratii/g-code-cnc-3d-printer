package dto;

public class Size {
    private final float width;
    private final float height;
    private final float volume;

    public Size(float width, float height, float volume) {
        this.width = width;
        this.height = height;
        this.volume = volume;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getVolume() {
        return volume;
    }
}

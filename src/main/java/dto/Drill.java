package dto;

public class Drill {
    private final float drillWidth;
    private final float drillConeHeight;
    private final float drillTotalHeight;

    public Drill(float drillWidth, float drillConeHeight, float drillTotalHeight) {
        this.drillWidth = drillWidth;
        this.drillConeHeight = drillConeHeight;
        this.drillTotalHeight = drillTotalHeight;
    }

    public float getDrillWidth() {
        return drillWidth;
    }

    public float getDrillConeHeight() {
        return drillConeHeight;
    }

    public float getDrillTotalHeight() {
        return drillTotalHeight;
    }
}

package yael.ao.uabc.p03;

public enum CarWashServices {
    VACUUM, WASH, DRY, EXPRESS_DRYING;

    public static CarWashServices getRandomService() {
        return values()[(int) (Math.random() * values().length)];
    }
}

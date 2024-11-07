package Entity;

public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double"),
    QUEEN("Queen"),
    TRIPLE("Triple"),
    QUAD("Quad");

    private String roomLabel;

    RoomType(String roomLabel) {
        this.roomLabel = roomLabel;
    }

    public String getRoomLabel() {
        return this.roomLabel;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel = roomLabel;
    }
}

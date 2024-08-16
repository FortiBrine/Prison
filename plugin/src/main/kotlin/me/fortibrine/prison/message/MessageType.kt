package me.fortibrine.prison.message

enum class MessageType (
    val path: String
) {
    FIRST_JOIN_TITLE("first-join.title"),
    FIRST_JOIN_SUBTITLE("first-join.subtitle"),
    NOT_OP("command.not-op"),
    NOT_PLAYER("command.not-player"),
    ADD_LOCATION("command.location.add-location"),
    MINE_UPDATE("mine.update");
}

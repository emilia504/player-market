package pl.codema.playermarket.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerNotExistException extends RuntimeException {

    public PlayerNotExistException(long id) {
        super("Player with id: " + id + " not found.");
    }

}

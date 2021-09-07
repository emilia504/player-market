package pl.codema.playermarket.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamNotExistException extends RuntimeException {

    public TeamNotExistException(long id) {
        super("Team with id: " + id + " not found.");
    }

}

package pl.codema.playermarket.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransferException extends RuntimeException {

    public TransferException(String message) {
        super(message);
    }

}

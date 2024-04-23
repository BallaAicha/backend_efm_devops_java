package org.etutoria.backend_android.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)//pour lui dire lorsque cette exception est levée, il faut retourner un code 400, qui est le bad request
public class EmailAlreadyExistsException extends RuntimeException {
    private String message;

    public EmailAlreadyExistsException(String message)
    {
        super(message);
    }

}

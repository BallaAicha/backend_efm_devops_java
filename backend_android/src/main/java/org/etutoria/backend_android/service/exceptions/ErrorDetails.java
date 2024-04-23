package org.etutoria.backend_android.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    //cette classe permet de renvoyer un message d'erreur plus détaillé
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String errorCode;


}

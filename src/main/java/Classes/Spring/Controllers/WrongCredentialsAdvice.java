package Classes.Spring.Controllers;

import Classes.Spring.Controllers.Exceptions.UserAlreadyExsistsException;
import Classes.Spring.Controllers.Exceptions.WrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WrongCredentialsAdvice {
    @ResponseBody
    @ExceptionHandler(WrongCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String personNotFoundHandler(WrongCredentialsException e) {
        return "Такого пользователя не существует";
    }
    @ResponseBody
    @ExceptionHandler(UserAlreadyExsistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String personAlreadyExistsHandler(WrongCredentialsException e) {
        return "Такой пользователь уже существует";
    }
}

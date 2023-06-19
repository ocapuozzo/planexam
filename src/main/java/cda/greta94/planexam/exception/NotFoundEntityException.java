package cda.greta94.planexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends RuntimeException {

  public NotFoundEntityException() {
  }

  public NotFoundEntityException(String message) {
    super(message);
  }

  public NotFoundEntityException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundEntityException(Throwable cause) {
    super(cause);
  }

  public NotFoundEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

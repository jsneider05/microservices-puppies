package com.puppies.post.infrastructure.configuration.error;

import com.puppies.post.domain.exception.ExternalApiException;
import com.puppies.post.domain.exception.InternalProcessException;
import com.puppies.post.domain.exception.InvalidValueException;
import com.puppies.post.domain.exception.NoDataException;
import com.puppies.post.domain.exception.RequiredValueException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  private static final String ERROR_CONTACT_ADMINISTRATOR = "An error has occurred. Please contact the administrator.";

  private static final ConcurrentHashMap<String, Integer> STATE_CODES = new ConcurrentHashMap<>();

  public ErrorHandler() {
    STATE_CODES.put(InvalidValueException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    STATE_CODES.put(NoDataException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    STATE_CODES.put(RequiredValueException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    STATE_CODES.put(ExternalApiException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    STATE_CODES.put(InternalProcessException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    STATE_CODES
        .put(ConstraintViolationException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    STATE_CODES.put(IllegalArgumentException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler(Throwable.class)
  private ResponseEntity<Error> handleAllExceptions(Throwable exception) {

    String exceptionName = exception.getClass().getSimpleName();
    Integer exceptionCode = STATE_CODES.get(exceptionName);

    return Optional.ofNullable(exceptionCode)
        .map((Integer code) -> {
          String message = exception.getMessage();
          List<String> messages = Stream.of(message).collect(Collectors.toList());
          HttpStatus httpStatus = HttpStatus.valueOf(code);
          Error error = new Error(LocalDateTime.now(), httpStatus.value(),
              httpStatus.getReasonPhrase(), messages);
          return new ResponseEntity<>(error, httpStatus);
        })
        .orElseGet(() -> {
          List<String> generalMessage = Stream.of(ERROR_CONTACT_ADMINISTRATOR)
              .toList();
          HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
          Error error = new Error(LocalDateTime.now(), httpStatus.value(),
              httpStatus.getReasonPhrase(), generalMessage);
          return new ResponseEntity<>(error, httpStatus);
        });
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException exception, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {

    List<String> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
        .map(error -> buildMessage.apply(error.getField(), error.getDefaultMessage()))
        .toList();

    List<String> globalErrors = exception.getBindingResult().getGlobalErrors().stream()
        .map(error -> buildMessage.apply(error.getObjectName(), error.getDefaultMessage()))
        .toList();

    List<String> errors = Stream.of(fieldErrors, globalErrors)
        .flatMap(List::stream)
        .sorted()
        .toList();

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    Error error = new Error(LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(),
        errors);

    return handleExceptionInternal(exception, error, headers,
        HttpStatus.valueOf(error.getStatusCode()), request);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private Error handleConstraintValidationException(ConstraintViolationException exception) {
    List<String> errors = exception.getConstraintViolations().stream()
        .map(error -> buildMessage
            .apply(getParameterName.apply(error.getPropertyPath()), error.getMessage()))
        .sorted()
        .toList();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return new Error(LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), errors);
  }

  private final BinaryOperator<String> buildMessage = (field, message) ->
      String.join(": ", field, message);

  private final Function<Path, String> getParameterName = path ->
      StreamSupport.stream(path.spliterator(), false)
          .map(Node::toString)
          .reduce((methodName, parameterName) -> parameterName)
          .orElse("The parameter");

  @ExceptionHandler(value = {BadCredentialsException.class})
  public ResponseEntity<Error> handleBadCredentialsException(BadCredentialsException ex){
    HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    Error error = new Error(LocalDateTime.now(), httpStatus.value(),
        httpStatus.getReasonPhrase(), List.of(ex.getMessage()));
    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler(value = {AccessDeniedException.class})
  public ResponseEntity<Error> handleAccessDeniedException(AccessDeniedException ex){
    HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    Error error = new Error(LocalDateTime.now(), httpStatus.value(),
        httpStatus.getReasonPhrase(), List.of(ex.getMessage()));
    return new ResponseEntity<>(error, httpStatus);
  }

}

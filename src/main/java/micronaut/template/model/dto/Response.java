package micronaut.template.model.dto;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;

import java.nio.charset.StandardCharsets;

/**
 * Response message DTO
 *
 * @author Anton Kurako
 * @since 14.09.2020
 */
public class Response {

    private boolean success;
    private String message;

    public static Response failed() {
        return failed("Unexpected error occurred");
    }

    public static Response failed(String message) {
        final Response response = new Response();
        response.success = false;
        response.message = message;
        return response;
    }

    public static Response failed(String message, Object... args) {
        final String formatted = String.format(message.replace("{}", "%s"), args);
        final Response response = new Response();
        response.success = false;
        response.message = formatted;
        return response;
    }

    public static Response success() {
        return success("Operation competed successfully");
    }

    public static Response success(String message, Object... args) {
        final String formatted = String.format(message.replace("{}", "%s"), args);
        final Response response = new Response();
        response.message = formatted;
        response.success = true;
        return response;
    }

    public MutableHttpResponse asHttp() {
        return asHttp(success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public MutableHttpResponse asHttp(HttpStatus status) {
        return HttpResponse.status(status)
                .body(toString())
                .contentType(MediaType.APPLICATION_JSON_TYPE)
                .characterEncoding(StandardCharsets.UTF_8);
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("{\"success\": %s, \"message\": \"%s\"}", success, message);
    }
}

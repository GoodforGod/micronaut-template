package micronaut.template.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import micronaut.template.model.dto.Response;

import javax.validation.constraints.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 14.09.2020
 */
@Controller("/test")
public class ApplicationController {

    @Get("/{name}")
    @Operation(
            summary = "Get Endpoint",
            description = "Get Endpoint Description",
            tags = { "Test" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "500", description = "Server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Response.class)))
            })
    public Single<HttpResponse> get(@NotNull @PathVariable String name) {
        return Single.just(Response.success("Name - " + name).asHttp());
    }

    @Post
    @Operation(
            summary = "Post Endpoint",
            description = "Post Endpoint Description",
            tags = { "Test" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "500", description = "Server error",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Response.class)))
            })
    public Single<HttpResponse> post(@NotNull @Body String message) {
        return Single.just(Response.success(message).asHttp());
    }
}

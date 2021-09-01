package io.app.micronaut.template.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.constraints.NotNull;
import reactor.core.publisher.Mono;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 14.09.2020
 */
@Controller("/hello")
public class ApplicationController {

    @Get("/{name}")
    @Operation(
            summary = "Get Endpoint",
            description = "Get Endpoint Description",
            tags = { "Test" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = MediaType.TEXT_PLAIN,
                                    schema = @Schema(implementation = String.class)))
            })
    public Mono<String> get(@NotNull @PathVariable String name) {
        return Mono.just("Name - " + name);
    }
}

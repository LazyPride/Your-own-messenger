package com.s1cket.labs.client.service;

import com.s1cket.labs.client.model.dto.UserLoginDto;
import com.s1cket.labs.client.model.dto.UserRegistrationDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RegistrationService {
    private final WebClient webClient;

    public RegistrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<UserRegistrationDto> registerUser(UserRegistrationDto userRegistrationDto) {
        return this.webClient.post()
                .uri("/user/register")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(userRegistrationDto), UserRegistrationDto.class)
                .retrieve()
                .bodyToMono(UserRegistrationDto.class);
    }

    public Mono<UserRegistrationDto> loginUser(UserLoginDto userLoginDto) {
        return this.webClient.post()
                .uri("/user/login")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(userLoginDto), UserLoginDto.class)
                .retrieve()
                .bodyToMono(UserRegistrationDto.class);
    }

}

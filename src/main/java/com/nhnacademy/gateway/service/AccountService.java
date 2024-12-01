package com.nhnacademy.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
public class AccountService {

    private final RestTemplate restTemplate;

}

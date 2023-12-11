package com.oreilly.library;

import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication
                .from(LibraryApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}

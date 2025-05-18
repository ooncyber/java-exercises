package com.example.kipper.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import com.example.kipper.service.HelloWorldService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    public String helloWorld() {
        return helloWorldService.helloWorld(" Gabriel");
    }

    @PostMapping
    public String helloWorldPost(@RequestParam("file") MultipartFile file) {
        System.out.println("recebendo o arquivo " + file.getOriginalFilename());
        try {
            // copia para máquina local
            // var path = "C:/Users/gabri/Desktop/top/" + file.getOriginalFilename();
            // Files.copy(file.getInputStream(), Path.of(path),
            // StandardCopyOption.REPLACE_EXISTING);



            // Copia para o bucket S3
            S3Client s3Client = S3Client.builder()
                    .region(Region.US_EAST_1)
                    .build();

            InputStream inputStream = file.getInputStream();
            PutObjectRequest.Builder putObjectRequestBuilder = PutObjectRequest.builder()
                    .bucket("aws-teste-java")
                    .key("teste/"+UUID.randomUUID().toString() +"-"+ file.getOriginalFilename());

            PutObjectRequest putObjectRequest = putObjectRequestBuilder.build();

            software.amazon.awssdk.core.sync.RequestBody requestBody = software.amazon.awssdk.core.sync.RequestBody
                    .fromInputStream(inputStream, file.getSize());
                    System.out.println("Enviando arquivo para o S3");
            s3Client.putObject(putObjectRequest, requestBody);
            System.out.println("Arquivo recebido com sucesso");
            return "Arquivo recebido com sucesso";
        } catch (Exception e) {
            System.out.println("Erro ao receber o arquivo");
            System.out.println(e);
            return "Erro ao receber o arquivo";
        }
    }

}

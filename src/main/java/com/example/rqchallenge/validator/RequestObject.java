package com.example.rqchallenge.validator;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class RequestObject {

    @EvenNumber(message = "Number should be even!")
    private long id;

    @Min(value = 10, message = "Too low")
    @Max(value = 25, message = "Too high")
    private double price;

    @Pattern(regexp = "d.*e", message = "Title is not valid. First character should be 'd' and the last one should be 'e'!")
    private String title;

    @Future
    private LocalDateTime date;

    @Email
    private String email;
}

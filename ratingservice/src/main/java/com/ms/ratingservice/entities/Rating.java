package com.ms.ratingservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("ratingservice_db")
@Builder
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private String feedback;
    private int rating;
}

package com.Ahmed.AhmedSpring.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "JobPost")
public class Post {

    private String profile;
    private String desc;
    private String exp;
    private String techs[];

}

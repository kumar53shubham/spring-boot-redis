package com.spring.cache.hash;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Customer")
@Builder
//In redis, entity is equivalent to hash
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dob;
    private String phone;
}

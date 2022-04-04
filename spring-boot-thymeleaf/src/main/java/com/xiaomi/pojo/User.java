package com.xiaomi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private Integer sex;
    private String address;
    private LocalDate birthday;
    private LocalDateTime createDate;
}

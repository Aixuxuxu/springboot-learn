package com.aixu.async.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Author 冠旭
 * @Date 2023/8/28 10:03
 */
@Data
@Builder
public class User {
    private Long id;
    private String name;
}

package com.aixu;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class redisTest {

//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Test
    void test1(){
        /*
        * redis测试常用的五种数据类型
        * */
        // 在redis中新增一条list类型的数据
        stringRedisTemplate.opsForList().leftPush("list", "从左边插入");
        stringRedisTemplate.opsForList().rightPush("list", "从右边插入");
        stringRedisTemplate.opsForList().rightPush("list", "aixu");
        // 在redis中新增一条string类型的数据
        stringRedisTemplate.opsForValue().set("name", "aixu");
        // 在redis中新增一条hash类型的数据
        stringRedisTemplate.opsForHash().put("hash", "name", "aixu");
        // 在redis中新增一条set类型的数据
        stringRedisTemplate.opsForSet().add("set", "aixu");
        // 在redis中新增一条zset类型的数据
        stringRedisTemplate.opsForZSet().add("zset", "aixu", 1);

    }

}

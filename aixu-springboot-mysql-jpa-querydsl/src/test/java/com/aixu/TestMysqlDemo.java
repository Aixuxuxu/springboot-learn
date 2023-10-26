package com.aixu;

import com.aixu.domain.user.QUser;
import com.aixu.domain.user.User;
import com.aixu.domain.user.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class TestMysqlDemo {

    @Autowired
    private UserRepository userRepository;


    @Test
    void testSql(){

        User user = new User()
                .setId(2)
                .setUser("test222222")
                .setAccount("account2222")
                .setVersion(1);
        User save = userRepository.save(user);
        System.out.println(save);

    }


    @Test
    void testQueryAndSqlUpdate(){
        BooleanBuilder and = new BooleanBuilder().and(QUser.user1.id.eq(2L));
        User user1 = userRepository.findOne(and).orElse(null);

        user1
                .setUser("test222222")
                .setAccount("account1")
               ;
        User save = userRepository.save(user1);
        System.out.println(save);

    }


    /**
     * Mysql乐观锁测试
     */
    @Test
    void concurrentUpdate(){

        int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);

        for (int i = 0; i < size; i++) {
            int finalI = i;
                // 并发修改数据库用户ID为1的属性值
                executorService.submit(() -> {

                    User user = userRepository
                            .findOne(new BooleanBuilder().and(QUser.user1.id.eq(1L)))
                            .orElse(null);

                    try {

                        user.setUser("我被插入了：" + finalI)
                            .setAccount("account1");
                        // 更新操作
                        userRepository.save(user);

                        log.info(finalI + "争夺成功");

                    } catch (Exception e) {

                        log.error("争夺失败");
                        log.error("报错信息=====>{}",e.getMessage() );
                        // 如果出现空指针异常，中断当前线程
                        Thread.currentThread().interrupt();
                    }

                });

        }

        // 主线程阻塞等待子线程执行完毕
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("报错");
        }finally {
            executorService.shutdown();
        }

    }
}

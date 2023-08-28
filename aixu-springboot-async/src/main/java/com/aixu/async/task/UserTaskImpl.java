package com.aixu.async.task;

import com.aixu.async.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Author 冠旭
 * @Date 2023/8/28 10:06
 */
@Slf4j
@Service
public class UserTaskImpl implements UserTaskService {


    /**
     * 查询用户部门信息
     */
    @SneakyThrows
    @Async
    @Override
    public Future<Boolean> queryDeptInfo(User user) {
        long start = System.currentTimeMillis();
        Thread.sleep(1000); // 假设查询需要1000毫秒
        long end = System.currentTimeMillis();
        log.info("{}：耗时({})毫秒", Thread.currentThread().getName(), (end - start));
        return new AsyncResult<>(true);
    }

    /** 查询用户角色信息 */
    @Async
    @SneakyThrows
    @Override
    public Future<Boolean> queryRoleInfo(User user) {
        long start = System.currentTimeMillis();
        Thread.sleep(500);  // 假设查询需要1000毫秒
        long end = System.currentTimeMillis();
        log.info("{}：耗时({})毫秒", Thread.currentThread().getName(), (end - start));
        return new AsyncResult<>(true);
    }
}

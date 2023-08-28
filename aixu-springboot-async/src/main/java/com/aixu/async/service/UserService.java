package com.aixu.async.service;

import cn.hutool.core.map.MapUtil;
import com.aixu.async.model.User;
import com.aixu.async.task.UserTaskImpl;
import com.aixu.async.task.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Author 冠旭
 * @Date 2023/8/28 10:05
 */
@Slf4j
@Service
public class UserService {


    @Resource
    UserTaskService userTask ;

    /** 模拟用户存储 */
    private static final Map<Long, User> USER_MAP = MapUtil.newHashMap();

    static {
        USER_MAP.put(1L, User.builder().id(1L).name("aixu").build());
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public User queryUserById(Long userId) {
        long start = System.currentTimeMillis();

        // 1. 先查询用户信息
        User mingYueUser = USER_MAP.get(userId);
        /*
         * 同时查询用户部门或角色信息
         */
        // 1.异步查询部门信息
        Future<Boolean> deptInfo = userTask.queryDeptInfo(mingYueUser);
        // 2.异步查询角色信息
        Future<Boolean> roleInfo = userTask.queryRoleInfo(mingYueUser);

        while (!deptInfo.isDone() || !roleInfo.isDone()) {
            // 当所有的任务都执行完时，退出
            if (deptInfo.isDone() && roleInfo.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        log.info("任务全部完成，总耗时：({})毫秒", end - start);
        return USER_MAP.get(userId);
    }
}

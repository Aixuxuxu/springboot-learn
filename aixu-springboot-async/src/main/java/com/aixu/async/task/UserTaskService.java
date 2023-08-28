package com.aixu.async.task;

import com.aixu.async.model.User;

import java.util.concurrent.Future;

/**
 * @Author 冠旭
 * @Date 2023/8/28 10:32
 */
public interface UserTaskService {

    Future<Boolean> queryDeptInfo(User user);

    Future<Boolean> queryRoleInfo(User user);
}

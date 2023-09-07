package com.aixu.knife4j.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "标签")
@RequestMapping("/test2")
@ApiSupport(author = "Ai_xu")
public class TestTwoController {

    @Operation(summary = "接口2")
    @Parameters({
            @Parameter(name = "id",description = "用户id",in = ParameterIn.PATH),
            @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "name",description = "姓名",required = true,in= ParameterIn.QUERY)
    })
    @GetMapping("/getTest/{id}")
    public String getTest(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name){

        return "Hello "+ name + "Test2!!!";
    }
}

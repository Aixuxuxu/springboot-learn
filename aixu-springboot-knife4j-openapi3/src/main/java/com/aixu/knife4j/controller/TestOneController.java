package com.aixu.knife4j.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "标签")
@ApiSupport(author = "Ai_xu",order = 284)
@RequestMapping("/test1")

public class TestOneController {


    @Operation(summary = "接口1")
    @Parameters({
//            @Parameter(name = "id",description = "文件id",in = ParameterIn.PATH),
//            @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "name",description = "姓名",required = true,in=ParameterIn.QUERY)
    })
    @GetMapping("/getTest")
    public String getTest( @RequestParam("name") String name){

        return "Hello "+ name + "Test1!!!";
    }

}

package com.penglecode.codeforce.samples.common.api.controller;

import com.penglecode.codeforce.common.consts.GlobalConstants;
import com.penglecode.codeforce.common.model.Result;
import com.penglecode.codeforce.common.web.servlet.support.ServletHttpApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 示例Controller
 *
 * @author pengpeng
 * @version 1.0
 */
@RestController
@RequestMapping("/api/example")
@Tag(name="ExampleController", description="示例AI接口")
public class ExampleController extends ServletHttpApiSupport {

    private final Logger logger = LoggerFactory.getLogger(ExampleController.class);

    private final ExecutorService executorService;

    public ExampleController() {
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Operation(summary="获取应用信息")
    @GetMapping(value="/appinfo", produces=MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> getAppInfo() throws Exception {
        logger.info(">>> 获取应用信息开始：traceId = {}", MDC.get(GlobalConstants.DEFAULT_MDC_TRACE_ID_KEY));
        logger.info(">>> 获取应用信息开始：context = {}", MDC.getCopyOfContextMap());
        Future<Map<String,Object>> future = executorService.submit(() -> {
            Map<String,Object> appInfo = new HashMap<>();
            String appName = getEnvironment().getProperty("spring.application.name");
            String appCode = getEnvironment().getProperty("spring.application.code");
            appInfo.put("appName", appName);
            appInfo.put("appCode", appCode);
            logger.info("<<< 成功获取应用信息：traceId = {}", MDC.get(GlobalConstants.DEFAULT_MDC_TRACE_ID_KEY));
            logger.info("<<< 成功获取应用信息：context = {}", MDC.getCopyOfContextMap());
            return appInfo;
        });
        Object data = future.get();
        logger.info("<<< 获取应用信息结束：traceId = {}", MDC.get(GlobalConstants.DEFAULT_MDC_TRACE_ID_KEY));
        logger.info("<<< 获取应用信息结束：context = {}", MDC.getCopyOfContextMap());
        return Result.success().data(data).build();
    }

}

package com.L.exception;

import com.L.utils.LJSONResult;

@RestControllerAdvice
public class CustomExceptionHandler {

    // 上传文件超过500k，捕获异常：MaxUploadSizeExceededException
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public LJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        return LJSONResult.errorMsg("文件上传大小不能超过500k，请压缩图片或者降低图片质量");
    }
}

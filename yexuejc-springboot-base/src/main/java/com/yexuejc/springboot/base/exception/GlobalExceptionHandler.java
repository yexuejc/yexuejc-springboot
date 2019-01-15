package com.yexuejc.springboot.base.exception;

import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.constant.RespsCode;
import com.yexuejc.springboot.base.http.ResponseParams;
import com.yexuejc.springboot.base.util.LogUtil;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理
 * <p>处理成 response:200 返回</p>
 *
 * @author maxf
 * @version 1.0
 * @ClassName GlobalExceptionHandler
 * @date 2018/12/26 17:49
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 出现异常时用于返回Json数据
     *
     * @param e
     * @return
     */
    @ExceptionHandler({
            Throwable.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class,
            AsyncRequestTimeoutException.class,
            NullPointerException.class,
            AuthenticationException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseParams jsonErrorHandler(Throwable e) {
        LogUtil.exceptionLogger.error("", e);
        if (e instanceof BasicException) {
            //自定义异常
            BasicException exp = (BasicException) e;
            String msg = StrUtil.isNotEmpty(exp.getMessage()) ? exp.getMessage() :
                    exp.respsSubCode != null ? exp.respsSubCode.msg : exp.code.msg;

            ResponseParams responseParams = new ResponseParams();
            responseParams.setCode(exp.code);
            responseParams.setSubCode(StrUtil.isNotEmpty(exp.subCode) ? exp.subCode :
                    exp.respsSubCode != null ? exp.respsSubCode.code : null);
            if (StrUtil.isEmpty(responseParams.getSubCode())) {
                responseParams.setMsg(msg);
            } else {
                responseParams.setSubMsg(msg);
            }
            return responseParams;
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseParams.resps(RespsCode.INTERIOR_BIZ_ERR);
        } else if (e instanceof HttpMessageNotReadableException) {
            return ResponseParams.resps(RespsCode.MIS_PARAM);
        }
        return ResponseParams.resps(RespsCode.INTERIOR_BIZ_ERR);
    }

}
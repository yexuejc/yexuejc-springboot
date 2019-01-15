package com.yexuejc.springboot.base.util;

import com.yexuejc.base.constant.RespsConsts;
import com.yexuejc.base.http.Resps;
import com.yexuejc.springboot.base.constant.RespsCode;
import com.yexuejc.springboot.base.http.ResponseParams;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 检查SpringMVC提交是否有数据校验错误
 *
 * @PackageName: com.yexuejc.util.base.util
 * @Description:
 * @author: maxf
 * @date: 2018/1/17 14:01
 */
public class ValidUtil {
    private ValidUtil() {
    }

    /**
     * APP用； 检查SpringMVC提交是否有数据校验错误，如果有错则直接response错误信息
     *
     * @param errors
     * @return
     * @throws IOException
     * @since 1.0
     */
    public static Resps<Object> errResps(HttpServletResponse response, Errors errors) throws IOException {
        List<ObjectError> objectErrorList = errors.getAllErrors();
        String[] err = new String[objectErrorList.size()];
        for (int i = 0; i < objectErrorList.size(); i++) {
            err[i] = objectErrorList.get(i).getDefaultMessage() == null ? objectErrorList.get(i).getCode() : objectErrorList.get(i).getDefaultMessage();
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Resps.error(RespsConsts.CODE_VALIDATION, err);
    }

    /**
     * 参数加密使用； 检查SpringMVC提交是否有数据校验错误，如果有错则直接response错误信息
     *
     * @param errors
     * @return
     * @throws IOException
     * @since 2.0
     */
    public static ResponseParams errResps2( Errors errors) {
        List<ObjectError> objectErrorList = errors.getAllErrors();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < objectErrorList.size(); i++) {
            sb.append(i + 1 + ":" + objectErrorList.get(i).getDefaultMessage() + ";");
        }
        return ResponseParams.resps(RespsCode.MIS_PARAM.code, sb.toString().substring(0, sb.length() - 1));
    }

}
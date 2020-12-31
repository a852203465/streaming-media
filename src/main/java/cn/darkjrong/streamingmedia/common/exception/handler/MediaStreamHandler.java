package cn.darkjrong.streamingmedia.common.exception.handler;

import cn.darkjrong.streamingmedia.common.enums.ResponseEnum;
import cn.darkjrong.streamingmedia.common.exception.MediaStreamException;
import cn.darkjrong.streamingmedia.common.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.crypto.BadPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.LinkedList;
import java.util.List;

/**
 *  异常控制处理器
 * @author Rong.Jia
 * @date 2019/4/3
 */
@Slf4j
@SuppressWarnings("ALL")
@RestControllerAdvice
public class MediaStreamHandler {

    /**
     *  捕获自定义异常，并返回异常数据
     * @author Rong.Jia
     * @date 2019/4/3 8:46
     */
    @ExceptionHandler(value = MediaStreamException.class)
    public ResponseVO mediaStreamExceptionHandler(MediaStreamException e){

        log.error("mediaStreamExceptionHandler {}", e.getMessage());

        return ResponseVO.error(e.getMessage());

    }

    /**
     *  捕获不合法的参数异常，并返回异常数据
     * @author Rong.Jia
     * @date 2019/4/3 8:46
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseVO illegalArgumentExceptionHandler(IllegalArgumentException e){

        log.error("illegalArgumentExceptionHandler  {}", e.getMessage());

        return ResponseVO.error(e.getMessage());
    }

    /**
     *  捕捉404异常
     * @param e 404 异常
     * @date 2019/04/17 09:46:22
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseVO noHandlerFoundHandle(NoHandlerFoundException e) {

        log.error("noHandlerFoundHandle {}", e.getMessage());

        return new ResponseVO(ResponseEnum.NOT_FOUND);

    }

    /**
     *  字段验证异常处理
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVO handle(MethodArgumentNotValidException e){
        List<String> errorList = new LinkedList<>();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError error : errors ) {

            log.error ("MethodArgumentNotValidException : {} - {}",error.getObjectName(),error.getDefaultMessage());
            errorList.add(error.getDefaultMessage());

        }

        log.error("MethodArgumentNotValidExceptionHandle :{}",e.getMessage());

        return ResponseVO.error(errorList.get(0));

    }

    /**
     *  字段验证异常处理
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseVO bindExceptionhandle(BindException e){
        List<String> errorList = new LinkedList<>();
        List<ObjectError> errors = e.getAllErrors();
        for (ObjectError error : errors ) {

            log.error ("bindExceptionhandle : {} - {}",error.getObjectName(), error.getDefaultMessage());
            errorList.add(error.getDefaultMessage());

        }

        log.error("bindExceptionhandle :{}", errors.toString());

        return ResponseVO.error(errorList.get(0));

    }

    /**
     *  参数类型不正确异常
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2020/03/25 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseVO methodArgumentTypeMismatchExceptionException(Exception e) {

        log.error("methodArgumentTypeMismatchExceptionException : {}",e.getMessage());

        return ResponseVO.error(ResponseEnum.THE_PARAMETER_TYPE_IS_INCORRECT);

    }

    /**
     *  不支持当前请求方法
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

        log.error("HttpRequestMethodNotSupportedException : {}",e.getMessage());

        return ResponseVO.error(ResponseEnum.REQUEST_MODE_ERROR);

    }

    /**
     *  不支持当前媒体类型
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseVO handleHttpMediaTypeNotSupportedException(Exception e) {

        log.error("HttpMediaTypeNotSupportedException : {}",e.getMessage());

        return ResponseVO.error(ResponseEnum.MEDIA_TYPE_NOT_SUPPORTED);

    }

    /**
     *  默认异常处理
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO defaultErrorHandler( Exception e) {

        if (e.getCause() instanceof MediaStreamException) {
            mediaStreamExceptionHandler((MediaStreamException)e);
        }

        if (e.getCause() instanceof IllegalArgumentException) {
            illegalArgumentExceptionHandler((IllegalArgumentException)e.getCause());
        }

        if (e.getCause() instanceof MethodArgumentTypeMismatchException) {
            methodArgumentTypeMismatchExceptionException((MethodArgumentTypeMismatchException)e.getCause());
        }

        log.error("defaultErrorHandler : {}", e.getMessage());

        return ResponseVO.error(e.getMessage());
    }

    /**
     *  默认运行异常处理
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseVO runtimeErrorHandler(RuntimeException e) {

        if (e.getCause() instanceof MediaStreamException) {
            mediaStreamExceptionHandler((MediaStreamException)e);
        }

        if(e.getCause() instanceof HttpMessageNotReadableException){
            httpMesssageHandler((HttpMessageNotReadableException)e.getCause());
        }

        if (e.getCause() instanceof IllegalArgumentException) {
            illegalArgumentExceptionHandler((IllegalArgumentException)e.getCause());
        }

        if (e.getCause() instanceof MethodArgumentTypeMismatchException) {
            methodArgumentTypeMismatchExceptionException((MethodArgumentTypeMismatchException)e.getCause());
        }

        log.error("runtimeErrorHandler : {}", e.getMessage());

        return ResponseVO.error(e.getMessage());
    }

    /**
     *  使用反射或者代理造成的异常需要根据异常类型单独处理
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(value = UndeclaredThrowableException.class)
    @ResponseBody
    public ResponseVO undeclaredThrowableException(HttpServletRequest req, UndeclaredThrowableException e) {

        //密文解密失败异常
        if (e.getCause() instanceof BadPaddingException) {

            log.error("BadPaddingException : {}",e.getMessage());
            return ResponseVO.error(e.getMessage());
        }

        log.error("UndeclaredThrowableException : {}",e.getMessage());
        return ResponseVO.error(e.getMessage());
    }

    /**
     *  http 请求参数格式错误
     * @param e  异常信息
     * @author Rong.Jia
     * @date 2018/8/4 20:02
     * @return ResponseVO 返回异常信息
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseVO  httpMesssageHandler(HttpMessageNotReadableException e){

        log.error("HttpMessageNotReadableException : {}",e.getMessage());

        return ResponseVO.error(ResponseEnum.REQUEST_PARAMETER_FORMAT_IS_INCORRECT);
    }


}

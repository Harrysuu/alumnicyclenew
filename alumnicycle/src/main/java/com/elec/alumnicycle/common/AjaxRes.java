package com.elec.alumnicycle.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Ajax return data type
 *
 * @author YngJea
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Return Type entity",description = "Return data")
public class AjaxRes<T> implements Serializable{

    private static final long serialVersionUID = 6390864154586650382L;

    /**
     * return code,default Const.REQUEST_FAILED
     */
    @ApiModelProperty(value = "return code", required = true)
    private int res = Const.SUCCESS;

    /**
     * return message
     */
    @ApiModelProperty(value = "return code")
    private String resMsg;

    /**
     * return object
     */
    @ApiModelProperty(value = "return object")
    private T result;

    /**
     * error stack
     */
    @ApiModelProperty(value = "error stack")
    private String stackTrace;

    public static <T> AjaxRes<T> success(T obj) {
        return success(obj, Const.SUCCESS_MSG);
    }

    public static <T> AjaxRes<T> success() {
        return success(null, Const.SUCCESS_MSG);
    }

    public static <T> AjaxRes<T> successMsg(String resMsg) {
        return success(null, resMsg);
    }

    public static <T> AjaxRes <T>success(T obj, String resMsg) {
        AjaxRes<T> ajaxRes = new AjaxRes<T>();
        ajaxRes.setSuccess(obj, resMsg);
        return ajaxRes;
    }

    /**
     * fail message
     */
    public static <T> AjaxRes <T>fail() {
        return fail(null, Const.FAIL_MSG);
    }

    public static <T> AjaxRes<T> fail(T obj) {
        return fail(obj, Const.FAIL_MSG);
    }

    public static <T> AjaxRes<T> failMsg(String resMsg) {
        return fail(null, resMsg);
    }

    public static <T> AjaxRes<T> fail(T obj, String resMsg) {
        AjaxRes<T> ajaxRes = new AjaxRes<T>();
        ajaxRes.setFailMsg(obj, resMsg);
        return ajaxRes;
    }

    /**
     * success message
     *
     * @param obj    object
     * @param resMsg message
     */
    private AjaxRes<T> setSuccess(T obj, String resMsg) {
        this.setResMsg(resMsg);
        this.setResult(obj);
        this.setRes(Const.SUCCESS);
        return this;
    }

    /**
     * fail message
     *
     * @param obj    object
     * @param resMsg message
     */
    private AjaxRes<T> setFailMsg(T obj, String resMsg) {
        this.setResMsg(resMsg);
        this.setResult(obj);
        this.setRes(Const.FAIL);
        return this;
    }

    public T getResult() {

        return this.result;
    }

    @Override
    public String toString() {
        return "AjaxRes{" +
                "res=" + res +
                ", resMsg='" + resMsg + '\'' +
                ", result=" + result +
                ", stackTrace='" + stackTrace + '\'' +
                '}';
    }

}

package com.elec.alumnicycle.common;

import com.elec.alumnicycle.entity.ShoppingCart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Ajax encapsulation
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "return entity",description = "return data")
public class AjaxRes<T> implements Serializable{

    private static final long serialVersionUID = 6390864154586650382L;

    /**
     * default Const.REQUEST_FAILED
     */
    @ApiModelProperty(value = "return value", required = true)
    private int res = Const.SUCCESS;

    /**
     * return message
     */
    @ApiModelProperty(value = "return value")
    private String resMsg;

    /**
     * return object
     */
    @ApiModelProperty(value = "return object")
    private T result;

    /**
     * wrong message stack
     */
    @ApiModelProperty(value = "wrong message stack")
    private String stackTrace;

    /**
     * return success object
     *
     * @param obj object
     */
    public static <T> AjaxRes<T> success(T obj) {
        return success(obj, Const.SUCCESS_MSG);
    }

    /**
     * return success
     *
     * @return
     */
    public static <T> AjaxRes<T> success() {
        return success(null, Const.SUCCESS_MSG);
    }

    /**
     * return success
     *
     * @param resMsg return resMsg
     */
    public static <T> AjaxRes<T> successMsg(String resMsg) {
        return success(null, resMsg);
    }

    /**
     * return success
     *
     * @param resMsg ajaxRes
     */
    public static <T> AjaxRes <T>success(T obj, String resMsg) {
        AjaxRes<T> ajaxRes = new AjaxRes<T>();
        ajaxRes.setSuccess(obj, resMsg);
        return ajaxRes;
    }

    /**
     * return fail
     */
    public static <T> AjaxRes <T>fail() {
        return fail(null, Const.FAIL_MSG);
    }

    /**
     * return fail
     *
     * @param obj object
     */
    public static <T> AjaxRes<T> fail(T obj) {
        return fail(obj, Const.FAIL_MSG);
    }

    /**
     * return fail
     *
     * @param resMsg
     */
    public static <T> AjaxRes<T> failMsg(String resMsg) {
        return fail(null, resMsg);
    }

    /**
     * return fail
     *
     * @param resMsg ajaxRes
     */
    public static <T> AjaxRes<T> fail(T obj, String resMsg) {
        AjaxRes<T> ajaxRes = new AjaxRes<T>();
        ajaxRes.setFailMsg(obj, resMsg);
        return ajaxRes;
    }

    /**
     * set success
     *
     * @param obj    set object
     * @param resMsg set resMsg
     */
    private AjaxRes<T> setSuccess(T obj, String resMsg) {
        this.setResMsg(resMsg);
        this.setResult(obj);
        this.setRes(Const.SUCCESS);
        return this;
    }

    /**
     * return fail
     *
     * @param obj    set object
     * @param resMsg set resMsg
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

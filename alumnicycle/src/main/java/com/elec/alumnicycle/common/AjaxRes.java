package com.elec.alumnicycle.common;

import com.elec.alumnicycle.entity.ShoppingCart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Ajax封装类
 *
 * @author YngJea
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "返回数据实体",description = "返回数据")
public class AjaxRes<T> implements Serializable{

    private static final long serialVersionUID = 6390864154586650382L;

    /**
     * 返回码值,默认值Const.REQUEST_FAILED
     */
    @ApiModelProperty(value = "返回码值", required = true)
    private int res = Const.SUCCESS;

    /**
     * 返回消息
     */
    @ApiModelProperty(value = "返回码值")
    private String resMsg;

    /**
     * 返回对象
     */
    @ApiModelProperty(value = "返回对象")
    private T result;

    /**
     * 错误堆栈
     */
    @ApiModelProperty(value = "错误堆栈")
    private String stackTrace;

    /**
     * 返回成功值
     *
     * @param obj 设置对象
     */
    public static <T> AjaxRes<T> success(T obj) {
        return success(obj, Const.SUCCESS_MSG);
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static <T> AjaxRes<T> success() {
        return success(null, Const.SUCCESS_MSG);
    }

    /**
     * 返回成功值
     *
     * @param resMsg 返回码值解析
     */
    public static <T> AjaxRes<T> successMsg(String resMsg) {
        return success(null, resMsg);
    }

    /**
     * 返回成功值
     *
     * @param resMsg 返回码值解析
     */
    public static <T> AjaxRes <T>success(T obj, String resMsg) {
        AjaxRes<T> ajaxRes = new AjaxRes<T>();
        ajaxRes.setSuccess(obj, resMsg);
        return ajaxRes;
    }

    /**
     * 返回失败值
     */
    public static <T> AjaxRes <T>fail() {
        return fail(null, Const.FAIL_MSG);
    }

    /**
     * 返回失败值
     *
     * @param obj 设置对象
     */
    public static <T> AjaxRes<T> fail(T obj) {
        return fail(obj, Const.FAIL_MSG);
    }

    /**
     * 返回失败值
     *
     * @param resMsg 返回码值解析
     */
    public static <T> AjaxRes<T> failMsg(String resMsg) {
        return fail(null, resMsg);
    }

    /**
     * 返回失败值
     *
     * @param resMsg 返回码值解析
     */
    public static <T> AjaxRes<T> fail(T obj, String resMsg) {
        AjaxRes<T> ajaxRes = new AjaxRes<T>();
        ajaxRes.setFailMsg(obj, resMsg);
        return ajaxRes;
    }

    /**
     * 设置成功值
     *
     * @param obj    设置对象
     * @param resMsg 设置码值解析
     */
    private AjaxRes<T> setSuccess(T obj, String resMsg) {
        this.setResMsg(resMsg);
        this.setResult(obj);
        this.setRes(Const.SUCCESS);
        return this;
    }

    /**
     * 返回失败值
     *
     * @param obj    设置对象
     * @param resMsg 设置码值解析
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

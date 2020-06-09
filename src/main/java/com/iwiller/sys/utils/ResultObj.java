package com.iwiller.sys.utils;

import com.iwiller.sys.constants.SysConstants;

public class ResultObj {
    private Integer code = 0;
    private String msg;
    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS = new ResultObj(SysConstants.CODE_SUCCESS,SysConstants.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR = new ResultObj(SysConstants.CODE_ERROR,SysConstants.ADD_ERROR);
    /**
     * 更新成功
     */
    public static final ResultObj UPDATE_SUCCESS = new ResultObj(SysConstants.CODE_SUCCESS,SysConstants.UPDATE_SUCCESS);
    /**
     * 更新失败
     */
    public static final ResultObj UPDATE_ERROR = new ResultObj(SysConstants.CODE_ERROR,SysConstants.UPDATE_ERROR);
    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS = new ResultObj(SysConstants.CODE_SUCCESS,SysConstants.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR = new ResultObj(SysConstants.CODE_ERROR,SysConstants.DELETE_ERROR);
    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS = new ResultObj(SysConstants.CODE_SUCCESS,SysConstants.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR = new ResultObj(SysConstants.CODE_ERROR,SysConstants.RESET_ERROR);
    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(SysConstants.CODE_SUCCESS,SysConstants.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR = new ResultObj(SysConstants.CODE_ERROR,SysConstants.DISPATCH_ERROR);

    /**
     * 分配成功
     */
    public static final ResultObj STATUS_TRUE = new ResultObj(SysConstants.CODE_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj STATUS_FALSE = new ResultObj(SysConstants.CODE_ERROR);


    private ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultObj(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

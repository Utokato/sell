package com.ml.sell.utils;

import com.ml.sell.VO.ResultVO;

/**
 * 返回结果工具类
 *
 * @author
 */
public class ResultVOUtils {

    /**
     * 成功时，根据对象信息返回ResultVO
     *
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    /**
     * 成功时，返回ResultVO
     *
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }

    /**
     * 错误时，根据错误信息返回ResultVO
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}

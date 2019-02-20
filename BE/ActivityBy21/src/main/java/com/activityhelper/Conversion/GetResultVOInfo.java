package com.activityhelper.Conversion;

import com.activityhelper.Pojo.ResultVO;
import org.springframework.stereotype.Component;

/**
 * @author byene
 * @date 2019/2/10 5:55 AM
 */

@Component
public class GetResultVOInfo<T> {

    public ResultVO GetResultVO( Integer code, String msg, T data )
    {
        ResultVO resultVO = new ResultVO();

        resultVO.setCode( code );
        resultVO.setMsg( msg );
        resultVO.setData( data );
        return resultVO;
    }
}

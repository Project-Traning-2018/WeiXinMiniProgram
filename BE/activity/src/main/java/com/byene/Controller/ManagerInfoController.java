package com.byene.Controller;

import com.byene.Dao.ManagerInfo;
import com.byene.Enums.ManagerInfoStatusEnum;
import com.byene.Pojo.ManagerInfo2Back;
import com.byene.Pojo.ResultVO;
import com.byene.Service.ManagerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author byene
 * @date 2019/1/11 9:27 PM
 */

@RestController
@RequestMapping( "/back" )
public class ManagerInfoController {

    @Autowired
    ManagerInfoService managerInfoService;

    /*添加管理员账号*/
    @PostMapping( "/managerinfo/save" )
    public ResultVO managerSave(@RequestBody ManagerInfo2Back managerInfo2Back )
    {
        ResultVO resultVO = new ResultVO();

        ManagerInfo managerInfo = new ManagerInfo();

        /*获取注册的管理员账号*/
        String managerloginnumber = managerInfo2Back.getManagerLoginno();

        /*账号重复，返回失败*/
        if( managerInfoService.findbymanagerId( managerloginnumber ) != null )
        {
            resultVO.setCode( ManagerInfoStatusEnum.ACCOUNT_ERROR.getCode() );
            resultVO.setMsg(  ManagerInfoStatusEnum.ACCOUNT_ERROR.getMessage() );
            return resultVO;
        }

        /*将managerInfo2Back复制到managerInfo中*/
        managerInfo.setManagerNickname( managerInfo2Back.getManagerNickname() );
        managerInfo.setManagerLoginno( managerInfo2Back.getManagerLoginno() );
        managerInfo.setManagerPassword( managerInfo2Back.getManagerPassword() );

        managerInfoService.save( managerInfo );
        resultVO.setCode( ManagerInfoStatusEnum.ACCOUNT_SUCCESS.getCode() );
        resultVO.setMsg( ManagerInfoStatusEnum.ACCOUNT_SUCCESS.getMessage() );
        return resultVO;
    }

    /*更新管理员账号信息*/
    @PostMapping( "/managerinfo/update" )
    public ResultVO managerUpdate( @RequestBody ManagerInfo2Back managerInfo2Back )
    {
        ResultVO resultVO = new ResultVO();

        /*获取更新的管理员账号*/
        String ManagerLoginno = managerInfo2Back.getManagerLoginno();

        /*由管理员账号获取完整信息*/
        ManagerInfo managerInfo = managerInfoService.findbymanagerId( ManagerLoginno );

        /*更新账号信息*/
        managerInfo.setManagerNickname( managerInfo2Back.getManagerNickname() );
        managerInfo.setManagerLoginno( managerInfo2Back.getManagerLoginno() );
        managerInfo.setManagerPassword( managerInfo2Back.getManagerPassword() );

        managerInfoService.save( managerInfo );
        resultVO.setCode( ManagerInfoStatusEnum.ACCOUNT_UPDATE_SUCCESS.getCode() );
        resultVO.setMsg( ManagerInfoStatusEnum.ACCOUNT_UPDATE_SUCCESS.getMessage() );
        return resultVO;
    }


    /*获取管理员账号信息列表*/
    @PostMapping( "/managerinfo/list" )
    public ResultVO managerInfoList(  @RequestParam("managerLoginno") String  managerLoginno )
    {
        ResultVO resultVO = new ResultVO();

        List< ManagerInfo > managerInfoList = managerInfoService.findall( managerLoginno );

        resultVO.setCode( ManagerInfoStatusEnum.ACCOUNT_FIND_SUCCESS.getCode() );
        resultVO.setMsg( ManagerInfoStatusEnum.ACCOUNT_FIND_SUCCESS.getMessage() );
        resultVO.setData( managerInfoList );
        return resultVO;
    }

    /*删除管理员账号信息*/
    @PostMapping( "/managerinfo/delete" )
    public ResultVO managerInfoDelete( @RequestParam("managerLoginno") String managerLoginno )
    {
        ResultVO resultVO = new ResultVO();

        managerInfoService.delete( managerLoginno );

        resultVO.setCode( ManagerInfoStatusEnum.ACCOUNT_DELETE_SUCCESS.getCode() );
        resultVO.setMsg( ManagerInfoStatusEnum.ACCOUNT_DELETE_SUCCESS.getMessage() );
        return resultVO;
    }

    /*判断是否为超级管理员*/
    @PostMapping( "/managerinfo/supermanagercheck" )
    public ResultVO supermanagerCheck( @RequestParam("managerLoginno") String managerLoginno )
    {
        ResultVO resultVO = new ResultVO();
        if( managerLoginno.equals( "root" ) )
        {
            resultVO.setCode( ManagerInfoStatusEnum.IS_SUPERMANAGER.getCode() );
            resultVO.setMsg( ManagerInfoStatusEnum.IS_SUPERMANAGER.getMessage() );
            return resultVO;
        }

        resultVO.setCode( ManagerInfoStatusEnum.NOT_SUPERMANAGER.getCode() );
        resultVO.setMsg( ManagerInfoStatusEnum.NOT_SUPERMANAGER.getMessage() );
        return resultVO;
    }
}

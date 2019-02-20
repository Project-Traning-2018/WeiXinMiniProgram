package com.activityhelper.BackController;

import com.activityhelper.Pojo.ManagerInfo2Back;
import com.activityhelper.Pojo.ResultVO;
import com.activityhelper.Service.impl.ManagerInfoServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author byene
 * @date 2019/2/14 7:16 PM
 */

@RestController
@RequestMapping( "/back" )
public class ManagerInfoController
{

    @Autowired
    ManagerInfoServiceimpl managerInfoService;

    /*添加管理员账号*/
    @PostMapping( "/managerinfo/save" )
    public ResultVO managerSave(@RequestBody ManagerInfo2Back managerInfo2Back )
    {
        return managerInfoService.managerSave( managerInfo2Back );
    }

    /*更新管理员账号信息*/
    @PostMapping( "/managerinfo/update" )
    public ResultVO managerUpdate( @RequestBody ManagerInfo2Back managerInfo2Back )
    {
        return managerInfoService.managerUpdate( managerInfo2Back );
    }

    /*获取管理员账号信息列表*/
    @PostMapping( "/managerinfo/list" )
    public ResultVO managerInfoList(  @RequestParam("managerLoginno") String  managerLoginno )
    {
        return managerInfoService.managerInfoList( managerLoginno );
    }

    /*删除管理员账号信息*/
    @PostMapping( "/managerinfo/delete" )
    public ResultVO managerInfoDelete( @RequestParam("managerLoginno") String managerLoginno )
    {
        return managerInfoService.managerInfoDelete( managerLoginno );
    }

    /*判断是否为超级管理员*/
    @PostMapping( "/managerinfo/supermanagercheck" )
    public ResultVO supermanagerCheck( @RequestParam("managerLoginno") String managerLoginno )
    {
        return managerInfoService.supermanagerCheck( managerLoginno );
    }

    /*搜索管理员账号*/
    @PostMapping( "/managerinfo/search" )
    public ResultVO managerSearch( @RequestParam("str") String str )
    {
        return managerInfoService.SearchmanagerList( str );
    }
}

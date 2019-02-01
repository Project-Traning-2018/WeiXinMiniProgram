package com.byene.Controller;

import com.byene.Enums.FileStatusEnum;
import com.byene.Pojo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author byene
 * @date 2019/1/29 12:51 AM
 */
@RestController
@RequestMapping( "/file" )
@Slf4j
public class FileController {

    public static final String ROOT = "/Users/byene/Downloads";

    @PostMapping( "/upload" )
    public ResultVO uploadfile(@RequestParam( "file" ) MultipartFile file )
    {
        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件名称] - [{}]", file.getOriginalFilename());
        log.info("[文件大小] - [{}]", file.getSize());
        ResultVO resultVO = new ResultVO();
        if( file.isEmpty() )
        {
            resultVO.setCode( FileStatusEnum.FILE_EMPTY.getCode() );
            resultVO.setMsg( FileStatusEnum.FILE_EMPTY.getMessage() );
            return resultVO;
        }
        String FileName   = file.getOriginalFilename();  // 文件名
        String SuffixName = FileName.substring( FileName.lastIndexOf(".") );  // 后缀名
        String FilePath   = "/Users/byene/Downloads";
        String contentType = file.getContentType();
        File newFile = new File( FilePath, "byene" + SuffixName );
        try{
            file.transferTo( newFile );
        }catch ( IOException e)
        {
            e.printStackTrace();
        }
        resultVO.setCode( FileStatusEnum.FILE_SUCCESS.getCode() );
        resultVO.setMsg( FileStatusEnum.FILE_SUCCESS.getMessage() );
        return resultVO;
    }


    @PostMapping( "/get" )
    public ResultVO getfile(@RequestParam( "filename" ) String filename ) throws IOException
    {
        ResultVO resultVO = new ResultVO();
        String FilePath = "/Users/byene/Downloads";
        String FileName = filename + ".jpg";
        File newFile = new File( FilePath,  FileName);
        FileInputStream inputStream = new FileInputStream( newFile );
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        resultVO.setCode( FileStatusEnum.FILE_GET_SUCCESS.getCode() );
        resultVO.setMsg( FileStatusEnum.FILE_GET_SUCCESS.getMessage() );
        resultVO.setData( bytes );
        return resultVO;
    }
}

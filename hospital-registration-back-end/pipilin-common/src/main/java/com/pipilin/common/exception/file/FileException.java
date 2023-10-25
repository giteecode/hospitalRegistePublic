package com.pipilin.common.exception.file;

import com.pipilin.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author  2891517520@qq.com
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}

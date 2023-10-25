package com.pipilin.system.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.pipilin.common.core.domain.entity.SysUser;
import com.pipilin.common.exception.base.BaseException;
import com.pipilin.common.utils.DateUtils;
import com.pipilin.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pipilin.system.mapper.PPayMapper;
import com.pipilin.system.domain.PPay;
import com.pipilin.system.service.IPPayService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 充值记录信息Service业务层处理
 * 
 * @author pipilin
 * @date 2023-03-03
 */
@Service
public class PPayServiceImpl implements IPPayService 
{
    @Autowired
    private PPayMapper pPayMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询充值记录信息
     * 
     * @param id 充值记录信息主键
     * @return 充值记录信息
     */
    @Override
    public PPay selectPPayById(Long id)
    {
        return pPayMapper.selectPPayById(id);
    }

    /**
     * 查询充值记录信息列表
     * 
     * @param pPay 充值记录信息
     * @return 充值记录信息
     */
    @Override
    public List<PPay> selectPPayList(PPay pPay)
    {
        return pPayMapper.selectPPayList(pPay);
    }

    /**
     * 新增充值记录信息
     * 
     * @param pPay 充值记录信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPPay(PPay pPay)
    {
        SysUser sysUser = sysUserMapper.selectUserById(pPay.getUserId());
        if(ObjectUtil.isNotNull(sysUser)){
            SysUser sysUser1 = new SysUser();
            sysUser1.setUserId(sysUser.getUserId());
            sysUser1.setBalance(sysUser.getBalance().add(pPay.getPrice()));
            sysUserMapper.updateUser(sysUser1);
        }else {
            throw new BaseException("用户信息不存在");
        }
        pPay.setCreateTime(DateUtils.getNowDate());
        return pPayMapper.insertPPay(pPay);
    }

    /**
     * 修改充值记录信息
     * 
     * @param pPay 充值记录信息
     * @return 结果
     */
    @Override
    public int updatePPay(PPay pPay)
    {
        return pPayMapper.updatePPay(pPay);
    }

    /**
     * 批量删除充值记录信息
     * 
     * @param ids 需要删除的充值记录信息主键
     * @return 结果
     */
    @Override
    public int deletePPayByIds(Long[] ids)
    {
        return pPayMapper.deletePPayByIds(ids);
    }

    /**
     * 删除充值记录信息信息
     * 
     * @param id 充值记录信息主键
     * @return 结果
     */
    @Override
    public int deletePPayById(Long id)
    {
        return pPayMapper.deletePPayById(id);
    }
}

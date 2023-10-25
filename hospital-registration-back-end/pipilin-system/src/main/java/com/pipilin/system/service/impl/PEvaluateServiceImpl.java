package com.pipilin.system.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.pipilin.common.utils.DateUtils;
import com.pipilin.system.domain.PRegistration;
import com.pipilin.system.mapper.PRegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pipilin.system.mapper.PEvaluateMapper;
import com.pipilin.system.domain.PEvaluate;
import com.pipilin.system.service.IPEvaluateService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评价信息Service业务层处理
 * 
 * @author pipilin
 * @date 2023-03-03
 */
@Service
public class PEvaluateServiceImpl implements IPEvaluateService 
{
    @Autowired
    private PEvaluateMapper pEvaluateMapper;

    @Autowired
    private PRegistrationMapper pRegistrationMapper;

    /**
     * 查询评价信息
     * 
     * @param id 评价信息主键
     * @return 评价信息
     */
    @Override
    public PEvaluate selectPEvaluateById(Long id)
    {
        return pEvaluateMapper.selectPEvaluateById(id);
    }

    /**
     * 查询评价信息列表
     * 
     * @param pEvaluate 评价信息
     * @return 评价信息
     */
    @Override
    public List<PEvaluate> selectPEvaluateList(PEvaluate pEvaluate)
    {
        return pEvaluateMapper.selectPEvaluateList(pEvaluate);
    }

    /**
     * 新增评价信息
     * 
     * @param pEvaluate 评价信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPEvaluate(PEvaluate pEvaluate)
    {
        PRegistration pRegistration = pRegistrationMapper.selectPRegistrationById(pEvaluate.getRegistrationId());
        if(ObjectUtil.isNotNull(pRegistration)){
            PRegistration pRegistration1 = new PRegistration();
            pRegistration1.setId(pRegistration.getId());
            pRegistration1.setStatus("3");
            pRegistrationMapper.updatePRegistration(pRegistration1);
        }
        pEvaluate.setCreateTime(DateUtils.getNowDate());
        return pEvaluateMapper.insertPEvaluate(pEvaluate);
    }

    /**
     * 修改评价信息
     * 
     * @param pEvaluate 评价信息
     * @return 结果
     */
    @Override
    public int updatePEvaluate(PEvaluate pEvaluate)
    {
        return pEvaluateMapper.updatePEvaluate(pEvaluate);
    }

    /**
     * 批量删除评价信息
     * 
     * @param ids 需要删除的评价信息主键
     * @return 结果
     */
    @Override
    public int deletePEvaluateByIds(Long[] ids)
    {
        return pEvaluateMapper.deletePEvaluateByIds(ids);
    }

    /**
     * 删除评价信息信息
     * 
     * @param id 评价信息主键
     * @return 结果
     */
    @Override
    public int deletePEvaluateById(Long id)
    {
        return pEvaluateMapper.deletePEvaluateById(id);
    }
}

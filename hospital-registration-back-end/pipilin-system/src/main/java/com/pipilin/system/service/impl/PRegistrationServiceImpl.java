package com.pipilin.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.pipilin.common.core.domain.entity.SysUser;
import com.pipilin.common.exception.base.BaseException;
import com.pipilin.common.utils.DateUtils;
import com.pipilin.system.domain.PScheduling;
import com.pipilin.system.mapper.PSchedulingMapper;
import com.pipilin.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pipilin.system.mapper.PRegistrationMapper;
import com.pipilin.system.domain.PRegistration;
import com.pipilin.system.service.IPRegistrationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预约挂号记录Service业务层处理
 * 
 * @author pipilin
 * @date 2023-02-27
 */
@Service
public class PRegistrationServiceImpl implements IPRegistrationService 
{
    @Autowired
    private PRegistrationMapper pRegistrationMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PSchedulingMapper pSchedulingMapper;

    private static final int maxNum = 5;

    /**
     * 查询预约挂号记录
     * 
     * @param id 预约挂号记录主键
     * @return 预约挂号记录
     */
    @Override
    public PRegistration selectPRegistrationById(Long id)
    {
        return pRegistrationMapper.selectPRegistrationById(id);
    }

    /**
     * 查询预约挂号记录列表
     * 
     * @param pRegistration 预约挂号记录
     * @return 预约挂号记录
     */
    @Override
    public List<PRegistration> selectPRegistrationList(PRegistration pRegistration)
    {
        return pRegistrationMapper.selectPRegistrationList(pRegistration);
    }

    /**
     * 新增预约挂号记录
     * 
     * @param pRegistration 预约挂号记录
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPRegistration(PRegistration pRegistration)
    {
        // 判断预约时间
        PScheduling pScheduling = new PScheduling();
        pScheduling.setSchedulingId(pRegistration.getDoctorId());
        List<PScheduling> pSchedulings = pSchedulingMapper.selectPSchedulingList(pScheduling);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (PScheduling scheduling : pSchedulings) {
            System.out.println(scheduling.getSchedulingStartTime());
            System.out.println(scheduling.getSchedulingEndTime());

            System.out.println(scheduling.getSchedulingStartTime().before(pRegistration.getRegistrationTime()));
            System.out.println(scheduling.getSchedulingEndTime().before(pRegistration.getRegistrationTime()));
            if(pRegistration.getRegistrationTime().before(scheduling.getSchedulingStartTime())){
                throw new BaseException("预约时间段：" + sdf.format(scheduling.getSchedulingStartTime()) + "-" + sdf.format(scheduling.getSchedulingEndTime()) );
            }
            if(pRegistration.getRegistrationTime().after(scheduling.getSchedulingEndTime())){
                throw new BaseException("预约时间段：" + sdf.format(scheduling.getSchedulingStartTime()) + "-" + sdf.format(scheduling.getSchedulingEndTime()) );
            }
            if(scheduling.getSchedulingStartTime().before(pRegistration.getRegistrationTime()) && scheduling.getSchedulingEndTime().before(pRegistration.getRegistrationTime())){
                throw new BaseException("预约时间段：" + sdf.format(scheduling.getSchedulingStartTime()) + "-" + sdf.format(scheduling.getSchedulingEndTime()) );
            }
        }
//        pSchedulings.forEach(item -> {
//            if(!item.getSchedulingStartTime().before(pRegistration.getRegistrationTime()) && !item.getSchedulingEndTime().after(pRegistration.getRegistrationTime())){
//                throw new BaseException("预约时间段：" + item.getSchedulingStartTime() + "-" + item.getSchedulingEndTime() );
//            }
//        });
        PRegistration pRegistration1 = new PRegistration();
        pRegistration1.setUserId(pRegistration.getUserId());
        pRegistration1.setStatus("0");
        List<PRegistration> pRegistrations = pRegistrationMapper.selectPRegistrationList(pRegistration1);
        if(pRegistrations.size() > 0){
            throw new BaseException("已有待就诊订单，请完成后再预约!");
        }
        //扣减就诊人费用
        SysUser sysUser = sysUserMapper.selectUserById(pRegistration.getUserId());
        if(sysUser.getCancelNumber() >= maxNum){
            throw new BaseException("您取消次数过多，已将您加入黑名单");
        }
        if(ObjectUtil.isNotNull(sysUser)){
            if(sysUser.getBalance().compareTo(pRegistration.getRegistrationPrice()) < 0){
                throw new BaseException("余额不足，请充值!");
            }
            SysUser sysUser1 = new SysUser();
            sysUser1.setUserId(sysUser.getUserId());
            sysUser1.setBalance(sysUser.getBalance().subtract(pRegistration.getRegistrationPrice()));
            sysUserMapper.updateUser(sysUser1);
        }
        //扣减医生预约数量
        SysUser sysUserDoctor = sysUserMapper.selectUserById(pRegistration.getDoctorId());
        if(ObjectUtil.isNotNull(sysUserDoctor)){
            if(sysUserDoctor.getResidualNumber() == 0){
                throw new BaseException("预约已满！");
            }
            SysUser sysUser2 = new SysUser();
            sysUser2.setUserId(sysUserDoctor.getUserId());
            sysUser2.setResidualNumber(sysUserDoctor.getResidualNumber() - 1);
            sysUserMapper.updateUser(sysUser2);
        }
        pRegistration.setOrderNo(IdUtil.getSnowflake().nextIdStr());
        pRegistration.setCreateTime(DateUtils.getNowDate());
        return pRegistrationMapper.insertPRegistration(pRegistration);
    }

    /**
     * 修改预约挂号记录
     * 
     * @param pRegistration 预约挂号记录
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePRegistration(PRegistration pRegistration)
    {
        PRegistration pRegistration1 = pRegistrationMapper.selectPRegistrationById(pRegistration.getId());
        if("1".equals(pRegistration.getStatus())){
            //增加就诊人费用以及取消次数
            SysUser sysUser = sysUserMapper.selectUserById(pRegistration1.getUserId());
            if(ObjectUtil.isNotNull(sysUser)){
                SysUser sysUser1 = new SysUser();
                //int i = sysUser.getCancelNumber() + 1;
                sysUser1.setUserId(sysUser.getUserId());
                sysUser1.setBalance(sysUser.getBalance().add(pRegistration1.getRegistrationPrice()));
                sysUser1.setCancelNumber(sysUser.getCancelNumber() + 1);
                if(sysUser1.getCancelNumber() > maxNum){
                    throw new BaseException("您取消次数过多，已将您加入黑名单");
                }
                if(sysUser1.getCancelNumber()  == maxNum){
                    sysUser1.setStatus("1");
                }
                sysUserMapper.updateUser(sysUser1);
            }
            //增加医生预约数量
            SysUser sysUserDoctor = sysUserMapper.selectUserById(pRegistration1.getDoctorId());
            if(ObjectUtil.isNotNull(sysUserDoctor)){
                SysUser sysUser2 = new SysUser();
                sysUser2.setUserId(sysUserDoctor.getUserId());
                sysUser2.setResidualNumber(sysUserDoctor.getResidualNumber() + 1);
                sysUserMapper.updateUser(sysUser2);
            }
            pRegistration.setCancelTime(DateUtils.getNowDate());
        }
        return pRegistrationMapper.updatePRegistration(pRegistration);
    }

    /**
     * 批量删除预约挂号记录
     * 
     * @param ids 需要删除的预约挂号记录主键
     * @return 结果
     */
    @Override
    public int deletePRegistrationByIds(Long[] ids)
    {
        return pRegistrationMapper.deletePRegistrationByIds(ids);
    }

    /**
     * 删除预约挂号记录信息
     * 
     * @param id 预约挂号记录主键
     * @return 结果
     */
    @Override
    public int deletePRegistrationById(Long id)
    {
        return pRegistrationMapper.deletePRegistrationById(id);
    }
}

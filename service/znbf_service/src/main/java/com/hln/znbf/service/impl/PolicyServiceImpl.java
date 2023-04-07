package com.hln.znbf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import com.hln.znbf.constants.DsrnhConstants;
import com.hln.znbf.entity.Dsrnh;
import com.hln.znbf.entity.Policy;
import com.hln.znbf.mapper.PolicyMapper;
import com.hln.znbf.service.DsrnhService;
import com.hln.znbf.service.PolicyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 浙农帮扶——政策 服务实现类
 * </p>
 *
 * @author wcg
 * @since 2022-12-01
 */
@Service
public class PolicyServiceImpl extends ServiceImpl<PolicyMapper, Policy> implements PolicyService {

    @Autowired
    private DsrnhService dsrnhService;

    @Override
    public Map<String, Object> getMyPolicyList(HttpServletRequest request, String title,String type,String category) {
        //获取登录用户id
        String userId = JwtUtils.getIdByJwtToken(request);
        if(StringUtils.isEmpty(userId)){
            throw new PracticeException(ResultCode.ERROR,"参数错误！");
        }
        Dsrnh dsrnh = dsrnhService.getById(userId);
        if(dsrnh==null){
            throw new PracticeException(ResultCode.ERROR,"用户不存在！");
        }
        String code =   dsrnh.getAreacode().substring(0,6);
        QueryWrapper<Policy> wrapper = new QueryWrapper<>();
        wrapper.eq("area_code",code);
        if(StringUtils.isNotEmpty(title)){
            wrapper.like("title",title);
        }
        if(StringUtils.isNotEmpty(type)){
            wrapper.like("type",type);
        }
        if(StringUtils.isNotEmpty(category)){
            wrapper.like("title",category);
        }
        List<Policy> policies = this.baseMapper.selectList(wrapper);
        Map<String,Object> map = new HashMap<>();
        List<Policy> proList = policies.stream().filter(e -> e.getLevle().equals("省级")).collect(Collectors.toList());
        List<Policy> cityList = policies.stream().filter(e -> e.getLevle().equals("市级")).collect(Collectors.toList());
        List<Policy> countryList = policies.stream().filter(e -> e.getLevle().equals("县级")).collect(Collectors.toList());
        map.put("proList",proList);
        map.put("cityList",cityList);
        map.put("countryList",countryList);
        return map;
    }

    @Override
    public Policy getMyPolicyById(String id) {
        Policy policy = this.getById(id);
        DsrnhConstants.initialize();
        if(policy.getDsrnhType().contains(",")){
            String[] nhType = policy.getDsrnhType().split(",");
           List<String> type=new ArrayList<>();
           for (String t:nhType){
               type.add(DsrnhConstants.TYPE.get(t));
           }
           policy.setDsrnhType(org.apache.commons.lang.StringUtils.join(type,","));
        }else {
            policy.setDsrnhType(DsrnhConstants.TYPE.get(policy.getDsrnhType()));

        }
        if(policy.getWhcd().contains(",")){
            String[] nhWhcd = policy.getDsrnhType().split(",");
            List<String> whcd=new ArrayList<>();
            for (String t:nhWhcd){
                whcd.add(DsrnhConstants.WHCD.get(t));
            }
        }else {
            policy.setWhcd(DsrnhConstants.WHCD.get(policy.getWhcd()));
        }
        if(policy.getJkzk().contains(",")){
            String[] nhjkzk = policy.getDsrnhType().split(",");
            List<String> jkzk=new ArrayList<>();
            for (String t:nhjkzk){
                jkzk.add(DsrnhConstants.JKZK.get(t));
            }
            policy.setJkzk(org.apache.commons.lang.StringUtils.join(jkzk,","));
        }else {
            policy.setJkzk(DsrnhConstants.JKZK.get(policy.getJkzk()));
        }
        return policy;
    }

    @Override
    public Map<String, Object> getPolicyList(String areaCode, String unit, String title,Integer pageNum,Integer pageSize) {
        if(pageNum==null || pageSize==null){
            throw new PracticeException(ResultCode.ERROR,"参数缺失！");
        }
        QueryWrapper<Policy> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(areaCode)) {
            wrapper.likeRight("area_code",areaCode);
        }
        if(StringUtils.isNotEmpty(unit)) {
            wrapper.like("unit",unit);
        }
        if(StringUtils.isNotEmpty(title)) {
            wrapper.like("title",title);
        }
        IPage<Policy> iPage = new Page<>(pageNum,pageSize);
        this.page(iPage,wrapper);
        List<Policy> list = iPage.getRecords();
        Map<String,Object> map = new HashMap<>();

        map.put("total", iPage.getTotal());
        map.put("item", iPage.getRecords());
        return map;
    }
}

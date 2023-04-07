package com.hln.znbf.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hln.commonuntils.JwtUtils;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import com.hln.znbf.constants.DsrnhConstants;
import com.hln.znbf.entity.Apply;
import com.hln.znbf.entity.Dsrnh;
import com.hln.znbf.entity.Work;
import com.hln.znbf.mapper.ApplyMapper;
import com.hln.znbf.mapper.WorkMapper;
import com.hln.znbf.service.DsrnhService;
import com.hln.znbf.service.WorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hln.znbf.vo.WorkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 浙农帮扶——岗位详情 服务实现类
 * </p>
 *
 * @author wcg
 * @since 2022-12-02
 */
@Service
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {

    @Autowired
    private DsrnhService dsrnhService;
    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public Map<String, Object> getMyWorkList(HttpServletRequest request, String name, Integer pageNum, Integer pageSize) {
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
            QueryWrapper<Work> wrapper = new QueryWrapper<>();
            wrapper.eq("area_code",code)
                    .like("name",name)
                    .gt("end_time", DateUtil.parse(DateUtil.today()).toJdkDate());
            IPage<Work> iPage = new Page<>(pageNum,pageSize);
                this.page(iPage,wrapper);
            List<Work> records = iPage.getRecords();
            DsrnhConstants.initialize();
            records.stream().forEach(item -> {
                item.setType(DsrnhConstants.GWLX.get(item.getType()));
            });
                Map<String,Object> map = new HashMap<>();
                map.put("total", iPage.getTotal());
                map.put("item", records);
            return map;
    }

    @Override
    public void applyWork(Integer workId, HttpServletRequest request) {
        //获取登录用户id
        String userId = JwtUtils.getIdByJwtToken(request);
        if(StringUtils.isEmpty(userId)){
            throw new PracticeException(ResultCode.ERROR,"参数错误！");
        }
        Dsrnh dsrnh = dsrnhService.getById(userId);
        if(dsrnh==null){
            throw new PracticeException(ResultCode.ERROR,"用户不存在！");
        }
        Work work = this.getById(workId);
        if(work==null){
            throw new PracticeException(ResultCode.ERROR,"岗位不存在！");
        }
        Apply apply = new Apply();
        apply.setUserId(dsrnh.getId())
             .setWorkId(workId)
             .setStatus("1");
        applyMapper.insert(apply);
      /*  //岗位数+1
        work.setNum(work.getNum()+1);
        this.updateById(work);*/
    }

    @Override
    public Map<String, Object> getWorkList(String areaCode, String name, Integer pageNum, Integer pageSize) {
        QueryWrapper<Work> wrapper = new QueryWrapper<>();
        wrapper.like("area_code",areaCode)
                .like("name",name);
        IPage<Work> iPage = new Page<>(pageNum,pageSize);
        this.page(iPage,wrapper);
        List<Work> records = iPage.getRecords();
        DsrnhConstants.initialize();
        records.stream().forEach(item -> {
            item.setType(DsrnhConstants.GWLX.get(item.getType()));
        });
        Map<String,Object> map = new HashMap<>();
        map.put("total", iPage.getTotal());
        map.put("item", records);
        return map;
    }


    @Override
    public Map<String, Object> getMyApplyList(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        String userId = JwtUtils.getIdByJwtToken(request);
        //获取total;
        QueryWrapper<Apply> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        Integer count = applyMapper.selectCount(wrapper);
        //获取条数分页
        Integer idnex = (pageNum-1)*pageSize;
        List<Work> works = this.baseMapper.getWorkApplyList(userId,idnex,pageSize);
        DsrnhConstants.initialize();
        works.stream().forEach(item -> {
            item.setType(DsrnhConstants.GWLX.get(item.getType()));
        });
        Map<String,Object> map = new HashMap<>();
        map.put("total", count);
        map.put("item", works);
        return map;
    }

    @Override
    public Map<String, Object> getApplyList(String workName, String name, Integer pageNum, Integer pageSize, String status) {
        Integer count = applyMapper.getApplyCount(workName,name,status);
        //获取条数分页
        Integer index = (pageNum-1)*pageSize;
        List<WorkVo> works = applyMapper.getApplyList(workName,name,status,index,pageSize);

        Map<String,Object> map = new HashMap<>();
        map.put("total", count);
        map.put("item", works);
        return map;
    }

    @Override
    public void updateWork(Work work) {
        if(work.getId()==null){
            throw new PracticeException(ResultCode.ERROR,"岗位不存在！");
        }
        this.updateById(work);
    }

    @Override
    public Work getWorkInfo(Integer workId) {
        Work work = this.getById(workId);
        DsrnhConstants.initialize();
        work.setType(DsrnhConstants.GWLX.get(work.getType()));
        return work;
    }
}

package com.hln.association.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hln.association.entity.ActCertificate;
import com.hln.association.entity.excel.CretData;
import com.hln.association.mapper.ActCertificateMapper;
import com.hln.association.service.ActCertificateService;
import com.hln.commonuntils.ResultCode;
import com.hln.servicebase.exceptionhandler.PracticeException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;

public class CretExcelListener extends AnalysisEventListener<CretData> {


    /**
     * 创建有参数构造，传递subjectService用于操作数据库
     * 因为SubjectExcelListener不能交给spring去管理，所以需要自己new，不能注入对象
     * 此处一定要是public，不然永远获取不到对象
     */

    public Map<String,Object> item;
    public ActCertificateMapper actCertificateMapper;

    //无参构造
    public CretExcelListener() {

    }

    /**
     * 构造方法赋值
     * @param actCertificateMapper
     * @param item
     */
    public CretExcelListener(ActCertificateMapper actCertificateMapper, Map<String,Object> item) {
        this.actCertificateMapper = actCertificateMapper;

        this.item=item;
    }


    @Override
    public void invoke(CretData cretData, AnalysisContext analysisContext) {
        if (StringUtils.isEmpty(cretData)){
            throw new PracticeException(ResultCode.ERROR,"文件数据为空");
        }
        //一行一行去读取excel内容
        //判断是否可以存入;
        boolean flag = isEmptyData(cretData);
        if(flag) {
            List<CretData> list = (List<CretData>) item.get("failList");
            Integer failTotal = (Integer) item.get("failTotal");
            item.put("failTotal",failTotal+1);
            list.add(cretData);
        }else {
            ActCertificate cret = new ActCertificate();
            BeanUtils.copyProperties(cretData,cret);
            //判断一级分类是否重复
            actCertificateMapper.insert(cret);
            Integer successTotal = (Integer) item.get("successTotal");
            successTotal+=1;
            item.put("successTotal",successTotal+1);
        }

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private boolean isEmptyData(CretData cretData){

        if(StringUtils.isEmpty(cretData.getName())){
            cretData.setReason("姓名为空！");
            return true;
        }
        if(StringUtils.isEmpty(cretData.getCode())){
            cretData.setReason("证书号为空！");
            return true;
        }
        if(StringUtils.isEmpty(cretData.getTitle())){
            cretData.setReason("项目名称为空！");
            return true;
        }
        if(StringUtils.isEmpty(cretData.getUnit())){
            cretData.setReason("单位为空！");
            return true;
        }
        //去重
        QueryWrapper<ActCertificate> wrapper = new QueryWrapper<>();
        wrapper.eq("code",cretData.getCode());
        ActCertificate cret = actCertificateMapper.selectOne(wrapper);
        if(cret!=null){
            cretData.setReason("证书号重复！");
            return true;
        }
        return false;
    }
}

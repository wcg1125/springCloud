package com.hln.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.StorageClass;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hln.oss.service.OssService;
import com.hln.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {




    @Override
    public String uploadActFile(MultipartFile file,String source) {
        /**
         * 基于工具类获取对应的参数名称
         */
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


        if(StringUtils.isEmpty(source)){
            source="focus";
        }

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/jpg");

            InputStream inputStream = file.getInputStream();//文件的流

            //调用oss方法实现上传
            //第一个参数：bucket名称阿里云服务器获取
            //第二个参数， 文件上传后的路径和名称 /aa/bb/c.jpg
            String fileName = file.getOriginalFilename();
            //重新定义名称防止名称重复覆盖
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName=uuid+fileName;

            //对上传后的文件按照日期进行分类(pom工具类)
           // String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName=source+"/"+fileName;

            //第三个参数  文件流
            ossClient.putObject(bucketName, fileName, inputStream,objectMetadata);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回的路径手动需要拼接出来
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return  url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 设置存储类型与访问权限
     *
     * @param request 文件请求
     */
    private static PutObjectRequest jurisdiction(PutObjectRequest request) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        metadata.setContentType("image/jpg");
        request.setMetadata(metadata);
        return request;
    }


}

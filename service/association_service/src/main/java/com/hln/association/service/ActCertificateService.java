package com.hln.association.service;

import com.hln.association.entity.ActCertificate;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 职协-证书管理 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-10-25
 */
public interface ActCertificateService extends IService<ActCertificate> {

    Map<String, Object> getCretList(Integer page, Integer size, String code, String title);

    Map<String, Object> importExcel(MultipartFile excelFile);

    ActCertificate selectCret(String code);
}

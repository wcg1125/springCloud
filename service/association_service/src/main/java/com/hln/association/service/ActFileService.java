package com.hln.association.service;

import com.hln.association.entity.ActCertificate;
import com.hln.association.entity.ActFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcg
 * @since 2022-11-07
 */
public interface ActFileService extends IService<ActFile> {

    void saveActFile(List<ActFile> actFiles, String type);

    List<ActFile> getImgList(String type);

}

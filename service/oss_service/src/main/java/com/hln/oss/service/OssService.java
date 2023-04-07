package com.hln.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    String uploadActFile(MultipartFile file,String source);
}

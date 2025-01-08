package cn.lanqiao.library_final_project.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import cn.lanqiao.library_final_project.result.Result;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@RestController
@Slf4j
public class CaptchaController {
    @Value("${image.path}")
    private String imagePath;
    private final ResourceLoader resourceLoader;
    public CaptchaController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping("/getcaptcha")
    public Result<HashMap<String, String>> getCaptcha1(HttpSession session) {
        // 1.生成验证码到本地
        // 定义图形验证码的长和宽 (这个验证码的大小需要和自己前端的验证码的大小匹配)
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ".png";

        try {
            // 获取类路径下的文件资源
            Resource resource = resourceLoader.getResource(imagePath);
            File file = resource.getFile();
            // 将验证码图片写入到文件
            File captchaFile = new File(file, fileName);
            FileOutputStream fos = new FileOutputStream(captchaFile);
            lineCaptcha.write(fos);
            fos.close();
            // 将验证码信息存储到Session中
            session.setAttribute("captchaCode", lineCaptcha.getCode());
            log.info("验证码：" + lineCaptcha.getCode());
            HashMap<String, String> result = new HashMap<>();
            result.put("codeurl", "/image/" + uuid + ".png");
            result.put("codekey", uuid);
            return Result.success(result);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("验证码生成失败");
        }
    }
//    验证验证码

}

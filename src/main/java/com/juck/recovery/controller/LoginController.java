package com.juck.recovery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.juck.recovery.annotation.PassLogin;
import com.juck.recovery.entity.LoginInfo;
import com.juck.recovery.entity.R;
import com.juck.recovery.utils.ImageVerificationUtil;
import com.juck.recovery.utils.JWTUtil;
import com.juck.recovery.utils.MemoryCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    @PassLogin
    public JSONObject login(@RequestBody LoginInfo info, HttpServletRequest request) {
        JSONObject obj = new JSONObject();

        String code = (String) request.getSession().getAttribute("code");
        if (!StringUtils.equalsIgnoreCase(code, info.getVerificationCode())) {
            obj.put("verification", false);
            return obj;
        }

        obj.put("token", JWTUtil.genToken(info.getUsername(), info.getPassword()));
        obj.put("username", info.getUsername());

        MemoryCache.set(info.getUsername() + "#TOKEN", JWTUtil.genToken(info.getUsername(), info.getPassword()));
        MemoryCache.set(info.getUsername() + "#PWD", info.getPassword());
        return obj;
    }

    @GetMapping("/verification")
    @PassLogin
    public R<String> genVerification(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage image = ImageVerificationUtil.getImage();
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            ImageVerificationUtil.output(image, response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("Generate verification code failed due to {}", e.getMessage(), e);
        }

        HttpSession session = request.getSession();
        session.setAttribute("code", ImageVerificationUtil.getText());

        return new R<String>().success("1", "成功");
    }

    @GetMapping("/test")
    public JSONObject test(String test) {
        JSONObject json = new JSONObject();

        LOGGER.info("request test {}", test);

        json.put("key", "test");
        json.put("value", test);

        return json;
    }
}

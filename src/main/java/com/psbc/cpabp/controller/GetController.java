package com.psbc.cpabp.controller;

import com.psbc.cpabp.entity.TbUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Slf4j  //日志注解
@RestController
public class GetController {

    private String baseDir = "D:\\MyJava\\WorkSpace\\cpabp\\src\\main\\resources\\static\\img\\";

    /*GET测试1*/
    @RequestMapping("/get_1")
    @ResponseBody
    public String get_1(String name, String password) {
        log.info("get_1...");
        String params;
        params = "name=" + name + ",password=" + password;
        log.info(params);
        return params;
    }

    /*GET测试2*/
    @RequestMapping("/get_2")
    @ResponseBody
    public String get_2(HttpServletRequest request) {
        log.info("get_2...");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        log.info(name + "," + password);
        return name + "," + password;
    }

    /*GET测试3*/
    @RequestMapping("/get_3")
    @ResponseBody
    public String get_3(TbUser user) {
        log.info("get_3...");
        String name = user.getName();
        String password = user.getPassword();
        log.info(name + "," + password);
        return name + "," + password;
    }

    /*GET测试4*/
    @RequestMapping("/get_4/{a}/{b}")
    @ResponseBody
    public String get_4(@PathVariable("a") String a, @PathVariable("b") String b) {
        log.info("get_4...");
        log.info(a + "," + b);
        return a + "," + b;
    }

    /*GET测试5*/
    @RequestMapping("/get_5")
    @ResponseBody
    public String get_5(@RequestParam("name") String name, @RequestParam("password") String password) {
        log.info("get_5...");
        log.info(name + "," + password);
        return name + "," + password;
    }

    /*GET测试6*/
    @RequestMapping("/get_6")
    @ResponseBody
    public Map<String, String> get_6(@RequestParam Map<String, String> map) {
        log.info("get_6...");
        map.forEach((k, v) -> {
            log.info(k + "-->" + v);
        });
        return map;
    }

    /**
     * 返回值为二进制
     * 其实这里可以使用 Files.readAllBytes()这个方法，这样就简单了。这里我就不改了，我习惯了使用这种
     * 循环读取的方式，不过确实有点繁琐了。
     */
    @GetMapping("/get_7/{name}")
    public void getFile(@PathVariable("name") String name, HttpServletResponse response) {
        try (OutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            try (InputStream in = new BufferedInputStream(new FileInputStream(new File(baseDir, name)))) {
                int len = 0;
                byte[] data = new byte[4 * 1024];
                while ((len = in.read(data)) != -1) {
                    out.write(data, 0, len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Files.readAllBytes()方法读取文件
     * @param name
     * @param response
     */
    @GetMapping("/get_8/{name}")
    public void get_8(@PathVariable("name") String name, HttpServletResponse response) {
        try (OutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            byte[] contets = Files.readAllBytes(Paths.get(baseDir + name));
            out.write(contets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

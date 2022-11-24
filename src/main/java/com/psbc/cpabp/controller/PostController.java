package com.psbc.cpabp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.events.Comment;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j  //日志注解
@RestController
public class PostController {

    private String baseDir = "D:\\MyJava\\WorkSpace\\cpabp\\src\\main\\resources\\static\\img\\";

    /**
     * POST方式传递参数1
     * @return
     * */
    @PostMapping("/post_1")
    public String post_1(@RequestParam("name") String name,
                         @RequestParam("password") String password) {
        log.info("post_1...");
        log.info(name + "," + password);
        return name + "," + password;
    }

    /**
     * POST方式传递参数2
     * @param map
     * @return
     */
    @PostMapping("/post_2")
    public Map<String, String> post02(@RequestParam Map<String, String> map) {
        map.forEach((k, v) -> {
            System.out.println(k + " --> " + v);
        });
        return map;
    }

    /**
     * POST方式传递参数3
     * @param comment
     * @return
     */
    @PostMapping("/post_3")
    public Comment post_3(@RequestBody Comment comment) {
        log.info(comment.toString());
        return comment;
    }

    /**
     * POST测试4
     * 传递对象数组
     * @param comments
     * @return
     */
    @PostMapping("/post_4")
    public Comment[] post_4(@RequestBody Comment[] comments) {
        return comments;
    }

    /**
     * POST测试5
     * json数组映射List
     * @param commentList
     * @return
     */
    @PostMapping("/post_5")
    public List<Comment> post_5(@RequestBody List<Comment> commentList) {
        return commentList;
    }

    /**
     * POST测试6
     * 上传文件
     * 传递二进制数据
     * */
    @PostMapping("/post_6")
    public String post_6(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(new File(baseDir, fileName)); // 对于 SpringBoot 中使用路径还是懵逼！
                return "success";
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Fail";
    }

    /**
     * POST测试7
     * 表单数据，含文本和二进制
     * */
    @PostMapping("/post_7")
    public String post_7(@RequestParam("name") String name,
                             @RequestParam("file") MultipartFile file) {

        log.info("name: " + name);
        log.info("fileName: " + file != null ? file.getOriginalFilename() : "null");

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(new File(baseDir, fileName));
                return "success";
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Fail";
    }

    /**
     * POST以二进制形式传递文件，通常的web表单是做不到的，但是ajax2.0以后是支持的，我们来尝试一下。
     * 注意它和 Multipart的区别，Multipart实际上不只包含文件本身的数据，还有文件的其它的信息，例如刚才获取的文件名。
     * 但是如果以二进制的形式传递，它就是完全的文件数据流，不包含任何其它信息，只有文件本身的二进制数据流。
     *
     * 使用这种形式，只能传输单个文件，无法传输多个文件，因为它只是文件本身的二进制数据，如果是多个的话，
     * 那么谁也别想从一个连续的二进制流中把图片切分出来了。
     * */
    @PostMapping("/post_8")
    public String post_8(@RequestBody byte[] fileData) {
        try {
            Files.write(Paths.get(baseDir, UUID.randomUUID().toString() + ".jpg"), fileData);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}

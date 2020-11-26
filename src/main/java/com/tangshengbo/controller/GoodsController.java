package com.tangshengbo.controller;


import com.tangshengbo.dao.DltaskMapper;
import com.tangshengbo.model.ApiResult;
import com.tangshengbo.model.Dltask;
import com.tangshengbo.model.Goods;
import com.tangshengbo.model.PageBean;
import com.tangshengbo.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tangshengbo on 2018/9/30
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private DltaskMapper dltaskMapper;

    /**
     * 分页查询
     *
     * @param goods    查询条件
     * @param pageNum 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @RequestMapping("/findByConPage")
    public ApiResult findByConPage(Goods goods,
                                   @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        logger.info("分页查询参数:pageNum:{},pageSize:{},Goods:{}", pageNum, pageSize, goods);
        PageBean pageBean = goodsService.findByPage(goods, pageNum, pageSize);
        return ApiResult.success(pageBean);
    }

    /**
     * 新增商品
     *
     * @param goods
     * @return
     */
    @RequestMapping("/create")
    public ApiResult create(@RequestBody Goods goods) {
        try {
            goodsService.create(goods);
            return ApiResult.success("创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.error("创建失败");
        }
    }

    /**
     * 更新数据成功
     *
     * @param goods
     * @return
     */
    @RequestMapping("/update")
    public ApiResult update(@RequestBody Goods goods) {
        try {
            goodsService.update(goods);
            return new ApiResult(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(false, "发生未知错误");
        }
    }

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public ApiResult delete(@RequestBody Long... ids) {
//        try {
//            goodsService.delete(ids);
//            return new ApiResult(true, "更新数据成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ApiResult(false, "发生未知错误");
//        }
        Dltask dltask = new Dltask();
        dltask.setA("a");
        dltask.setB("b");
        dltask.setC("c");

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable r = () -> dltaskMapper.deleteBy(dltask);
        for (int i = 0; i < 3; i++) {
            executorService.execute(r);
        }

        return new ApiResult(true, "更新数据成功");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ApiResult findById(@RequestParam(value = "id") Long id) {
        try {
            Goods goods = goodsService.findById(id);
            return ApiResult.success("操作成功", goods);
        } catch (Exception e) {
            return new ApiResult(false, "发生未知错误");
        }
    }

    /**
     * 文件上传
     *
     * @param picture
     * @return
     */
    @RequestMapping("/upload")
    public ApiResult upload(@RequestParam("picture") MultipartFile picture) {
        //获取文件在服务器的储存位置
        String path = request.getServletContext().getRealPath("/upload");
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            return new ApiResult(true, "/upload/" + fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new ApiResult(false, "上传失败");
        }
    }
}

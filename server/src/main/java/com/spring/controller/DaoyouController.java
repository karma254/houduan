
package com.spring.controller;

import com.base.controller.BaseController1;
import com.base.util.JsonResult;
import com.spring.entity.Daoyou;
import com.spring.service.DaoyouService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping
public class DaoyouController extends BaseController1 {
    @Resource
    private DaoyouService daoyouService;
    @RequestMapping("/daoyou_list")
    public JsonResult page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(required = false) String name, // 确保接收name参数
            @RequestParam(required = false) String status) {
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer limit,
//            String name,
//            String status) {
        try {
            Map<String, Object> result = daoyouService.pageQuery(page, limit, name, status);
            // 修改前：return renderSuccess(result);
            // 修改后：直接返回result，避免嵌套data层
            return new JsonResult(200, "查询成功", result);
        } catch (Exception e) {
            return renderError("查询失败: " + e.getMessage());
        }
    }
//
//    @RequestMapping("/daoyou_list")
//    public JsonResult page(
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer limit,
//            String name,
//            String status) {
//        try {
//            Map<String, Object> result = daoyouService.pageQuery(page, limit, name, status);
//            System.out.println("xxx");
//            return renderSuccess(result);
//        } catch (Exception e) {
//
//            return renderError("查询失败: " + e.getMessage());
//        }
//
//    }

    @RequestMapping("/daoyou_add")
    public JsonResult add(Daoyou daoyou) {
        try {
            int result = daoyouService.insert(daoyou);
            if (result > 0) {
                return renderSuccess("添加成功");
            } else {
                return renderError("添加失败");
            }
        } catch (Exception e) {
            return renderError("添加失败: " + e.getMessage());
        }
    }

    @RequestMapping("/daoyou_update")
    public JsonResult update(Daoyou daoyou) {
        try {
            int result = daoyouService.update(daoyou);
            if (result > 0) {
                return renderSuccess("修改成功");
            } else {
                return renderError("修改失败");
            }
        } catch (Exception e) {
            return renderError("修改失败: " + e.getMessage());
        }
    }
    @RequestMapping("/daoyou_delete")
    public JsonResult delete(@RequestParam("id") Integer id) {
        try {
            System.out.println("收到删除请求，ID: " + id);

            int result = daoyouService.delete(id);
            if (result > 0) {
                System.out.println("删除成功，ID: " + id);
                return new JsonResult(200, "删除成功", null);
            } else {
                System.out.println("删除失败，记录不存在，ID: " + id);
                return new JsonResult(500, "删除失败，记录可能不存在", null);
            }
        } catch (Exception e) {
            System.out.println("删除异常，ID: " + id + ", 错误: " + e.getMessage());
            e.printStackTrace();
            return new JsonResult(500, "删除失败: " + e.getMessage(), null);
        }
    }
//    @RequestMapping(value = "/daoyou_delete", method = {RequestMethod.POST, RequestMethod.GET})
//    public JsonResult delete(@RequestParam("id") Integer id) {
//        try {
//            int result = daoyouService.delete(id);
//            if (result > 0) {
//                return new JsonResult(200, "删除成功", null);
//            } else {
//                return new JsonResult(500, "删除失败，记录可能不存在", null);
//            }
//        } catch (Exception e) {
//            return new JsonResult(500, "删除失败: " + e.getMessage(), null);
//        }
//    }
//    @RequestMapping("/daoyou_delete")
//    public JsonResult delete(Integer id) {
//        try {
//            int result = daoyouService.delete(id);
//            if (result > 0) {
//                return renderSuccess("删除成功");
//            } else {
//                return renderError("删除失败");
//            }
//        } catch (Exception e) {
//            return renderError("删除失败: " + e.getMessage());
//        }
//    }

    @RequestMapping("/daoyou_detail")
    public JsonResult detail(Integer id) {
        try {
            Daoyou daoyou = daoyouService.find(id);
            if (daoyou == null) {
                return renderError("导游不存在");
            }
            return renderSuccess(daoyou);
        } catch (Exception e) {
            return renderError("查询失败: " + e.getMessage());
        }
    }
}
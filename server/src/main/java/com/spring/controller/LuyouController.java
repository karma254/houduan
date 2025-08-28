package com.spring.controller;

import com.base.controller.BaseController1;
import com.base.util.JsonResult;
import com.spring.entity.Luyou;
import com.spring.service.LuyouService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("luyou")
public class LuyouController extends BaseController1 {
    @Resource
    private LuyouService luyouService;

    @RequestMapping("/page")
    public JsonResult page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            String name,
            String difficulty) {
        try {
            Map<String, Object> result = luyouService.pageQuery(page, limit, name, difficulty);
            return renderSuccess(result);
        } catch (Exception e) {
            return renderError("查询失败: " + e.getMessage());
        }
    }

    @RequestMapping("/add")
    public JsonResult add(Luyou luyou) {
        try {
            int result = luyouService.insert(luyou);
            if (result > 0) {
                return renderSuccess("添加成功");
            } else {
                return renderError("添加失败");
            }
        } catch (Exception e) {
            return renderError("添加失败: " + e.getMessage());
        }
    }

    @RequestMapping("/update")
    public JsonResult update(Luyou luyou) {
        try {
            int result = luyouService.update(luyou);
            if (result > 0) {
                return renderSuccess("修改成功");
            } else {
                return renderError("修改失败");
            }
        } catch (Exception e) {
            return renderError("修改失败: " + e.getMessage());
        }
    }

    @RequestMapping("/delete")
    public JsonResult delete(Integer id) {
        try {
            int result = luyouService.delete(id);
            if (result > 0) {
                return renderSuccess("删除成功");
            } else {
                return renderError("删除失败");
            }
        } catch (Exception e) {
            return renderError("删除失败: " + e.getMessage());
        }
    }

    @RequestMapping("/detail")
    public JsonResult detail(Integer id) {
        try {
            Luyou luyou = luyouService.find(id);
            if (luyou == null) {
                return renderError("路线不存在");
            }
            return renderSuccess(luyou);
        } catch (Exception e) {
            return renderError("查询失败: " + e.getMessage());
        }
    }
}
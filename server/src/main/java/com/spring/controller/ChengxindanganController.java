package com.spring.controller;

import com.spring.dao.ChengxindanganMapper;
import com.spring.entity.Chengxindangan;
import com.spring.entity.Diqu;
import com.spring.entity.Xinwenxinxi;
import com.spring.service.ChengxindanganService;
import dao.CommDAO;
import dao.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;
import util.Request;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class ChengxindanganController extends BaseController{
    @Autowired
    private ChengxindanganMapper dao;
    @Autowired
    private ChengxindanganService service;


    boolean findByTwo(String leibie, String relativeId){
        Chengxindangan record = service.findByTow(leibie,relativeId);
        return record != null;
    }//true为有记录


    /**
     *  后台列表页
     *
     */
    @RequestMapping("/dangan_list")
    public String list(){
        // 检测是否有登录，没登录则跳转到登录页面
        if(!checkLogin()){return showError("尚未登录" , "./login.do");}
        String order = Request.get("order" , "id"); // 获取前台提交的URL参数 order  如果没有则设置为id
        String sort  = Request.get("sort" , "desc"); // 获取前台提交的URL参数 sort  如果没有则设置为desc
        int    pagesize = Request.getInt("pagesize" , 12); // 获取前台一页多少行数据
        Example example = new Example(Diqu.class); //  创建一个扩展搜索类
        Example.Criteria criteria = example.createCriteria();          // 创建一个扩展搜索条件类
        String where = " 1=1 ";   // 创建初始条件为：1=1
        where += getWhere();      // 从方法中获取url 上的参数，并写成 sql条件语句
        criteria.andCondition(where);   // 将条件写进上面的扩展条件类中
        if(sort.equals("desc")){        // 判断前台提交的sort 参数是否等于  desc倒序  是则使用倒序，否则使用正序
            example.orderBy(order).desc();  // 把sql 语句设置成倒序
        }else{
            example.orderBy(order).asc();   // 把 sql 设置成正序
        }
        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));  // 获取前台提交的URL参数 page  如果没有则设置为1
        page = Math.max(1 , page);  // 取两个数的最大值，防止page 小于1
        List<Chengxindangan> list = service.selectPageExample(example , page , pagesize);   // 获取当前页的行数
        // 将列表写给界面使用
        assign("totalCount" , request.getAttribute("totalCount"));
        assign("list" , list);
        assign("orderby" , order);  // 把当前排序结果写进前台
        assign("sort" , sort);      // 把当前排序结果写进前台
        return json();   // 将数据写给前端
    }

    public String getWhere(){
        _var = new LinkedHashMap(); // 重置数据
        String where = " ";
        // 以下也是一样的操作，判断是否符合条件，符合则写入sql 语句
        if(!Request.get("leibie").isEmpty()) {
            where += " AND leibie = '"+Request.get("leibie")+"' ";
        }
        if(!Request.get("relativeId").isEmpty()) {
            where += " AND relativeid ='"+Request.get("relativeId")+"' ";
        }
        if(!Request.get("xinyu").isEmpty()) {
            where += " AND xinyu = '"+Request.get("xinyu")+"' ";
        }
        if(!Request.get("fuwu").isEmpty()) {
            where += " AND fuwu = '"+Request.get("fuwu")+"' ";
        }
        return where;
    }


    @RequestMapping("/dangan_add")
    public String add() {
        _var = new LinkedHashMap(); // 重置数据
        return json();   // 将数据写给前端
    }

    @RequestMapping("/dangan_updt")
    public String updt() {
        _var = new LinkedHashMap(); // 重置数据
        int id = Request.getInt("id");
        // 获取行数据，并赋值给前台jsp页面
        Chengxindangan mmm = service.find(id);
        assign("mmm" , mmm);
        assign("updtself" , 0);
        return json();   // 将数据写给前端
    }
    /**
     * 添加内容
     */
    @RequestMapping("/danganinsert")
    public String insert() {
        _var = new LinkedHashMap(); // 重置数据

        if(findByTwo(Request.get("leibie"),Request.get("relativeId"))){
           return showError("信息已存在");//有记录就不能添加
        }

        Chengxindangan post = new Chengxindangan();  // 创建实体类
        // 设置前台提交上来的数据到实体类中
        post.setLeibie(Request.get("leibie"));
        post.setRelativeId(Request.get("relativeId"));
        post.setXinyu(Request.get("xinyu"));
        post.setFuwu(Request.get("fuwu"));
        service.insert(post); // 插入数据
        if(isAjax()){return jsonResult(post);}
        return showSuccess("保存成功" , Request.get("referer").isEmpty() ? request.getHeader("referer") : Request.get("referer"));
    }

    /**更新内容*/
    @RequestMapping("/danganupdate")
    public String update() {
        _var = new LinkedHashMap(); // 重置数据
        Chengxindangan post = new Chengxindangan();  // 创建实体类
        // 将前台表单数据填充到实体类
        if(!Request.get("xinyu").isEmpty())
            post.setXinyu(Request.get("xinyu"));
        if(!Request.get("fuwu").isEmpty())
            post.setFuwu(Request.get("fuwu"));
        post.setId(Request.getInt("id"));
        service.update(post); // 更新数据
        if(isAjax()){return jsonResult(post);}
        return showSuccess("保存成功" , Request.get("referer")); // 弹出保存成功，并跳转到前台提交的 referer 页面
    }


    /**详情*/
    @RequestMapping("/dangandetail")
    public String detailweb() {
        _var = new LinkedHashMap(); // 重置数据
        int id = Request.getInt("id");
        Chengxindangan map = service.find(id);
        assign("map" , map);
        return json();   // 将数据写给前端
    }

    /**
     *  删除
     */
    @RequestMapping("/dangan_delete")
    public String delete()
    {
        _var = new LinkedHashMap(); // 重置数据
        if(!checkLogin()){
            return showError("尚未登录");
        }
        int id = Request.getInt("id");  // 根据id 删除某行数据
        HashMap map = Query.make("chengxindangan").find(id);

        service.delete(id);// 根据id 删除某行数据
        return showSuccess("删除成功",request.getHeader("referer"));//弹出删除成功，并跳回上一页
    }


}

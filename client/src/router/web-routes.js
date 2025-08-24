
export default [
{
    path:"yonghuadd",
        name:"IndexyonghuAdd",
    component: () => import("@/views/yonghu/webadd"),
    meta: { title:"用户添加"  }
},
{
    path:"xinwenxinxi",
        name:"IndexxinwenxinxiList",
    component: () => import("@/views/xinwenxinxi/index"),
    meta: { title:"告示信息列表"  }
},
{
    path:"xinwenxinxidetail",
        name:"IndexxinwenxinxiDetail",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/xinwenxinxi/webdetail"),
    meta: { title:"告示信息详情"  }
},




    {
        path:"dangan",
        name:"IndexdanganList",
        component: () => import("@/views/dangan/index"),
        meta: { title:"档案列表"  }
    },
    {
        path:"dangandetail",
        name:"IndexxinwenxinxiDetail",
        props:route=>({id:route.query.id}),
        component: () => import("@/views/dangan/webdetail"),
        meta: { title:"档案信息详情"  }
    },





{
    path:"liuyanbanadd",
        name:"IndexliuyanbanAdd",
    component: () => import("@/views/liuyanban/webadd"),
    meta: { title:"投诉板添加" ,authLogin:true,msg:true }
},
{
    path:"jingdianxinxi",
        name:"IndexjingdianxinxiList",
    component: () => import("@/views/jingdianxinxi/index"),
    meta: { title:"景点信息列表"  }
},
{
    path:"jingdianxinxidetail",
        name:"IndexjingdianxinxiDetail",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/jingdianxinxi/webdetail"),
    meta: { title:"景点信息详情"  }
},
{
    path:"difangmeishi",
        name:"IndexdifangmeishiList",
    component: () => import("@/views/difangmeishi/index"),
    meta: { title:"地方美食列表"  }
},
{
    path:"difangmeishidetail",
        name:"IndexdifangmeishiDetail",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/difangmeishi/webdetail"),
    meta: { title:"地方美食详情"  }
},
]

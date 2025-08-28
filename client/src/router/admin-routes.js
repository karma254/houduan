import store from '@/store';

export default [
{
    path:"admins",
        name:"AdminadminsList",
    component: () => import("@/views/admins/list"),
    meta: { title:"管理员列表",authLogin:true }
},
{
    path:"adminsadd",
        name:"AdminadminsAdd",
    component: () => import("@/views/admins/add"),
    meta: { title:"添加管理员",authLogin:true }
},
{
    path:"adminsupdt",
        name:"AdminadminsUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/admins/updt"),
    meta: { title:"编辑管理员",authLogin:true }
},
{
    path:"adminsupdtself",
        name:"AdminadminsUpdtSelf",
    props:route=>({id:store.state.user.session.id}),
    component: () => import("@/views/admins/updt"),
    meta: { title:"编辑管理员",authLogin:true }
},
{
    path:"yonghu",
        name:"AdminyonghuList",
    component: () => import("@/views/yonghu/list"),
    meta: { title:"用户列表",authLogin:true }
},
{
    path:"yonghuadd",
        name:"AdminyonghuAdd",
    component: () => import("@/views/yonghu/add"),
    meta: { title:"添加用户",authLogin:true }
},
{
    path:"yonghuupdt",
        name:"AdminyonghuUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/yonghu/updt"),
    meta: { title:"编辑用户",authLogin:true }
},
{
    path:"yonghuupdtself",
        name:"AdminyonghuUpdtSelf",
    props:route=>({id:store.state.user.session.id}),
    component: () => import("@/views/yonghu/updt"),
    meta: { title:"编辑用户",authLogin:true }
},
{
    path:"xinwenfenlei",
        name:"AdminxinwenfenleiList",
    component: () => import("@/views/xinwenfenlei/list"),
    meta: { title:"告示分类列表",authLogin:true }
},
{
    path:"xinwenfenleiadd",
        name:"AdminxinwenfenleiAdd",
    component: () => import("@/views/xinwenfenlei/add"),
    meta: { title:"添加告示分类",authLogin:true }
},
{
    path:"xinwenfenleiupdt",
        name:"AdminxinwenfenleiUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/xinwenfenlei/updt"),
    meta: { title:"编辑告示分类",authLogin:true }
},
{
    path:"xinwenxinxi",
        name:"AdminxinwenxinxiList",
    component: () => import("@/views/xinwenxinxi/list"),
    meta: { title:"告示信息列表",authLogin:true }
},
{
    path:"xinwenxinxi_tianjiaren",
        name:"AdminxinwenxinxiListtianjiaren",
    component: () => import("@/views/xinwenxinxi/list-tianjiaren"),
    meta: { title:"告示信息列表",authLogin:true }
},
{
    path:"xinwenxinxiadd",
        name:"AdminxinwenxinxiAdd",
    component: () => import("@/views/xinwenxinxi/add"),
    meta: { title:"添加告示信息",authLogin:true }
},
{
    path:"xinwenxinxiupdt",
        name:"AdminxinwenxinxiUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/xinwenxinxi/updt"),
    meta: { title:"编辑告示信息",authLogin:true }
},
{
    path:"xinwenxinxidetail",
        props:route=>({id:route.query.id}),
    name:"AdminxinwenxinxiDetail",
    component: () => import("@/views/xinwenxinxi/detail"),
    meta: { title:"告示信息详情",authLogin:true }
},




    {
        path:"dangan",
        name:"AdmindanganList",
        component: () => import("@/views/dangan/list"),
        meta: { title:"档案信息列表",authLogin:true }
    },
    {
        path:"danganadd",
        name:"AdmindanganAdd",
        component: () => import("@/views/dangan/add"),
        meta: { title:"添加档案信息",authLogin:true }
    },
    {
        path:"danganupdt",
        name:"AdmindanganUpdt",
        props:route=>({id:route.query.id}),
        component: () => import("@/views/dangan/updt"),
        meta: { title:"编辑档案信息",authLogin:true }
    },
    {
        path:"dangandetail",
        props:route=>({id:route.query.id}),
        name:"AdmindanganDetail",
        component: () => import("@/views/dangan/detail"),
        meta: { title:"档案信息详情",authLogin:true }
    },





{
    path:"liuyanban",
        name:"AdminliuyanbanList",
    component: () => import("@/views/liuyanban/list"),
    meta: { title:"投诉列表",authLogin:true }
},
{
    path:"liuyanban_liuyanren",
        name:"AdminliuyanbanListliuyanren",
    component: () => import("@/views/liuyanban/list-liuyanren"),
    meta: { title:"投诉列表",authLogin:true }
},
{
    path:"liuyanbanadd",
        name:"AdminliuyanbanAdd",
    component: () => import("@/views/liuyanban/add"),
    meta: { title:"添加投诉板",authLogin:true }
},
{
    path:"liuyanbanupdt",
        name:"AdminliuyanbanUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/liuyanban/updt"),
    meta: { title:"编辑投诉板",authLogin:true }
},
{
    path:"liuyanbandetail",
        props:route=>({id:route.query.id}),
    name:"AdminliuyanbanDetail",
    component: () => import("@/views/liuyanban/detail"),
    meta: { title:"投诉板详情",authLogin:true }
},
{
    path:"lunbotu",
        name:"AdminlunbotuList",
    component: () => import("@/views/lunbotu/list"),
    meta: { title:"轮播图列表",authLogin:true }
},
{
    path:"lunbotuadd",
        name:"AdminlunbotuAdd",
    component: () => import("@/views/lunbotu/add"),
    meta: { title:"添加轮播图",authLogin:true }
},
{
    path:"lunbotuupdt",
        name:"AdminlunbotuUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/lunbotu/updt"),
    meta: { title:"编辑轮播图",authLogin:true }
},
{
    path:"jingdianxinxi",
        name:"AdminjingdianxinxiList",
    component: () => import("@/views/jingdianxinxi/list"),
    meta: { title:"景点信息列表",authLogin:true }
},
{
    path:"jingdianxinxiadd",
        name:"AdminjingdianxinxiAdd",
    component: () => import("@/views/jingdianxinxi/add"),
    meta: { title:"添加景点信息",authLogin:true }
},
{
    path:"jingdianxinxiupdt",
        name:"AdminjingdianxinxiUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/jingdianxinxi/updt"),
    meta: { title:"编辑景点信息",authLogin:true }
},
{
    path:"jingdianxinxidetail",
        props:route=>({id:route.query.id}),
    name:"AdminjingdianxinxiDetail",
    component: () => import("@/views/jingdianxinxi/detail"),
    meta: { title:"景点信息详情",authLogin:true }
},
{
    path:"diqu",
        name:"AdmindiquList",
    component: () => import("@/views/diqu/list"),
    meta: { title:"地区列表",authLogin:true }
},
{
    path:"diquadd",
        name:"AdmindiquAdd",
    component: () => import("@/views/diqu/add"),
    meta: { title:"添加地区",authLogin:true }
},
{
    path:"diquupdt",
        name:"AdmindiquUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/diqu/updt"),
    meta: { title:"编辑地区",authLogin:true }
},
{
    path:"difangfandian",
        name:"AdmindifangfandianList",
    component: () => import("@/views/difangfandian/list"),
    meta: { title:"地方饭店列表",authLogin:true }
},
{
    path:"difangfandianadd",
        name:"AdmindifangfandianAdd",
    component: () => import("@/views/difangfandian/add"),
    meta: { title:"添加地方饭店",authLogin:true }
},
{
    path:"difangfandianupdt",
        name:"AdmindifangfandianUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/difangfandian/updt"),
    meta: { title:"编辑地方饭店",authLogin:true }
},
{
    path:"difangfandiandetail",
        props:route=>({id:route.query.id}),
    name:"AdmindifangfandianDetail",
    component: () => import("@/views/difangfandian/detail"),
    meta: { title:"地方饭店详情",authLogin:true }
},
{
    path:"fandianfenlei",
        name:"AdminfandianfenleiList",
    component: () => import("@/views/fandianfenlei/list"),
    meta: { title:"饭店分类列表",authLogin:true }
},
{
    path:"fandianfenleiadd",
        name:"AdminfandianfenleiAdd",
    component: () => import("@/views/fandianfenlei/add"),
    meta: { title:"添加饭店分类",authLogin:true }
},
{
    path:"fandianfenleiupdt",
        name:"AdminfandianfenleiUpdt",
    props:route=>({id:route.query.id}),
    component: () => import("@/views/fandianfenlei/updt"),
    meta: { title:"编辑饭店分类",authLogin:true }
},
    {
        path:"daoyoulist",
        name:"Admindaoyou",
        props:route=>({id:route.query.id}),
        component: () => import('@/views/daoyou/list')

    },

    {
        path: "daoyouadd",
        name: "AdmindaoyouAdd",
        component: () => import("@/views/daoyou/add"),
        meta: { title: "添加导游", authLogin: true }
    },

    {
        path: "daoyouupdt",
        name: "AdmindaoyouUpdt",
        props: route => ({ id: route.query.id }),
        component: () => import("@/views/daoyou/updt"),
        meta: { title: "编辑导游", authLogin: true }
    },

    {
        path: "luyouadd",
        name: "AdminLuyouAdd",
        component: () => import("@/views/luyou/add"),
        meta: { title: "添加路线", authLogin: true }
    },
    {
        path: 'luyoulist',
        name: 'AdminLuyouList',
        component: () => import('@/views/luyou/list'),
        meta: { title: '路线管理' }
    },

    {
        path: "daoyou/yonghu_list", // 添加路径分隔符
        name: "AdminDaoyouYonghuList", // 使用驼峰命名
        meta: {
            title: "用户导游列表",
            authLogin: true
        },
        component: () => import("@/views/daoyou/yonghu_list")

    },
    {
        path: "luyou/yonghu_list", // 添加路径分隔符
        name: "AdminluyouYonghuList", // 使用驼峰命名
        meta: {
            title: "用户路线列表",
            authLogin: true
        },
        component: () => import("@/views/luyou/yonghu_list")

    },
]

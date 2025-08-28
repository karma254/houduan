

export default [
        {
        label:'个人中心',
        to:'',
        children:[
                        {
                label:'修改个人资料',
                to:'/admin/yonghuupdtself',
            },
                        {
                label:'修改密码',
                to:'/admin/mod',
            },
                        {
                label:'我的反馈',
                to:'/admin/liuyanban_liuyanren',
            },
                    ]
    },
    {
        label: '导游信息',
        icon: 'el-icon-guide', // 添加图标
        children: [
            {
                label: '导游查询',
                to: '/admin/daoyou/yonghu_list', // 保持与路由一致
                icon: 'el-icon-search'
            }
        ]

    },
    {
        label: '徒步路线信息',
        icon: 'el-icon-guide', // 添加图标
        children: [
            {
                label: '路线查询',
                to: '/admin/luyou/yonghu_list', // 保持与路由一致
                icon: 'el-icon-search'
            }
        ]

    },
    ]

create table admins
(
    id       int unsigned auto_increment
        primary key,
    username varchar(50)                         not null comment '帐号',
    pwd      varchar(50)                         not null comment '密码',
    addtime  timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '管理员' charset = utf8mb3;

create table difangfandian
(
    id            int unsigned auto_increment
        primary key,
    fandianbianhao varchar(50)                         not null comment '饭店编号',
    mingcheng     varchar(255)                        not null comment '名称',
    fujinjingdian varchar(255)                        not null comment '附近景点',
    fenlei        int unsigned                        not null comment '分类',
    tupian        text                                not null comment '图片',
    jiage         decimal(18, 2)                      not null comment '价格',
    fandianjianjie text                                not null comment '饭店简介',
    addtime       timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '地方饭店' charset = utf8mb3;

create index difangfandian_fenlei_index
    on difangfandian (fenlei);

create table diqu
(
    id            int unsigned auto_increment
        primary key,
    diqumingcheng varchar(255)                        not null comment '地区名称',
    addtime       timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '地区' charset = utf8mb3;

create table jingdianxinxi
(
    id                int unsigned auto_increment
        primary key,
    jingdianbianhao   varchar(50)                         not null comment '景点编号',
    jingdianmingcheng varchar(255)                        not null comment '景点名称',
    suoshudiqu        int unsigned                        not null comment '所属地区',
    tupian            text                                not null comment '图片',
    kaifangshijian    varchar(255)                        not null comment '开放时间',
    fujinfandian       text                                not null comment '附近饭店',
    dizhi             varchar(255)                        not null comment '地址',
    piaojia           decimal(18, 2)                      not null comment '票价',
    liulanliang       int                                 not null comment '浏览量',
    miaoshu           longtext                            not null comment '描述',
    addtime           timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '景点信息' charset = utf8mb3;

create index jingdianxinxi_suoshudiqu_index
    on jingdianxinxi (suoshudiqu);

create table liuyanban
(
    id            int unsigned auto_increment
        primary key,
    xingming      varchar(50)                         not null comment '姓名',
    lianxidianhua varchar(50)                         not null comment '联系电话',
    liuyanneirong text                                not null comment '留言内容',
    liuyanren     varchar(50)                         not null comment '留言人',
    huifuneirong  text                                not null comment '回复内容',
    addtime       timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '留言板' charset = utf8mb3;

create table lunbotu
(
    id      int unsigned auto_increment
        primary key,
    title   varchar(50)                         not null comment '标题',
    image   varchar(255)                        not null comment '图片',
    url     varchar(255)                        not null comment '连接地址',
    addtime timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '轮播图' charset = utf8mb3;

create table manager
(
    managerid varchar(50)   not null
        primary key,
    name      varchar(20)   null,
    password  varchar(20)   null,
    flag      int default 0 null
)
    charset = utf8mb3;

create table fandianfenlei
(
    id              int unsigned auto_increment
        primary key,
    fenleimingcheng varchar(255)                        not null comment '分类名称',
    addtime         timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '饭店分类' charset = utf8mb3;

create table token
(
    token      char(32)                            not null comment '唯一值'
        primary key,
    session    text                                not null comment '保存得数据',
    cx         varchar(50)                         not null comment '登录权限',
    login      varchar(50)                         not null comment '登录模块',
    username   varchar(50)                         not null comment '登录用户',
    valueid    varchar(50)                         not null comment '用户id',
    token_time timestamp default CURRENT_TIMESTAMP not null comment '当前时间'
)
    comment '前端登录凭证' charset = utf8mb3;

create table xinwenfenlei
(
    id              int unsigned auto_increment
        primary key,
    fenleimingcheng varchar(50)                         not null comment '分类名称',
    addtime         timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '新闻分类' charset = utf8mb3;

create table xinwenxinxi
(
    id         int unsigned auto_increment
        primary key,
    biaoti     varchar(255)                        not null comment '标题',
    fenlei     int unsigned                        not null comment '分类',
    tupian     varchar(255)                        not null comment '图片',
    tianjiaren varchar(50)                         not null comment '添加人',
    dianjilv   int                                 not null comment '点击率',
    neirong    longtext                            not null comment '内容',
    addtime    timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '新闻信息' charset = utf8mb3;

create index xinwenxinxi_fenlei_index
    on xinwenxinxi (fenlei);

create table yonghu
(
    id           int unsigned auto_increment
        primary key,
    yonghuming   varchar(50)                         not null comment '用户名',
    mima         varchar(50)                         not null comment '密码',
    xingming     varchar(50)                         not null comment '姓名',
    xingbie      varchar(255)                        not null comment '性别',
    shouji       varchar(50)                         not null comment '手机',
    youxiang     varchar(50)                         not null comment '邮箱',
    shenfenzheng varchar(50)                         not null comment '身份证',
    touxiang     varchar(255)                        not null comment '头像',
    addtime      timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
    comment '用户' charset = utf8mb3;



import http from "@/utils/ajax/http"
import {extend} from "@/utils/extend"

const api = {
    code:{
        OK:0    },
    user:{
        login:'authLogin.do?ac=login',
        tokenLogin:'tokenLogin.do',
        logout: 'logout.do'
    },
    checkUpdate:'sh.do',
    editorPassword :'editPassword',
    checkField:'checkno.do',

        payment:'payment.do',
    
    collect:'collect.do',
    shoucangjilu: {
        listusername:"shoucangjilu_list2.do",
        delete:'shoucangjilu_delete'
    },
    captch(){
        var url = "captcha.do?rd="+Math.floor(Math.random()*100000);
        return new Promise((resolve, reject) => {
            http.get( url ).then(res=>{
                var url = res.data;
                resolve(url);
            },reject);
        });
    },
    search:{
        select:'selectUpdateSearch.do',
        table:'tableAjax.do?a=table',
        selectView:'selectView.do',
        all:'selectAll.do'
    },
    attachment:{
        uploadUrl:'upload_re.do',
        upload (file) {
            return new Promise((resolve, reject) => {
                var formdata = new FormData();
                formdata.append("fujian" , file , file.name || 'tmp.png');
                http.post(api.attachment.uploadUrl , formdata)
                    .then(res=>{
                        if(res.code == api.code.OK)
                        {
                            resolve(res.data);
                        }else{
                            reject(res.msg);
                        }
                    }).catch(err=>{
                        reject(err.message);
                    });
            });
        },
    },
        admins:{
                list:"admins_list.do",
                insert:"adminsinsert.do",
                update:"adminsupdate.do",
                delete:"admins_delete.do",
                detail:"admins_detail.do",
                create:"admins_add.do",
                edit:"admins_updt.do",
            },
        yonghu:{
                list:"yonghu_list.do",
                insert:"yonghuinsert.do",
                update:"yonghuupdate.do",
                delete:"yonghu_delete.do",
                detail:"yonghu_detail.do",
                create:"yonghu_add.do",
                edit:"yonghu_updt.do",
            },
        xinwenfenlei:{
                list:"xinwenfenlei_list.do",
                insert:"xinwenfenleiinsert.do",
                update:"xinwenfenleiupdate.do",
                delete:"xinwenfenlei_delete.do",
                detail:"xinwenfenlei_detail.do",
                create:"xinwenfenlei_add.do",
                edit:"xinwenfenlei_updt.do",
            },
        xinwenxinxi:{
                list:"xinwenxinxi_list.do",
                insert:"xinwenxinxiinsert.do",
                update:"xinwenxinxiupdate.do",
                delete:"xinwenxinxi_delete.do",
                detail:"xinwenxinxi_detail.do",
                create:"xinwenxinxi_add.do",
                edit:"xinwenxinxi_updt.do",
                listtianjiaren:"xinwenxinxi_list_tianjiaren.do",
                weblist:"xinwenxinxilist.do",
                webdetail:"xinwenxinxidetail.do",
            },




    dangan:{
        list:"dangan_list.do",
        insert:"danganinsert.do",
        update:"danganupdate.do",
        delete:"dangan_delete.do",
        detail:"dangandetail.do",
        create:"dangan_add.do",
        edit:"dangan_updt.do",
    },



        liuyanban:{
                list:"liuyanban_list.do",
                insert:"liuyanbaninsert.do",
                update:"liuyanbanupdate.do",
                delete:"liuyanban_delete.do",
                detail:"liuyanban_detail.do",
                create:"liuyanban_add.do",
                edit:"liuyanban_updt.do",
                listliuyanren:"liuyanban_list_liuyanren.do",
            },
        lunbotu:{
                list:"lunbotu_list.do",
                insert:"lunbotuinsert.do",
                update:"lunbotuupdate.do",
                delete:"lunbotu_delete.do",
                detail:"lunbotu_detail.do",
                create:"lunbotu_add.do",
                edit:"lunbotu_updt.do",
            },
        jingdianxinxi:{
                list:"jingdianxinxi_list.do",
                insert:"jingdianxinxiinsert.do",
                update:"jingdianxinxiupdate.do",
                delete:"jingdianxinxi_delete.do",
                detail:"jingdianxinxi_detail.do",
                create:"jingdianxinxi_add.do",
                edit:"jingdianxinxi_updt.do",
                weblist:"jingdianxinxilist.do",
                webdetail:"jingdianxinxidetail.do",
            },
        diqu:{
                list:"diqu_list.do",
                insert:"diquinsert.do",
                update:"diquupdate.do",
                delete:"diqu_delete.do",
                detail:"diqu_detail.do",
                create:"diqu_add.do",
                edit:"diqu_updt.do",
            },
        difangfandian:{
                list:"difangfandian_list.do",
                insert:"difangfandianinsert.do",
                update:"difangfandianupdate.do",
                delete:"difangfandian_delete.do",
                detail:"difangfandian_detail.do",
                create:"difangfandian_add.do",
                edit:"difangfandian_updt.do",
                weblist:"difangfandianlist.do",
                webdetail:"difangfandiandetail.do",
            },
        fandianfenlei:{
                list:"fandianfenlei_list.do",
                insert:"fandianfenleiinsert.do",
                update:"fandianfenleiupdate.do",
                delete:"fandianfenlei_delete.do",
                detail:"fandianfenlei_detail.do",
                create:"fandianfenlei_add.do",
                edit:"fandianfenlei_updt.do",
            },
    daoyou:{
        list: "daoyou_list",
        insert: "daoyou_add",
        update: "daoyou_update",
        detail: "daoyou_detail",
        create: "daoyou_add",
        delete: "daoyou_delete"

    },
    luyou: {
        page: "luyou/page",
        add: "luyou/add",
        update: "luyou/update",
        delete: "luyou/delete",
        detail: "luyou/detail"
    }

}


export default api

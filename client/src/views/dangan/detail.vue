<template>
    
    <div class="dangan-detail" v-loading="loading" ref="print">
        <el-page-header @back="goBack" content="详情页面"></el-page-header>
        <div class="admin-detail">

          <div class="detail">
                <div class="detail-title">
                    类别：
                </div>
                <div class="detail-content">
                    {{ map.leibie }}
                </div>
            </div>

          <div class="detail">
                <div class="detail-title">
                    关联：
                </div>
            <div class="detail-content">
              {{ map.relativeId }}
            </div>
          </div>

          <div class="detail">
            <div class="detail-title">
              信誉：
            </div>
            <div class="detail-content">
              {{ map.xinyu }}
            </div>
          </div>

          <div class="detail">
            <div class="detail-title">
              服务：
            </div>
            <div class="detail-content">
              {{ map.fuwu }}
            </div>
          </div>
    </div>
    </div>
</template>
<style type="text/scss" scoped lang="scss">
.dangan-detail{
    .admin-detail {
        display: flex;
        flex-wrap: wrap;
        margin-top: 30px;
        .detail {
            width: 100%;
            padding: 5px;
            display: flex;
            margin-bottom: 10px;
            border-bottom: 1px solid #DEDEDE;
            .detail-title {
                width: 120px;
                text-align: right;
              flex-shrink: 0;
            }
            .detail-content {
                flex-grow: 1;
            }
        }

        .detail.detail-editor,.detail.detail-dataset  {
            width: 100%;
            flex-wrap: wrap;
            .detail-title {
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                background: #9a0606;
                color: #ffffff;
                text-align: left;
            }
            .detail-content{
                padding: 10px;
            }
        }
    }
}
</style>
<script>
    import api from '@/api';
    import { extend } from '@/utils/extend';

    
    export default {
        data() {
            return {
                loading:false,
                map:{},

                            }
        },
                props:{
            id:{
                type:[String,Number],
                required:true
            },
                    },
        watch: {
            id:{
                handler(){
                    this.loadDetail();
                },
                immediate:true
            }
        },
        computed: {
                    },
        methods: {
            goBack() {
                this.$router.go(-1)
            },
            loadDetail(){
                if(this.loading) return;
                this.loading = true;
                this.$get(api.dangan.detail , {
                    id:this.id
                }).then(res=>{
                    this.loading = false;
                    if(res.code == api.code.OK){
                        extend(this , res.data);
                    }else{
                        this.$message.error(res.msg);
                    }
                }).catch(err=>{
                    this.loading = false;
                    this.$message.error(err.message);
                });
            },
                    },
        created() {
                    },
        mounted() {
                    },
        destroyed() {
                    }
    }
</script>

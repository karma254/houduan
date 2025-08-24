<template>
    <div class="dangan-add" v-loading="loading">
        <el-card class="box-card">
            <div slot="header" class="clearfix updt">
              <el-page-header @back="$router.go(-1)" content="编辑档案信息">
                    </el-page-header>
            </div>
            <div class="form-database-form">


            <el-form :model="form" ref="formModel" label-width="130px" status-icon validate-on-rule-change>

              <!-- 类别 -->
              <el-form-item label="类别" prop="leibie" required :rules="[{required:true, message:'请选择类别'}]">
                <el-input
                    placeholder="选择类别"
                    style="width:450px;"
                    v-model="form.leibie"
                    disabled
                >
                </el-input>
              </el-form-item>

              <!-- 关联 -->
              <el-form-item label="关联" prop="relativeId" required :rules="[{required:true, message:'请选择关联'}]">
                <el-input
                    placeholder="关联"
                    style="width:450px;"
                    v-model="form.relativeId"
                    disabled
                />
              </el-form-item>
              <!-- 信誉 -->
              <el-form-item label="信誉" prop="xinyu" required :rules="[{required:true, message:'请选择等级'}]">
                <el-select placeholder="选择信誉等级" style="width:450px;" v-model="form.xinyu">
                  <el-option label="优秀" value="优秀"></el-option>
                  <el-option label="良好" value="良好"></el-option>
                  <el-option label="一般" value="一般"></el-option>
                  <el-option label="较差" value="较差"></el-option>
                  <el-option label="极差" value="极差"></el-option>
                </el-select>
              </el-form-item>

              <!-- 服务  -->
              <el-form-item label="服务" prop="fuwu" required :rules="[{required:true, message:'请选择服务状况'}]">
                <el-select placeholder="选择服务状况" style="width:450px;" v-model="form.fuwu">
                  <el-option label="满意" value="满意"></el-option>
                  <el-option label="基本满意" value="基本满意"></el-option>
                  <el-option label="不满意" value="不满意"></el-option>
                  <el-option label="极其不满意" value="极其不满意"></el-option>
                </el-select>
              </el-form-item>
                                <el-form-item v-if="btnText">
                    <el-button type="primary" @click="submit">{{ btnText }}</el-button>
                </el-form-item>
            </el-form>

            </div>
        </el-card>
    </div>
</template>

<script>
    import api from '@/api'
    import rule from '@/utils/rule'
    import { extend } from '@/utils/extend'

    
    export default {
      name:'dangan-updt',
      data() {
        return {
          rule,
          loading:false,
          form:{
            leibie:'',
            relativeId:'',
            xinyu:'',
            fuwu:'',
          },
          danganList:[],
        }
      },
                watch: {
                        id: {
                handler() {
                    this.loadInfo();
                }
            }
                    },
        props: {
            isRead:{
                type:Boolean,
                default:true
            },
            btnText:{
                type:String,
                default:'提交'
            },
                        id: {
                type: [String, Number],
                required: true
            },
                    },

        computed: {},
        methods: {
            submit(){
                this.$refs.formModel.validate().then(res=>{
                    if(this.loading)return;
                    this.loading = true;
                    var form = this.form;

                    this.$post(api.dangan.update , form).then(res=>{
                        this.loading = false;
                        if(res.code == api.code.OK){
                            this.$message.success('修改成功');
                            this.$emit('success' , res.data);
                            this.$refs.formModel.resetFields();
                                                        this.loadInfo();
                                                    }else{
                            this.$message.error(res.msg);
                        }
                    }).catch(err=>{
                        this.loading = false;
                        this.$message.error(err.message);
                    })

                }).catch(err=>{
                    console.log(err.message);
                })

            },
            loadInfo(){

                                // 更新数据,获取数据
                this.loading = true;
                var form = this.form;
                this.$post(api.dangan.edit , {
                    id:this.id
                }).then(res=>{
                    this.loading = false;
                    if(res.code == api.code.OK){
                        extend(this , res.data);
                        this.form = res.data.mmm;

                    }else{
                        this.$message.error(res.msg);
                        this.$router.go(-1);
                    }
                }).catch(err=>{
                    this.$message.error(err.message);
                    this.$router.go(-1);
                });
                            },
                    },
        created() {
            this.loadInfo();
                    },
        mounted() {
                    },
        destroyed() {
                    }
    }
</script>

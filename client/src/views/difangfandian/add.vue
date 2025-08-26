<template>
    <div class="difangfandian-add" v-loading="loading">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="title"> 添加地方饭店</span>
            </div>
            <div class="form-database-form">


                <el-form :model="form" ref="formModel" label-width="130px" status-icon validate-on-rule-change>
                    <el-form-item label="饭店编号" prop="fandianbianhao" :rules="[{required:true, message:'请填写饭店编号'}]">
                        <el-input placeholder="输入饭店编号" style="width:250px;" v-model="form.fandianbianhao"/>
                    </el-form-item>

                    <el-form-item label="名称" prop="mingcheng">
                        <el-input placeholder="输入名称" style="width:450px;" v-model="form.mingcheng"/>
                    </el-form-item>

                    <el-form-item label="附近景点" prop="fujinjingdian">
                        <el-select v-model="form.fujinjingdian">
                            <el-option v-for="m in jingdianxinxiList" :value="m.jingdianmingcheng"
                                       :label="m.jingdianmingcheng"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="分类" prop="fenlei">
                        <el-select v-model="form.fenlei">
                            <el-option v-for="m in fandianfenleiList" :value="m.id"
                                       :label="m.fenleimingcheng"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="图片" prop="tupian">
                        <e-upload-images v-model="form.tupian"></e-upload-images>
                    </el-form-item>

                    <el-form-item label="人均价格" prop="jiage" required
                                  :rules="[{required:true, message:'请填写价格'}, {validator:rule.checkNumber, message:'输入一个有效数字'}]">
                        <el-input placeholder="输入人均价格" style="width:250px;" v-model="form.jiage"/>
                    </el-form-item>

                    <el-form-item label="饭店简介" prop="fandianjianjie">
                        <el-input type="textarea" v-model="form.fandianjianjie"></el-input>
                    </el-form-item>

                    <el-form-item v-if="btnText">
                        <el-button type="primary" @click="submit">{{ btnText }}</el-button>
                    </el-form-item>
                </el-form>

            </div>
        </el-card>
    </div>
</template>
<style type="text/scss" scoped lang="scss">
    .difangfandian-add {

    }
</style>
<script>
    import api from '@/api'
    import rule from '@/utils/rule'
    import {extend} from '@/utils/extend'


    export default {
        name: 'difangfandian-add',
        data() {
            return {
                rule,
                loading: false,
                form: {
                    fandianbianhao: rule.getID(),
                    mingcheng: '',
                    fujinjingdian: '',
                    fenlei: '',
                    tupian: '',
                    jiage: '',
                    fandianjianjie: '',


                },

                jingdianxinxiList: [],
                fandianfenleiList: [],

            }
        },
        watch: {},
        props: {
            isRead: {
                type: Boolean,
                default: true
            },
            btnText: {
                type: String,
                default: '提交'
            },
        },

        computed: {},
        methods: {
            submit() {
                this.$refs.formModel.validate().then(res => {
                    if (this.loading) return;
                    this.loading = true;
                    var form = this.form;

                    this.$post(api.difangfandian.insert, form).then(res => {
                        this.loading = false;
                        if (res.code == api.code.OK) {
                            this.$message.success('添加成功');
                            this.$emit('success', res.data);
                            this.$refs.formModel.resetFields();
                            this.loadInfo();
                        } else {
                            this.$message.error(res.msg);
                        }
                    }).catch(err => {
                        this.loading = false;
                        this.$message.error(err.message);
                    })

                }).catch(err => {
                    console.log(err.message);
                })

            },
            loadInfo() {

                var form = this.form;
                // 获取模块得数据
                this.loading = true;
                this.$post(api.difangfandian.create, {
                    id: this.$route.query.id
                }).then(res => {
                    this.loading = false;
                    if (res.code == api.code.OK) {
                        extend(this, res.data);
                    } else {
                        this.$message.error(res.msg);
                        this.$router.go(-1);
                    }
                }).catch(err => {
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

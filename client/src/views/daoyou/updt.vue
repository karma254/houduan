<template>
  <div class="v-updt" v-loading="loading">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">编辑导游</span>
        <div class="actions">
          <el-button @click="$router.go(-1)" size="mini">返回</el-button>
        </div>
      </div>

      <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="身份证号" prop="idCard">
              <el-input v-model="form.idCard" placeholder="请输入身份证号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="导游证号" prop="certificateNumber">
              <el-input v-model="form.certificateNumber" placeholder="请输入导游证号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="等级" prop="level">
              <el-select v-model="form.level" placeholder="请选择等级">
                <el-option label="初级" value="初级"></el-option>
                <el-option label="中级" value="中级"></el-option>
                <el-option label="高级" value="高级"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态">
                <el-option label="空闲" value="空闲"></el-option>
                <el-option label="带团中" value="带团中"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="从业年限" prop="experience">
              <el-input-number v-model="form.experience" :min="0" :max="50"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="个人简介" prop="introduction">
              <el-input type="textarea" v-model="form.introduction" :rows="4" placeholder="请输入个人简介"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" @click="submitForm">保存</el-button>
          <el-button @click="$router.go(-1)">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import api from '@/api';
import { extend } from '@/utils/extend';

export default {
  data() {
    return {
      loading: false,
      form: {
        id: null,
        name: '',
        phone: '',
        idCard: '',
        certificateNumber: '',
        level: '初级',
        status: '空闲',
        experience: 0,
        introduction: ''
      },
      rules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        idCard: [{ required: true, message: '请输入身份证号', trigger: 'blur' }],
        certificateNumber: [{ required: true, message: '请输入导游证号', trigger: 'blur' }]
      }
    }
  },
  methods: {
    loadDetail() {
      if (this.loading) return;
      this.loading = true;

      this.$post(api.daoyou.detail, {
        id: this.$route.query.id
      }).then(res => {
        this.loading = false;
        if (res.code == api.code.OK) {
          this.form = res.data;
        } else {
          this.$message.error(res.msg);
        }
      }).catch(err => {
        this.loading = false;
        this.$message.error(err.message);
      });
    },

    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.loading = true;

          this.$post(api.daoyou.update, this.form).then(res => {
            this.loading = false;
            if (res.code == api.code.OK) {
              this.$message.success('更新成功');
              this.$router.push('/admin/daoyou');
            } else {
              this.$message.error(res.msg);
            }
          }).catch(err => {
            this.loading = false;
            this.$message.error(err.message);
          });
        }
      });
    }
  },
  created() {
    this.loadDetail();
  }
}
</script>
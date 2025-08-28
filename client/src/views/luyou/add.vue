<template>
  <div class="v-add" v-loading="loading">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">添加路线</span>
        <div class="actions">
          <el-button @click="$router.go(-1)" size="mini">返回</el-button>
        </div>
      </div>

      <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="路线名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入路线名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="难度" prop="difficulty">
              <el-select v-model="form.difficulty" placeholder="请选择难度">
                <el-option label="简单" value="简单"></el-option>
                <el-option label="中等" value="中等"></el-option>
                <el-option label="困难" value="困难"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="距离(km)" prop="distance">
              <el-input-number v-model="form.distance" :min="0" :step="0.1"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="预计时长" prop="duration">
              <el-input-number v-model="form.duration" :min="0" :step="0.5"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="路线描述" prop="description">
              <el-input type="textarea" v-model="form.description" :rows="4" placeholder="请输入路线描述"></el-input>
              </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
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
        name: '',
        difficulty: '中等',
        distance: 0,
        duration: 0,
        description: ''
      },
      rules: {
        name: [{ required: true, message: '请输入路线名称', trigger: 'blur' }],
        difficulty: [{ required: true, message: '请选择难度', trigger: 'change' }]
      }
    }
  },
  methods: {
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.loading = true;

          this.$post(api.luyou.add, this.form).then(res => {
            this.loading = false;
            if (res.code == api.code.OK) {
              this.$message.success('添加成功');
              this.$router.push('/admin/luyou');
            } else {
              this.$message.error(res.msg);
            }
          }).catch(err => {
            this.loading = false;
            this.$message.error(err.message);
          });
        }
      });
    },

    resetForm() {
      this.$refs.form.resetFields();
    }
  }
}
</script>

<style scoped>
.v-add {
  padding: 20px;
}

.box-card {
  max-width: 1000px;
  margin: 0 auto;
}

.title {
  font-size: 18px;
  font-weight: bold;
}

.actions {
  float: right;
}
</style>
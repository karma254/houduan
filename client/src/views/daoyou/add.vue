<template>
  <div class="v-add" v-loading="loading">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">添加导游</span>
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
            <el-form-item label="年龄" prop="age">
              <el-input-number
                  v-model="form.age"
                  :min="18"
                  :max="65"
                  placeholder="请输入年龄"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-select v-model="form.gender" placeholder="请选择性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
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
              <el-input-number
                  v-model="form.experience"
                  :min="0"
                  :max="50"
                  placeholder="请输入从业年限"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="个人简介" prop="introduction">
              <el-input
                  type="textarea"
                  v-model="form.introduction"
                  :rows="4"
                  placeholder="请输入个人简介"
              ></el-input>
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

export default {
  data() {
    return {
      loading: false,
      form: {
        name: '',
        age: null,
        gender: '',
        phone: '',
        idCard: '',
        certificateNumber: '',
        level: '初级',
        status: '空闲',
        experience: 0,
        introduction: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 10, message: '姓名长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
          { type: 'number', min: 18, max: 65, message: '年龄必须在 18 到 65 之间', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '身份证号格式不正确', trigger: 'blur' }
        ],
        certificateNumber: [
          { required: true, message: '请输入导游证号', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '请选择等级', trigger: 'change' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.loading = true;

          this.$post(api.daoyou.insert, this.form).then(res => {
            this.loading = false;
            if (res.code == api.code.OK) {
              this.$message.success('添加成功');
              this.$router.push('/admin/daoyou');
            } else {
              this.$message.error(res.msg);
            }
          }).catch(err => {
            this.loading = false;
            this.$message.error(err.message);
          });
        } else {
          this.$message.error('请完善表单信息');
          return false;
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
</style><!--<template>-->
<!--  <div class="daoyou-add-container">-->
<!--    <el-card class="box-card">-->
<!--      <div slot="header" class="clearfix">-->
<!--        <span class="title">添加导游</span>-->
<!--      </div>-->

<!--      <el-form :model="form" :rules="rules" ref="form" label-width="100px">-->
<!--        &lt;!&ndash; 表单字段 &ndash;&gt;-->
<!--        <el-form-item label="姓名" prop="name">-->
<!--          <el-input v-model="form.name"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="手机号" prop="phone">-->
<!--          <el-input v-model="form.phone"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="身份证号" prop="idCard">-->
<!--          <el-input v-model="form.idCard"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="导游证号" prop="certificateNumber">-->
<!--          <el-input v-model="form.certificateNumber"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="等级" prop="level">-->
<!--          <el-select v-model="form.level" placeholder="请选择等级">-->
<!--            <el-option label="初级" value="初级"></el-option>-->
<!--            <el-option label="中级" value="中级"></el-option>-->
<!--            <el-option label="高级" value="高级"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="状态" prop="status">-->
<!--          <el-select v-model="form.status" placeholder="请选择状态">-->
<!--            <el-option label="空闲" value="空闲"></el-option>-->
<!--            <el-option label="带团中" value="带团中"></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="从业年限" prop="experience">-->
<!--          <el-input-number v-model="form.experience" :min="0" :max="50"></el-input-number>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="个人简介" prop="introduction">-->
<!--          <el-input type="textarea" v-model="form.introduction" :rows="4"></el-input>-->
<!--        </el-form-item>-->

<!--        <el-form-item>-->
<!--          <el-button type="primary" @click="submitForm">提交</el-button>-->
<!--          <el-button @click="resetForm">重置</el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </el-card>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import api from '@/api';-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      form: {-->
<!--        name: '',-->
<!--        phone: '',-->
<!--        idCard: '',-->
<!--        certificateNumber: '',-->
<!--        level: '初级',-->
<!--        status: '空闲',-->
<!--        experience: 0,-->
<!--        introduction: ''-->
<!--      },-->
<!--      rules: {-->
<!--        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],-->
<!--        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],-->
<!--        idCard: [{ required: true, message: '请输入身份证号', trigger: 'blur' }],-->
<!--        certificateNumber: [{ required: true, message: '请输入导游证号', trigger: 'blur' }]-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    submitForm() {-->
<!--      this.$refs.form.validate((valid) => {-->
<!--        if (valid) {-->
<!--          this.$post(api.daoyou.insert, this.form).then(res => {-->
<!--            if (res.code == api.code.OK) {-->
<!--              this.$message.success('添加成功');-->
<!--              this.$router.push('/admin/daoyou');-->
<!--            } else {-->
<!--              this.$message.error(res.msg);-->
<!--            }-->
<!--          }).catch(err => {-->
<!--            this.$message.error(err.message);-->
<!--          });-->
<!--        }-->
<!--      });-->
<!--    },-->
<!--    resetForm() {-->
<!--      this.$refs.form.resetFields();-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->
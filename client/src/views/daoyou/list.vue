<template>
  <div class="v-list" v-loading="loading" element-loading-text="加载中">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">导游列表</span>
      </div>

      <!-- 搜索区域 -->
      <div class="form-search">
        <el-form @submit.prevent.stop :inline="true" size="mini">
          <el-form-item label="姓名">
            <el-input v-model="search.name" placeholder="请输入导游姓名" @keyup.enter="searchSubmit"></el-input>
          </el-form-item>

          <el-form-item label="手机号">
            <el-input v-model="search.phone" placeholder="请输入手机号" @keyup.enter="searchSubmit"></el-input>
          </el-form-item>

          <el-form-item label="等级">
            <el-select v-model="search.level" placeholder="请选择等级" clearable>
              <el-option label="全部" value=""></el-option>
              <el-option label="初级" value="初级"></el-option>
              <el-option label="中级" value="中级"></el-option>
              <el-option label="高级" value="高级"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="状态">
            <el-select v-model="search.status" placeholder="请极速状态" clearable>
              <el-option label="全部" value=""></el-option>
              <el-option label="空闲" value="空闲"></el-option>
              <el-option label="带团中" value="带团中"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="searchSubmit" icon="el-icon-search">查询</el-button>
            <el-button @click="resetSearch" icon="el-icon-refresh">重置</el-button>
          </el-form-item>
        </el-form>
      </div>

      <!-- 数据表格 -->
      <el-table border :data="list" style="width: 100%" highlight-current-row>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="姓名" prop="name" width="100"></el-table-column>
        <el-table-column label="手机号" prop="phone" width="120"></el-table-column>
        <el-table-column label="身份证号" prop="idCard" width="180"></el-table-column>
        <el-table-column label="导游证号" prop="certificateNumber" width="150"></el-table-column>
        <el-table-column label="等级" width="80">
          <template slot-scope="{row}">
            <el-tag :type="row.level === '高级' ? 'success' : (row.level === '中级' ? 'warning' : 'info')" size="small">
              {{ row.level }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template slot-scope="{row}">
            <el-tag :type="row.status === '空闲' ? 'success' : 'warning'" size="small">
              {{ row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template slot-scope="{row}">
            <el-button-group>
              <el-tooltip content="编辑" placement="top">
                <el-button
                    @click="$goto({path:'/admin/daoyouupdt', query:{id:row.id}})"
                    icon="el-icon-edit"
                    type="warning"
                    size="mini"
                ></el-button>
              </el-tooltip>

              <el-tooltip content="删除" placement="top">
                <el-button
                    icon="el-icon-delete"
                    type="danger"
                    size="mini"
                    @click="deleteItem(row)"
                ></el-button>
              </el-tooltip>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页控件 -->
      <div class="e-pages" style="margin-top: 10px; text-align: center">
        <el-pagination
            @current-change="loadList"
            :current-page="page"
            :page-size="pagesize"
            @size-change="sizeChange"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalCount"
        ></el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
import api from '@/api';
import { remove } from '@/utils/utils';
import { extend } from '@/utils/extend';
import objectDiff from 'objectdiff';

export default {
  data() {
    return {
      loading: false,
      list: [],
      search: {
        name: '',
        phone: '',
        level: '',
        status: ''
      },
      total: {},
      page: 1,
      pagesize: 10,
      totalCount: 0
    }
  },
  methods: {
    searchSubmit() {
      this.loadList(1);
    },

    resetSearch() {
      this.search = {
        name: '',
        phone: '',
        level: '',
        status: ''
      };
      this.loadList(1);
    },

    sizeChange(e) {
      this.pagesize = e;
      this.loadList(1);
    },

    loadList(page) {
      if (this.loading) return;

      this.loading = true;
      this.page = page;

      // 构建查询参数
      const filter = {
        page: page,
        pagesize: this.pagesize
      };

      // 添加搜索条件
      if (this.search.name) filter.name = this.search.name;
      if (this.search.phone) filter.phone = this.search.phone;
      if (this.search.level) filter.level = this.search.level;
      if (this.search.status) filter.status = this.search.status;

      // 更新URL参数
      const diff = objectDiff.diff(filter, this.$route.query);
      if (diff.changed !== 'equal') {
        this.$router.push({
          path: this.$route.path,
          query: filter
        });
      }

      // 调用API - 关键修复部分
      this.$post(api.daoyou.list, filter).then(res => {
        this.loading = false;
        console.log("API响应:", res);

        // 关键修复：根据截图中的响应结构调整
        if (res && res.code === 200) {
          // 注意：实际数据在 res.data 中
          this.list = res.data.list || [];
          this.totalCount = res.data.total || 0;

          // 显示成功消息（绿色）
          this.$message.success(res.data.msg || "查询成功");
        } else {
          // 只有当code不是200时才显示错误
          this.$message.error(res.msg || "查询失败");
        }
      }).catch(err => {
        this.loading = false;
        console.error("API请求错误:", err);
        this.$message.error("请求失败: " + (err.message || "未知错误"));
      });
    },
    deleteItem(row) {
      console.log('尝试删除导游:', row.id, row.name);

      this.$confirm('确定删除该导游信息？', '提示', {
        type: 'warning',
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(() => {
        this.loading = true;
        console.log('发送删除请求，参数:', { id: row.id });

        // 使用表单数据格式传递参数
        const formData = new FormData();
        formData.append('id', row.id);

        this.$post(api.daoyou.delete, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
          this.loading = false;
          console.log('删除响应:', res);

          if (res.code === 200) {
            // 从列表中移除该项
            const index = this.list.findIndex(item => item.id === row.id);
            if (index > -1) {
              this.list.splice(index, 1);
            }
            this.$message.success('删除成功');
          } else {
            this.$message.error(res.msg || '删除失败');
          }
        }).catch(err => {
          this.loading = false;
          console.error('删除错误详情:', err);

          if (err.response) {
            console.error('错误响应数据:', err.response.data);
            console.error('错误状态码:', err.response.status);

            if (err.response.status === 404) {
              this.$message.error('删除接口不存在');
            } else if (err.response.status === 400) {
              this.$message.error('参数错误: ' + (err.response.data.message || '缺少必要参数'));
            } else {
              this.$message.error('删除失败: ' + (err.response.data.message || '未知错误'));
            }
          } else {
            this.$message.error('删除失败: ' + (err.message || '未知错误'));
          }
        });
      }).catch(() => {
        console.log('用户取消删除操作');
        this.$message.info('已取消删除');
      });
    }
// 修改删除方法
//     deleteItem(row) {
//       this.$confirm('确定删除该导游信息？', '提示', {
//         type: 'warning'
//       }).then(() => {
//         this.loading = true;
//
//         // 使用正确的API端点
//         this.$post(api.daoyou.delete, {
//           id: row.id
//         }).then(res => {
//           this.loading = false;
//           if (res.code == api.code.OK) {
//             // 从列表中移除该项
//             const index = this.list.findIndex(item => item.id === row.id);
//             if (index > -1) {
//               this.list.splice(index, 1);
//             }
//             this.$message.success('删除成功');
//           } else {
//             this.$message.error(res.msg || "删除失败");
//           }
//         }).catch(err => {
//           this.loading = false;
//           console.error("删除请求错误:", err);
//
//           // 更详细的错误处理
//           if (err.response && err.response.status === 404) {
//             this.$message.error("删除接口不存在，请检查API配置");
//           } else {
//             this.$message.error("删除失败: " + (err.message || "未知错误"));
//           }
//         });
//       }).catch(() => {
//         // 取消删除
//       });
//     }
    // deleteItem(row) {
    //   this.$confirm('确定删除该导游信息？', '提示', {
    //     type: 'warning'
    //   }).then(() => {
    //     this.loading = true;
    //     this.$post(api.daoyou.delete, {
    //       id: row.id
    //     }).then(res => {
    //       this.loading = false;
    //       if (res && res.code === 200) {
    //         remove(this.list, row);
    //         this.$message.success('删除成功');
    //       } else {
    //         this.$message.error(res.msg || "删除失败");
    //       }
    //     }).catch(err => {
    //       this.loading = false;
    //       this.$message.error(err.message);
    //     });
    //   }).catch(() => {
    //     // 取消删除
    //   });
    // }
  },

  beforeRouteUpdate(to, from, next) {
    // 从URL参数恢复搜索条件
    if (to.query.name) this.search.name = to.query.name;
    if (to.query.phone) this.search.phone = to.query.phone;
    if (to.query.level) this.search.level = to.query.level;
    if (to.query.status) this.search.status = to.query.status;

    this.loadList(1);
    next();
  },

  created() {
    // 从URL参数恢复搜索条件
    if (this.$route.query.name) this.search.name = this.$route.query.name;
    if (this.$route.query.phone) this.search.phone = this.$route.query.phone;
    if (this.$route.query.level) this.search.level = this.$route.query.level;
    if (this.$route.query.status) this.search.status = this.$route.query.status;

    this.loadList(1);
  }
}
</script>

<style scoped>
.box-card {
  margin: 20px;
}

.title {
  font-size: 18px;
  font-weight: bold;
}

.form-search {
  margin-bottom: 20px;
}

.e-pages {
  margin-top: 20px;
}
</style>
<!--<template>-->
<!--  <div class="v-list" v-loading="loading" element-loading-text="加载中">-->
<!--    <el-card class="box-card">-->
<!--      <div slot="header" class="clearfix">-->
<!--        <span class="title">导游列表</span>-->
<!--      </div>-->

<!--      &lt;!&ndash; 搜索区域 &ndash;&gt;-->
<!--      <div class="form-search">-->
<!--        <el-form @submit.prevent.stop :inline="true" size="mini">-->
<!--          <el-form-item label="姓名">-->
<!--            <el-input v-model="search.name" placeholder="请输入导游姓名" @keyup.enter="searchSubmit"></el-input>-->
<!--          </el-form-item>-->

<!--          <el-form-item label="手机号">-->
<!--            <el-input v-model="search.phone" placeholder="请输入手机号" @keyup.enter="searchSubmit"></el-input>-->
<!--          </el-form-item>-->

<!--          <el-form-item label="等级">-->
<!--            <el-select v-model="search.level" placeholder="请选择等级" clearable>-->
<!--              <el-option label="全部" value=""></el-option>-->
<!--              <el-option label="初级" value="初级"></el-option>-->
<!--              <el-option label="中级" value="中级"></el-option>-->
<!--              <el-option label="高级" value="高级"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->

<!--          <el-form-item label="状态">-->
<!--            <el-select v-model="search.status" placeholder="请选择状态" clearable>-->
<!--              <el-option label="全部" value=""></el-option>-->
<!--              <el-option label="空闲" value="空闲"></el-option>-->
<!--              <el-option label="带团中" value="带团中"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->

<!--          <el-form-item>-->
<!--            <el-button type="primary" @click="searchSubmit" icon="el-icon-search">查询</el-button>-->
<!--            <el-button @click="resetSearch" icon="el-icon-refresh">重置</el-button>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--      </div>-->

<!--      &lt;!&ndash; 数据表格 &ndash;&gt;-->
<!--      <el-table border :data="list" style="width: 100%" highlight-current-row>-->
<!--        &lt;!&ndash; 表格列定义 &ndash;&gt;-->
<!--        <el-table-column prop="name" label="姓名" width="100"></el-table-column>-->
<!--        <el-table-column prop="phone" label="手机号" width="120"></el-table-column>-->
<!--        <el-table-column prop="idCard" label="身份证号" width="180"></el-table-column>-->
<!--        <el-table-column label="导游证号" prop="certificateNumber" width="150"></el-table-column>-->
<!--        <el-table-column label="等级" width="80">-->
<!--          <template slot-scope="{row}">-->
<!--            <el-tag :type="row.level === '高级' ? 'success' : (row.level === '中级' ? 'warning' : 'info')" size="small">-->
<!--              {{ row.level }}-->
<!--            </el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column label="状态" width="100">-->
<!--          <template slot-scope="{row}">-->
<!--            <el-tag :type="row.status === '空闲' ? 'success' : 'warning'" size="small">-->
<!--              {{ row.status }}-->
<!--            </el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column label="操作" width="180" fixed="right">-->
<!--          <template slot-scope="{row}">-->
<!--            <el-button-group>-->
<!--              <el-tooltip content="详情" placement="top">-->
<!--                <el-button-->
<!--                    @click="$goto({path:'/admin/daoyoudetail', query:{id:row.id}})"-->
<!--                    icon="el-icon-info"-->
<!--                    type="info"-->
<!--                    size="mini"-->
<!--                ></el-button>-->
<!--              </el-tooltip>-->

<!--              <el-tooltip content="编辑" placement="top">-->
<!--                <el-button-->
<!--                    @click="$goto({path:'/admin/daoyouupdt', query:{id:row.id}})"-->
<!--                    icon="el-icon-edit"-->
<!--                    type="warning"-->
<!--                    size="mini"-->
<!--                ></el-button>-->
<!--              </el-tooltip>-->

<!--              <el-tooltip content="删除" placement="top">-->
<!--                <el-button-->
<!--                    icon="el-icon-delete"-->
<!--                    type="danger"-->
<!--                    size="mini"-->
<!--                    @click="deleteItem(row)"-->
<!--                ></el-button>-->
<!--              </el-tooltip>-->
<!--            </el-button-group>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--      </el-table>-->

<!--      &lt;!&ndash; 分页控件 &ndash;&gt;-->
<!--      <div class="e-pages" style="margin-top: 10px; text-align: center">-->
<!--        <el-pagination-->
<!--            @current-change="loadList"-->
<!--            :current-page="page"-->
<!--            :page-size="pagesize"-->
<!--            @size-change="sizeChange"-->
<!--            layout="total, sizes, prev, pager, next, jumper"-->
<!--            :total="totalCount"-->
<!--        ></el-pagination>-->
<!--      </div>-->
<!--    </el-card>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import api from '@/api';-->
<!--import { remove } from '@/utils/utils';-->
<!--import { extend } from '@/utils/extend';-->
<!--import objectDiff from 'objectdiff';-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      loading: false,-->
<!--      list: [],-->
<!--      search: {-->
<!--        name: '',-->
<!--        phone: '',-->
<!--        level: '',-->
<!--        status: ''-->
<!--      },-->
<!--      total: {},-->
<!--      page: 1,-->
<!--      pagesize: 10,-->
<!--      totalCount: 0-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    searchSubmit() {-->
<!--      this.loadList(1);-->
<!--    },-->

<!--    resetSearch() {-->
<!--      this.search = {-->
<!--        name: '',-->
<!--        phone: '',-->
<!--        level: '',-->
<!--        status: ''-->
<!--      };-->
<!--      this.loadList(1);-->
<!--    },-->

<!--    sizeChange(e) {-->
<!--      this.pagesize = e;-->
<!--      this.loadList(1);-->
<!--    },-->

<!--    loadList(page) {-->
<!--      if (this.loading) return;-->

<!--      this.loading = true;-->
<!--      this.page = page;-->

<!--      // 构建查询参数-->
<!--      const filter = {-->
<!--        page: page,-->
<!--        pagesize: this.pagesize-->
<!--      };-->

<!--      // 添加搜索条件-->
<!--      if (this.search.name) filter.name = this.search.name;-->
<!--      if (this.search.phone) filter.phone = this.search.phone;-->
<!--      if (this.search.level) filter.level = this.search.level;-->
<!--      if (this.search.status) filter.status = this.search.status;-->

<!--      // 更新URL参数-->
<!--      const diff = objectDiff.diff(filter, this.$route.query);-->
<!--      if (diff.changed !== 'equal') {-->
<!--        this.$router.push({-->
<!--          path: this.$route.path,-->
<!--          query: filter-->
<!--        });-->
<!--      }-->

<!--      // // 调用API-->
<!--      // this.$post(api.daoyou.list, filter).then(res => {-->
<!--      //   this.loading = false;-->
<!--      //   if (res.code == api.code.OK) {-->
<!--      //     this.list = res.data.list;-->
<!--      //     this.totalCount = res.data.total;-->
<!--      //   } else {-->
<!--      //     this.$message.error(res.msg);-->
<!--      //   }-->
<!--      // }).catch(err => {-->
<!--      //   this.loading = false;-->
<!--      //   this.$message.error(err.message);-->
<!--      // });-->
<!--      // 在您的list.vue组件中，修改API响应处理-->
<!--      // this.$post(api.daoyou.list, filter).then(res => {-->
<!--      //   this.loading = false;-->
<!--      //   if (res.code == api.code.OK) {-->
<!--      //     // 确保正确访问嵌套的data属性-->
<!--      //     this.list = res.data.list; // 或者 res.data.data.list，取决于实际结构-->
<!--      //     this.totalCount = res.data.total;-->
<!--      //   } else {-->
<!--      //     this.$message.error(res.msg);-->
<!--      //   }-->
<!--      // }).catch(err => {-->
<!--      //   this.loading = false;-->
<!--      //   this.$message.error(err.message);-->
<!--      // });-->
<!--      // 在 list.vue 的 loadList 方法中-->
<!--      this.$post(api.daoyou.list, filter).then(res => {-->
<!--        this.loading = false;-->
<!--        console.log("API完整响应:", res); // 调试信息-->

<!--        // 根据实际响应结构调整-->
<!--        if (res.data.code === 200) {-->
<!--          // 注意：res.data 是响应体，res.data.data 是实际数据-->
<!--          this.list = res.data.data.list || [];-->
<!--          this.totalCount = res.data.data.total || 0;-->

<!--          // 检查是否有数据-->
<!--          if (this.list.length === 0) {-->
<!--            this.$message.info("未找到匹配的导游记录");-->
<!--          } else {-->
<!--            this.$message.success("查询成功"); // 显示成功提示-->
<!--          }-->
<!--        } else {-->
<!--          this.$message.error(res.data.msg || "查询失败");-->
<!--        }-->
<!--      }).catch(err => {-->
<!--        this.loading = false;-->
<!--        this.$message.error("请求失败: " + err.message);-->
<!--      });-->
<!--    },-->

<!--    deleteItem(row) {-->
<!--      this.$confirm('确定删除该导游信息？', '提示', {-->
<!--        type: 'warning'-->
<!--      }).then(() => {-->
<!--        this.loading = true;-->
<!--        this.$post(api.daoyou.delete, {-->
<!--          id: row.id-->
<!--        }).then(res => {-->
<!--          this.loading = false;-->
<!--          if (res.code != api.code.OK) {-->
<!--            this.$message.error(res.msg);-->
<!--          } else {-->
<!--            remove(this.list, row);-->
<!--            this.$message.success('删除成功');-->
<!--          }-->
<!--        }).catch(err => {-->
<!--          this.loading = false;-->
<!--          this.$message.error(err.message);-->
<!--        });-->
<!--      }).catch(() => {-->
<!--        // 取消删除-->
<!--      });-->
<!--    }-->
<!--  },-->

<!--  beforeRouteUpdate(to, from, next) {-->
<!--    // 从URL参数恢复搜索条件-->
<!--    if (to.query.name) this.search.name = to.query.name;-->
<!--    if (to.query.phone) this.search.phone = to.query.phone;-->
<!--    if (to.query.level) this.search.level = to.query.level;-->
<!--    if (to.query.status) this.search.status = to.query.status;-->

<!--    this.loadList(1);-->
<!--    next();-->
<!--  },-->

<!--  created() {-->
<!--    // 从URL参数恢复搜索条件-->
<!--    if (this.$route.query.name) this.search.name = this.$route.query.name;-->
<!--    if (this.$route.query.phone) this.search.phone = this.$route.query.phone;-->
<!--    if (this.$route.query.level) this.search.level = this.$route.query.level;-->
<!--    if (this.$route.query.status) this.search.status = this.$route.query.status;-->

<!--    this.loadList(1);-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.box-card {-->
<!--  margin: 20px;-->
<!--}-->

<!--.title {-->
<!--  font-size: 18px;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.form-search {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.e-pages {-->
<!--  margin-top: 20px;-->
<!--}-->
<!--</style>-->
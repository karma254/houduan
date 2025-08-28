<template>
  <div class="v-list" v-loading="loading" element-loading-text="加载中">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">路线列表</span>
        <div class="actions">
          <el-button type="primary" @click="$goto('/admin/luyouadd')" size="mini">新增路线</el-button>
        </div>
      </div>

      <!-- 搜索区域 -->
      <div class="form-search">
        <el-form @submit.prevent.stop :inline="true" size="mini">
          <el-form-item label="路线名称">
            <el-input v-model="search.name" placeholder="请输入路线名称" @keyup.enter="searchSubmit"></el-input>
          </el-form-item>

          <el-form-item label="难度">
            <el-select v-model="search.difficulty" placeholder="请选择难度" clearable>
              <el-option label="全部" value=""></el-option>
              <el-option label="简单" value="简单"></el-option>
              <el-option label="中等" value="中等"></el-option>
              <el-option label="困难" value="困难"></el-option>
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
        <el-table-column label="路线名称" prop="name" width="150"></el-table-column>
        <el-table-column label="难度" width="100">
          <template slot-scope="{row}">
            <el-tag :type="row.difficulty === '困难' ? 'danger' : (row.difficulty === '中等' ? 'warning' : 'success')" size="small">
              {{ row.difficulty }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="距离(km)" prop="distance" width="100"></el-table-column>
        <el-table-column label="预计时长" prop="duration" width="100"></el-table-column>
        <el-table-column label="创建时间" prop="createTime" width="150"></el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template slot-scope="{row}">
            <el-button-group>
              <el-tooltip content="编辑" placement="top">
                <el-button
                    @click="$goto({path:'/admin/luyouupdt', query:{id:row.id}})"
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
        difficulty: ''
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
        difficulty: ''
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

      const filter = {
        page: page,
        pagesize: this.pagesize
      };

      if (this.search.name) filter.name = this.search.name;
      if (this.search.difficulty) filter.difficulty = this.search.difficulty;

      const diff = objectDiff.diff(filter, this.$route.query);
      if (diff.changed !== 'equal') {
        this.$router.push({
          path: this.$route.path,
          query: filter
        });
      }

      // 调用路线查询API
      this.$post(api.luyou.page, filter).then(res => {
        this.loading = false;
        console.log("路线查询API响应:", res);

        if (res && res.code === 200) {
          this.list = res.data.list || [];
          this.totalCount = res.data.total || 0;
          this.$message.success(res.data.msg || "查询成功");
        } else {
          this.$message.error(res.msg || "查询失败");
        }
      }).catch(err => {
        this.loading = false;
        console.error("路线查询API请求错误:", err);
        this.$message.error("请求失败: " + (err.message || "未知错误"));
      });
    },

    deleteItem(row) {
      console.log('尝试删除路线:', row.id, row.name);

      this.$confirm(`确定删除路线 "${row.name}" 吗?`, '提示', {
        type: 'warning',
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(() => {
        this.loading = true;
        console.log('发送删除请求，参数:', { id: row.id });

        this.$post(api.luyou.delete, { id: row.id }).then(res => {
          this.loading = false;
          console.log('删除响应:', res);

          if (res.code === 200) {
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
  },

  beforeRouteUpdate(to, from, next) {
    if (to.query.name) this.search.name = to.query.name;
    if (to.query.difficulty) this.search.difficulty = to.query.difficulty;

    this.loadList(1);
    next();
  },

  created() {
    if (this.$route.query.name) this.search.name = this.$route.query.name;
    if (this.$route.query.difficulty) this.search.difficulty = this.$route.query.difficulty;

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
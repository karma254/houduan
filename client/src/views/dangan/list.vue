<template>
  <div class="v-list" v-loading="loading" element-loading-text="加载中">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">档案信息列表</span>
      </div>

      <!-- 搜索区域 -->
      <div class="form-search">
        <el-form @submit.prevent.stop :inline="true" size="mini">
          <el-form-item label="类别">
            <el-select v-model="search.leibie" placeholder="请选择类别">
              <el-option label="所有" value=""></el-option>
              <el-option label="导游" value="导游"></el-option>
              <el-option label="旅游团" value="旅游团"></el-option>
              <el-option label="饭店" value="饭店"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="关联">
            <el-input v-model="search.relativeId"></el-input>
          </el-form-item>

          <el-form-item label="信誉">
            <el-select v-model="search.xinyu" placeholder="请选择等级">
              <el-option label="所有" value=""></el-option>
              <el-option label="优秀" value="优秀"></el-option>
              <el-option label="良好" value="良好"></el-option>
              <el-option label="一般" value="一般"></el-option>
              <el-option label="较差" value="较差"></el-option>
              <el-option label="极差" value="极差"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="服务">
            <el-select v-model="search.fuwu" placeholder="请选择服务状况">
              <el-option label="所有" value=""></el-option>
              <el-option label="满意" value="满意"></el-option>
              <el-option label="基本满意" value="基本满意"></el-option>
              <el-option label="不满意" value="不满意"></el-option>
              <el-option label="极其不满意" value="极其不满意"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="searchSubmit" icon="el-icon-search">查询</el-button>
          </el-form-item>
        </el-form>
      </div>

      <!-- 数据表格 -->
      <el-table border :data="list" style="width: 100%" highlight-current-row>
        <el-table-column type="index" label="#"></el-table-column>

        <el-table-column label="类别">
          <template slot-scope="{row}">{{ row.leibie }}</template>
        </el-table-column>

        <el-table-column label="关联">
          <template slot-scope="{row}">{{ row.relativeId }}</template>
        </el-table-column>

        <el-table-column label="信誉">
          <template slot-scope="{row}">{{ row.xinyu }}</template>
        </el-table-column>

        <el-table-column label="服务">
          <template slot-scope="{row}">{{ row.fuwu }}</template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="{row}">
            <el-button-group>
              <el-tooltip content="详情" placement="top">
                <el-button @click="$goto({path:'/admin/dangandetail',query:{id:row.id } })" icon="el-icon-info" type="info" size="mini"></el-button>
              </el-tooltip>
              <el-tooltip content="编辑" placement="top">
                <el-button icon="el-icon-edit" @click="$goto({path:'/admin/danganupdt',query:{id:row.id } })" type="warning" size="mini"></el-button>
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <el-button icon="el-icon-delete" type="danger" size="mini" @click="deleteItem(row)"></el-button>
              </el-tooltip>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页控件 -->
      <div class="e-pages" style="margin-top: 10px;text-align: center">
        <el-pagination
            @current-change="loadList"
            :current-page="page"
            :page-size="pagesize"
            @size-change="sizeChange"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalCount">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<style type="text/scss" scoped lang="scss">
/* 可在此添加自定义样式 */
</style>

<script>
import api from '@/api';
import { remove } from '@/utils/utils';
import { extend } from '@/utils/extend';
import objectDiff from 'objectdiff';

/**
 * 后台档案列表页面，支持URL参数自动查询
 */
export default {
  data() {
    return {
      loading: false,
      list: [],
      search: {
        leibie: '',
        relativeId: '',
        xinyu: '',
        fuwu: '',
      },
      total: {},
      page: 1, // 当前页
      pagesize: 10, // 页大小
      totalCount: 0, // 总行数
      danganList: [],
    }
  },
  watch: {},
  computed: {},
  methods: {
    // 手动查询提交
    searchSubmit() {
      this.loadList(1);
    },

    // 分页大小改变
    sizeChange(e) {
      this.pagesize = e;
      this.loadList(1);
    },

    // 加载列表数据
    loadList(page) {
      // 防止重复加载
      if (this.loading) return;
      this.loading = true;
      this.page = page;

      // 构建筛选条件
      const filter = extend(true, {}, this.search, {
        page: page + "",
        pagesize: this.pagesize + ""
      });

      // 比较筛选条件与路由参数，不一致则更新URL
      const diff = objectDiff.diff(filter, this.$route.query);
      if (diff.changed !== 'equal') {
        this.$router.push({
          path: this.$route.path,
          query: filter
        });
      }

      // 发送请求获取数据
      this.$post(api.dangan.list, filter).then(res => {
        this.loading = false;
        if (res.code === api.code.OK) {
          extend(this, res.data);
        } else {
          this.$message.error(res.msg);
        }
      }).catch(err => {
        this.loading = false;
        this.$message.error(err.message);
      });
    },

    // 删除项目
    deleteItem(row) {
      this.$confirm('确定删除数据？', '提示', {
        type: 'warning'
      }).then(() => {
        this.loading = true;
        this.$post(api.dangan.delete, {
          id: row.id
        }).then(res => {
          this.loading = false;
          if (res.code !== api.code.OK) {
            this.$message.error(res.msg);
          } else {
            remove(this.list, row);
          }
        }).catch(err => {
          this.loading = false;
          this.$message.error(err.message);
        });
      }).catch(() => {
        // 取消删除操作
      });
    },

    // 从URL参数初始化查询条件
    initSearchFromParams() {
      const query = this.$route.query;

      // 处理类别和关联参数
      if (query.leibie) {
        this.search.leibie = query.leibie;
      }
      if (query.relativeId) {
        this.search.relativeId = query.relativeId;
      }

      // 处理其他查询参数
      if (query.xinyu) {
        this.search.xinyu = query.xinyu;
      }
      if (query.fuwu) {
        this.search.fuwu = query.fuwu;
      }

      // 处理分页参数
      if (query.page) {
        this.page = Math.floor(query.page);
      }
      if (query.pagesize) {
        this.pagesize = Math.floor(query.pagesize);
      }

      // 如果有类别或关联参数，自动执行查询
      if (query.leibie || query.relativeId) {
        this.loadList(this.page);
      }
      //没有则把类别设置为所有
      if (query.leibie==null && query.relativeId==null) {
        query.leibie = "所有"
        this.loadList(this.page);
      }
    }
  },
  // 路由参数更新时重新加载数据
  beforeRouteUpdate(to, from, next) {
    extend(this.search, to.query);
    this.loadList(1);
    next();
  },
  // 组件创建时初始化查询
  created() {
    this.initSearchFromParams();
  },
  mounted() {
  },
  destroyed() {
  }
}
</script>

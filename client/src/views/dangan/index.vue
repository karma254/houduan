<template>

  <div class="v-list" v-loading="loading" element-loading-text="加载中">

    <el-card class="box-card">
      <div slot="header" class="clearfix">
                <span class="title">
                档案信息列表
                </span>
      </div>
      <!-- 搜索 -->
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
            <el-input v-model="search.relativeId" ></el-input>
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


      <el-table border :data="list" style="width: 100%" highlight-current-row>

        <el-table-column label="类别">
          <template slot-scope="{row}">
            {{  row.leibie  }}
          </template>
        </el-table-column>


        <el-table-column label="关联">
          <template slot-scope="{row}">
            {{  row.relativeId  }}
          </template>
        </el-table-column>


        <el-table-column label="信誉">
          <template slot-scope="{row}">
            {{  row.xinyu  }}
          </template>
        </el-table-column>


        <el-table-column label="服务">
          <template slot-scope="{row}">
            {{  row.fuwu  }}
          </template>
        </el-table-column>



        <el-table-column label="操作">
          <template slot-scope="{row}">
            <el-button-group>

              <el-tooltip content="详情" placement="top">
                <el-button @click="$goto({path:'/dangandetail',query:{id:row.id } })" icon="el-icon-info" type="info" size="mini"></el-button>
              </el-tooltip>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table>



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
<style type="text/scss" lang="scss">
.v-list {
  .box-card {
    width: 80%;
    margin: 0 auto;
  }
}
</style>
<script>
import api from '@/api';
import { extend } from '@/utils/extend';
import objectDiff from 'objectdiff';


/**
 * 前台列表页面
 */
export default {
  data() {
    return {
      loading:false,
      list:[],
      search:{
        leibie:'',
        relativeId:'',
        xinyu:'',
        fuwu:'',
      },
      total:{},
      page:1, // 当前页
      pagesize:10, // 页大小
      totalCount:0, // 总行数
      danganList:[],
    }
  },
  watch: {},
  computed: {},
  methods: {
    searchSubmit(){
      this.loadList(1);
    },

    sizeChange(e){
      this.pagesize = e;
      this.loadList(1);
    },

    loadList( page ){
      // 防止重新点加载列表
      if(this.loading)return;
      this.loading = true;
      this.page = page;
      // 筛选条件
      var filter = extend(true, {}, this.search, {page:page+"", pagesize: this.pagesize+""});
      var diff = objectDiff.diff(filter, this.$route.query);
      if (diff.changed != 'equal') { // 筛选的条件不一致则更新链接
        this.$router.push({  // 更新query
          path: this.$route.path,
          query: filter
        });
      }
      this.$post(api.dangan.list , filter).then(res=>{
        this.loading = false;
        if(res.code == api.code.OK)
        {
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
  beforeRouteUpdate(to,form,next){
    extend(this.search , to.query)
    this.loadList(1);
    next();
  },
  created() {
    var search = extend(this.search , this.$route.query)
    if(search.page)
    {
      this.page = Math.floor(this.$route.query.page)
      delete search.page
    }
    if(search.pagesize)
    {
      this.pagesize = Math.floor(this.$route.query.pagesize)
      delete search.pagesize
    }

    this.loadList(1);
  },
  mounted() {
  },
  destroyed() {
  }
}
</script>

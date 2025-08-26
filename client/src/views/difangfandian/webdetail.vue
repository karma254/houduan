<template>
  <div class="difangfandian-detail" v-loading="loading">
    <div>
      <e-container>
        <div style="margin:10px 0 0 0">
          <e-module-model-box title="详情">



            <div class="">
              <div class="goods-info clearfix">
                <div class="gallery-list">
                  <e-shangpinban :images="map.tupian"></e-shangpinban>
                </div>
                <div class="goods-right-content">
                  <h3 class="title" v-text="map.mingcheng"></h3>
                  <div class="descount">
                    <div>
                      <span class="name">附近景点：</span>
                      <span class="val">{{ map.fujinjingdian }}</span>
                    </div>
                    <div>
                      <span class="name">分类：</span>
                      <span class="val">
                                                <e-select-view module="fandianfenlei" :value="map.fenlei" select="id" show="fenleimingcheng"></e-select-view>
                                            </span>
                    </div>
                    <div>
                      <span class="name">人均价格：</span>
                      <span class="val">{{ map.jiage }}</span>
                    </div>
                    <div>
                      <span class="name">饭店简介：</span>
                      <span class="val">{{ map.fandianjianjie }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div style="margin-bottom: 20px;margin-left: 12%">
              <el-button
                  type="primary"
                  @click="gotoDanganPage"
                  icon="el-icon-folder-opened">
                查看档案
              </el-button>
            </div>
          </e-module-model-box>
        </div>
      </e-container>
    </div>
  </div>
</template>

<style type="text/scss" scoped lang="scss">
/* 可以根据需要添加按钮样式 */
.el-button {
  margin-bottom: 15px;
}
</style>

<script>
import api from '@/api';
import { extend } from '@/utils/extend';

export default {
  data() {
    return {
      loading: false,   // 加载状态
      map: {
        fandianbianhao: '',
        mingcheng: '',
        fujinjingdian: '',
        fenlei: '',
        tupian: '',
        jiage: '',
        fandianjianjie: '',
        addtime: '',
      },
    }
  },
  props: {
    id: {
      type: [String, Number],
      required: true
    }
  },
  watch: {
    id: {
      handler() {
        this.loadDetail();
      },
      immediate: true
    },
  },
  computed: {},
  methods: {
    loadDetail() {
      if (this.loading) return;
      this.loading = true;
      this.$get(api.difangfandian.webdetail, {
        id: this.id
      }).then(res => {
        this.loading = false;
        if (res.code == api.code.OK) {
          extend(this, res.data);
        } else {
          this.$message.error(res.msg);
        }
      }).catch(err => {
        this.loading = false;
        this.$message.error(err.message);
      });
    },
    // 新增方法：跳转到档案页面并传递参数
    gotoDanganPage() {
      // 跳转到view/dangan/index.vue页面
      // 传递两个参数：leibie=饭店，relativeId=当前页面的id
      this.$router.push({
        path: '/dangan',
        query: {
          leibie: '饭店',
          relativeId: this.id
        }
      });
    }
  },
  created() {},
  mounted() {},
  destroyed() {}
}
</script>

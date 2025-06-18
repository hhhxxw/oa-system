<template>
  <div class="app-container home">
    <!-- 外层容器 -->
    <div style="display: flex; flex-direction: column; gap: 16px;">
      <!-- 第一行：2 个卡片 -->
      <div style="display: flex; gap: 16px;">
        <el-card style="width: calc(66.66% - 6px); min-width: 0;">
          <div style="font-size: 14px; color: #909399; margin-bottom: 10px;">通知公告</div>
          <div v-for="item in noticeList.slice(0, 3)" :key="item.id" style="font-size: 14px; margin-bottom: 8px;">
            {{ item.noticeTitle }}
            <span style="float: right">{{ item.createTime }}</span>
          </div>
        </el-card>
<!--        <span>这只是个测试</span>-->
<!--        <el-card style="flex: 1; min-width: 0;">-->
<!--          <div style="font-size: 14px; color: #909399; margin-bottom: 10px;">新闻头条 2</div>-->
<!--          <div v-for="item in notices.slice(2, 4)" :key="item.id" style="font-size: 14px; margin-bottom: 8px;">-->
<!--            {{ item.title }}-->
<!--            <span style="float: right">{{ item.time }}</span>-->
<!--          </div>-->
<!--        </el-card>-->
<!--        <el-card style="flex: 1; min-width: 0;">-->
<!--          <el-tabs v-model="activeName" @tab-click="handleClick">-->
<!--            <el-badge :value="12" style="margin-top: 10px; margin-right: 40px;">-->
<!--            <el-tab-pane label="待办" name="first">用户管理</el-tab-pane>-->
<!--            </el-badge>-->
<!--            <el-tab-pane label="待阅" name="second">配置管理</el-tab-pane>-->
<!--            <el-tab-pane label="已办" name="third">角色管理</el-tab-pane>-->
<!--          </el-tabs>-->
<!--        </el-card>-->
        <el-card style="flex: 1; min-width: 0;">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <!-- 待办标签（带数字提醒） -->
            <el-tab-pane name="first">
              <template #label>
                <el-badge :value="2" :max="99" class="tab-badge">
                  <span>待办</span>
                </el-badge>
              </template>
              待办内容...
            </el-tab-pane>

            <!-- 待阅标签（带数字提醒） -->
            <el-tab-pane name="second">
              <template #label>
                <el-badge :value="5" :max="99" class="tab-badge">
                  <span>待阅</span>
                </el-badge>
              </template>
              待阅内容...
            </el-tab-pane>

            <!-- 已办标签（不需要数字提醒） -->
            <el-tab-pane label="已办" name="third">已办内容...</el-tab-pane>

            <!-- 邮件标签（带数字提醒） -->
            <el-tab-pane name="fourth">
              <template #label>
                <el-badge :value="12" :max="99" class="tab-badge">
                  <span>邮件</span>
                </el-badge>
              </template>
              邮件内容...
            </el-tab-pane>
          </el-tabs>
        </el-card>
<!--        <el-tabs style="flex: 1; min-width: 0;" type="border-card">-->
<!--          <el-tab-pane>-->
<!--            <span slot="label"><i class="el-icon-date"></i> 我的行程</span>-->
<!--            我的行程-->
<!--          </el-tab-pane>-->
<!--          <el-tab-pane label="待办">消息中心</el-tab-pane>-->
<!--          <el-tab-pane label="待阅">角色管理</el-tab-pane>-->
<!--        </el-tabs>-->
      </div>

      <!-- 第二行：3 个卡片 -->
      <div style="display: flex; gap: 16px;">
        <el-card style="flex: 1; min-width: 0;">
          <div style="font-size: 14px; color: #909399; margin-bottom: 10px;">新闻公告</div>
          <div v-for="item in notices.slice(0, 3)" :key="item.id" style="font-size: 14px; margin-bottom: 8px;">
            {{ item.title }}
            <span style="float: right">{{ item.time }}</span>
          </div>
        </el-card>

        <el-card style="flex: 1; min-width: 0;">
          <el-carousel indicator-position="outside" height="100px">
            <el-carousel-item v-for="(item, index) in carouselImages" :key="index">
              <img
                  :src="item.url"
                  :alt="item.alt"
                  style="width: 100%; height: 100%; object-fit: cover;"
              >
            </el-carousel-item>
          </el-carousel>
        </el-card>

        <el-card style="flex: 1; min-width: 0;">
          <div style="font-size: 14px; color: #909399; margin-bottom: 10px;">新闻公告</div>
          <div v-for="item in notices.slice(0, 3)" :key="item.id" style="font-size: 14px; margin-bottom: 8px;">
            {{ item.title }}
            <span style="float: right">{{ item.time }}</span>
          </div>
        </el-card>
      </div>
    </div>
    <div style="display: flex; justify-content: space-between; padding: 20px;">
      <el-card style="width: 480px">
        <div style="font-size: 14px; color: #909399; margin-bottom: 10px;">新闻头条</div>
        <div v-for="item in notices" :key="item.id" style="font-size: 14px; margin-bottom: 8px;">
          {{item.title }}
          <span style="float: right">{{ item.time }}</span>
        </div>
      </el-card>

      <el-card style="width: 480px">
        <div style="font-size: 14px; color: #909399; margin-bottom: 10px;">新闻头条</div>
        <div v-for="item in notices" :key="item.id" style="font-size: 14px; margin-bottom: 8px;">
          {{item.title }}
          <span style="float: right">{{ item.time }}</span>
        </div>
      </el-card>
    </div>
<!--    <div>-->

<!--      <div style="display: flex; margin: 10px 0">-->
<!--        <div style="width: 50%;" class="card">-->
<!--          <div style="margin-bottom: 30px; font-size: 20px; font-weight: bold">公告列表</div>-->
<!--          <div>-->
<!--            <el-timeline reverse slot="reference">-->
<!--              <el-timeline-item v-for="item in noticeList" :key="item.id" :timestamp="item.time">-->
<!--                <span slot="reference">{{ item.title }}</span>-->
<!--                &lt;!&ndash;                <el-popover&ndash;&gt;-->
<!--                &lt;!&ndash;                    placement="right"&ndash;&gt;-->
<!--                &lt;!&ndash;                    width="200"&ndash;&gt;-->
<!--                &lt;!&ndash;                    trigger="hover"&ndash;&gt;-->
<!--                &lt;!&ndash;                    :content="item.content">&ndash;&gt;-->
<!--                &lt;!&ndash;                  <span slot="reference">{{ item.title }}</span>&ndash;&gt;-->
<!--                &lt;!&ndash;                </el-popover>&ndash;&gt;-->
<!--              </el-timeline-item>-->
<!--            </el-timeline>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->

    <el-row :gutter="300">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>联系信息</span>
            </div>
          </template>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 江苏天地图
              <el-link
                  href="http://www.ruoyi.vip"
                  target="_blank"
              ></el-link
              >
            </p>
          </div>
        </el-card>
      </el-col>


    </el-row>
  </div>
</template>

<script setup name="Index">
const version = ref('3.8.7')
const activate = ref('second')
const url=ref('https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg')
const srcList=ref("https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg")
const carouselImages = [
  {
    url: 'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
    alt: '图片1描述'
  },
  {
    url: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    alt: '图片2描述'
  },
  {
    url: 'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg',
    alt: '图片3描述'
  },
  {
    url: 'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg',
    alt: '图片4描述'
  }
]
const notices = ref([
  {
    id: 1,
    title: '系统升级通知',
    content: '系统将于2023-12-01 00:00至03:00进行升级维护',
    time: '2023-11-28 10:00'
  },
  {
    id: 2,
    title: '新功能上线',
    content: '新增用户反馈功能，欢迎使用',
    time: '2023-11-25 14:30'
  },
  {
    id: 3,
    title: '节假日安排',
    content: '元旦假期期间客服服务时间调整为9:00-18:00',
    time: '2023-11-20 09:15'
  }
])
const handleClick = (tab, event) => {
  console.log(tab, event)
}
function goTarget(url) {
  window.open(url, '__blank')
}
import { ref, onMounted } from 'vue'
import { listNotice } from '@/api/system/notice' // 引入API方法

// 原有其他变量定义保持不变...

// 新增noticeList数据
const noticeList = ref([])

// 获取通知列表
const fetchNoticeList = async () => {
  try {
    const response = await listNotice({}) // 可以传入查询参数
    noticeList.value = response.rows || []
  } catch (error) {
    console.error('获取通知列表失败:', error)
  }
}

// 组件挂载时调用
onMounted(() => {
  fetchNoticeList()
})
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
}
</style>


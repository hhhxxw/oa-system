<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考核标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入考核标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考核开始时间" prop="beginTime">
        <el-input
          v-model="queryParams.beginTime"
          placeholder="请输入考核开始时间"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考核结束时间" prop="endTime">
        <el-input
          v-model="queryParams.endTime"
          placeholder="请输入考核结束时间"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['oa:plan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['oa:plan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['oa:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['oa:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="考核标题" align="center" prop="title" />
      <el-table-column label="考核开始时间" align="center" prop="beginTime" />
      <el-table-column label="考核结束时间" align="center" prop="endTime" />
      <el-table-column label="考核规则" align="center" prop="kpiRule" />
      <el-table-column label="考核项Id" align="center" prop="itemId" />
      <el-table-column label="考核人" align="center" prop="chargeUser" />
      <el-table-column label="被考核人" align="center" prop="userRole" />
      <el-table-column label="被考核人" align="center" prop="deptRole" />
      <el-table-column label="被考核人" align="center" prop="levelRole" />
      <el-table-column label="相关附件" align="center" prop="attachId" />
      <el-table-column label="消息提醒方式" align="center" prop="msgType" />
      <el-table-column label="备注说明" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['oa:plan:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['oa:plan:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改考核计划对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="planRef" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="考核标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入考核标题" />
        </el-form-item>
        <el-form-item label="考核开始时间" prop="beginTime">
          <el-input v-model="form.beginTime" placeholder="请输入考核开始时间" />
        </el-form-item>
        <el-form-item label="考核结束时间" prop="endTime">
          <el-input v-model="form.endTime" placeholder="请输入考核结束时间" />
        </el-form-item>
        <el-form-item label="考核规则" prop="kpiRule">
          <el-input v-model="form.kpiRule" placeholder="请输入考核规则" />
        </el-form-item>
        <el-form-item label="考核项Id" prop="itemId">
          <el-input v-model="form.itemId" placeholder="请输入考核项Id" />
        </el-form-item>
        <el-form-item label="考核人" prop="chargeUser">
          <el-input v-model="form.chargeUser" placeholder="请输入考核人" />
        </el-form-item>
        <el-form-item label="被考核人" prop="userRole">
          <el-input v-model="form.userRole" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="被考核人" prop="deptRole">
          <el-input v-model="form.deptRole" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="被考核人" prop="levelRole">
          <el-input v-model="form.levelRole" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="相关附件" prop="attachId">
          <el-input v-model="form.attachId" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注说明" />
        </el-form-item>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Plan">
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/oa/plan"

const { proxy } = getCurrentInstance()

const planList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: null,
    beginTime: null,
    endTime: null,
    msgType: null,
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询考核计划列表 */
function getList() {
  loading.value = true
  listPlan(queryParams.value).then(response => {
    planList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    sortNo: null,
    planId: null,
    title: null,
    beginTime: null,
    endTime: null,
    kpiRule: null,
    itemId: null,
    chargeUser: null,
    userRole: null,
    deptRole: null,
    levelRole: null,
    attachId: null,
    msgType: null,
    remark: null,
    status: null,
    createUser: null,
    createTime: null,
    orgId: null
  }
  proxy.resetForm("planRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加考核计划"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getPlan(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改考核计划"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["planRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePlan(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addPlan(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除考核计划编号为"' + _ids + '"的数据项？').then(function() {
    return delPlan(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('oa/plan/export', {
    ...queryParams.value
  }, `plan_${new Date().getTime()}.xlsx`)
}

getList()
</script>

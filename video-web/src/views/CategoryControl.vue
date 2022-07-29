<template>
  <div class="container">
    <el-button type="primary" @click="showAddParentDialog">Add</el-button>
    <el-table
      class="table"
      align="center"
      v-loading="loading"
      :data="dataList"
      :header-cell-style="{ background: '#F5F5F5', color: '#000', fontSize: '16px' }"
      row-key="id"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column prop="name" label="Name" align="center" />
      <el-table-column prop="createTime" label="CreateTime" align="center">
        <template #default="scope">{{ parseTime(scope.row.createTime) }}</template>
      </el-table-column>
      <el-table-column prop="display" label="Display" align="center">
        <template #default="scope">
          <el-switch v-model="scope.row.display" :active-value="0" :inactive-value="1"
                     @change="handlerDisplayChange(scope.row)" />
        </template>
      </el-table-column>
      <el-table-column label="Operations" align="center">
        <template #default="scope">
          <el-button type="text" size="small" @click="showAddChildDialog(scope.row.id)">Add</el-button>
          <el-button type="text" size="small" @click="showEditDialog(scope.row)">Edit</el-button>
          <el-button type="text" size="small" @click="handleDelete(scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="dialogParam.visible" :title="dialogParam.title" width="50%">
      <el-form ref="dialogFormRef" :model="form" :rules="rules" label-width="60px">
        <el-form-item label="Name" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogParam.visible = false">Cancel</el-button>
          <el-button type="primary" @click="submitForm(dialogFormRef)">Confirm</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from "vue";
import axios from "../utils/axios";
import { ElForm, ElMessage, ElMessageBox } from "element-plus";
import parseTime from '../utils/dateTime'
import handleTree from '../utils/tree'

const dialogParam = reactive({
  visible: false,
  title: '',
  parentId: 0
})

const rules = reactive({
  name: [{ required: true, message: 'Please input Name', trigger: 'blur' }]
})

const form = reactive({
  id: 0,
  name: ''
})

type FormInstance = InstanceType<typeof ElForm>
const dialogFormRef = ref<FormInstance>()

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      if (form.id === 0) {
        handleAdd(dialogParam.parentId, form.name)
      } else {
        handleEdit(form)
      }
    }
  })
}

const showAddParentDialog = () => {
  dialogParam.visible = true
  dialogParam.title = 'Add'
  dialogParam.parentId = 0
  form.id = 0
  form.name = ''
}

const showAddChildDialog = (parentId) => {
  dialogParam.visible = true
  dialogParam.title = 'Add Child'
  dialogParam.parentId = parentId
  form.id = 0
  form.name = ''
}

const showEditDialog = (row) => {
  dialogParam.visible = true
  dialogParam.title = 'Edit'
  dialogParam.parentId = row.parentId
  form.id = row.id
  form.name = row.name
}

const handleAdd = (parentId, name) => {
  loading.value = true
  axios.post('system/category/create', { parentId, name }).then(res => {
    dialogParam.visible = false
    ElMessage.success(res.msg)
    getList()
  })
}

const handleEdit = (form) => {
  loading.value = true
  axios.put('system/category/update', form).then(res => {
    dialogParam.visible = false
    ElMessage.success(res.msg)
    getList()
  })
}

const handleDelete = (row) => {
  ElMessageBox.confirm(`Are you sure to delete ${row.name}?`)
    .then(() => {
      axios.delete('system/category/delete?id=' + row.id).then(res => {
        ElMessage.success(res.msg)
        getList()
      })
    })
    .catch(() => {
    })
}

const loading = ref(false)

const dataList = ref([])

const getList = () => {
  loading.value = true
  axios.get('system/category/list').then(res => {
    dataList.value = handleTree(res.data, "id")
    loading.value = false
  })
}

const handlerDisplayChange = (row) => {
  axios.put('system/category/update', row).then(res => {
    ElMessage.success('Update successfully')
  })
}

onMounted(() => {
  getList()
})

</script>

<style lang='less' scoped>
.container {
  padding: 10px 15px;

  .table {
    margin-top: 10px;
    width: 100%;
    font-weight: bold;
  }

  .dialog-footer button:first-child {
    margin-right: 10px;
  }

  .el-upload__tip {
    color: red;
  }
}
</style>

<template>
  <div class="container">
    <el-button type="primary" @click="showAddDialog">Upload</el-button>
    <el-button type="success" @click="categoryControlAction"
      >Category Control</el-button
    >
    <el-table
      class="table"
      align="center"
      v-loading="loading"
      :data="dataList"
      :header-cell-style="{
        background: '#F5F5F5',
        color: '#000',
        fontSize: '16px',
      }"
    >
      <el-table-column prop="title" label="Title" align="center" />
      <el-table-column prop="author" label="Author" align="center" />
      <el-table-column prop="categoryName" label="Category" align="center" />
      <el-table-column prop="display" label="Display" align="center">
        <template #default="scope">
          <el-switch
            v-model="scope.row.display"
            :active-value="0"
            :inactive-value="1"
            @change="handlerDisplayChange(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column prop="amountPlay" label="Views" align="center" />
      <el-table-column prop="createTime" label="UploadDate" align="center">
        <template #default="scope">{{
          parseTime(scope.row.createTime)
        }}</template>
      </el-table-column>
      <el-table-column label="Operations" align="center">
        <template #default="scope">
          <el-button type="text" size="small" @click="showEditDialog(scope.row)"
            >Edit</el-button
          >
          <el-button type="text" size="small" @click="handleDelete(scope.row)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      class="pagination"
      v-model:currentPage="queryParams.pageNo"
      v-model:page-size="queryParams.pageSize"
      :page-sizes="[10, 20, 30, 40, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="getList"
      @current-change="getList"
    />
    <el-dialog
      v-model="dialogVisible"
      :title="form.id === null ? 'Upload' : 'Edit'"
      width="50%"
      @open="onDialogOpen"
    >
      <el-form
        ref="dialogFormRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="Title" prop="title">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="Author" prop="author">
          <el-input v-model="form.author"></el-input>
        </el-form-item>
        <el-form-item label="Category" prop="categoryId">
          <el-select
            v-model="form.categoryId"
            placeholder="Please select Category"
            style="width: 100%"
          >
            <el-option
              v-for="item in categoryOptions"
              :key="item.id"
              :label="
                item.parentId === 0 ? item.name : '&emsp;&emsp;' + item.name
              "
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Poster" required>
          <el-upload
            ref="uploadRefPoster"
            :action="uploadUrl"
            :data="uploadPosterPostData"
            :limit="1"
            :before-upload="beforePosterUpload"
            :on-success="handlerPosterUploadSuccess"
            accept="image/*"
          >
            <el-button type="primary">Click to upload</el-button>
            <template #tip v-if="uploadPosterTipsVisible">
              <div class="el-upload__tip">Please upload file</div>
            </template>
          </el-upload>
          <!-- <el-image v-else :src="form.posterUrl" class="dialog-poster" /> -->
        </el-form-item>
        <el-form-item label="File" required>
          <el-upload
            ref="uploadRefVideo"
            :action="uploadUrl"
            :data="uploadVideoPostData"
            :limit="1"
            :before-upload="beforeVideoUpload"
            :on-success="handlerVideoUploadSuccess"
            accept="video/*"
          >
            <el-button type="primary">Click to upload</el-button>
            <template #tip v-if="uploadVideoTipsVisible">
              <div class="el-upload__tip">Please upload file</div>
            </template>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="submitForm(dialogFormRef)"
            >Confirm</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from "vue";
import { UploadFilled } from "@element-plus/icons-vue";
import type { ElForm, ElUpload } from "element-plus";
import axios from "../utils/axios";
import { ElMessage, ElMessageBox } from "element-plus";
import * as qiniu from "qiniu-js";
import uuid from "../utils/string";
import parseTime from "../utils/dateTime";
import { useRouter } from "vue-router";
import handleTree from "../utils/tree";

type FormInstance = InstanceType<typeof ElForm>;

const dialogFormRef = ref<FormInstance>();
const uploadRefPoster = ref<InstanceType<typeof ElUpload>>();
const uploadRefVideo = ref<InstanceType<typeof ElUpload>>();

const dialogVisible = ref(false);

interface CategoryListItem {
  id: number;
  parentId: number;
  name: string;
}

const categoryOptions = ref<CategoryListItem[]>([]);

const onDialogOpen = () => {
  uploadPosterTipsVisible.value = false;
  uploadVideoTipsVisible.value = false;

  axios.get("system/category/list").then((res) => {
    const list = handleTree(res.data, "id");
    const list2 = [];
    for (var i = 0; i < list.length; i++) {
      list2.push(list[i]);
      if (list[i].children) {
        for (var j = 0; j < list[i].children.length; j++) {
          list2.push(list[i].children[j]);
        }
      }
    }
    categoryOptions.value = list2;
  });

  axios.get("qiniu/token/get").then((res) => {
    uploadPosterPostData.token = res.data.token;
    uploadVideoPostData.token = res.data.token;
    getUploadUrl();
  });
};

const uploadUrl = ref("");

const getUploadUrl = () => {
  qiniu
    .getUploadUrl(
      {
        useCdnDomain: true,
        region: qiniu.region.na0,
      },
      uploadPosterPostData.token
    )
    .then((res) => {
      uploadUrl.value = res;
    });
};

const uploadPosterPostData = reactive({
  key: "",
  token: "",
});

const uploadVideoPostData = reactive({
  key: "",
  token: "",
});

const beforePosterUpload = (file) => {
  uploadPosterTipsVisible.value = false;
  let arr = file.name.split(".");
  uploadPosterPostData.key = "image/" + uuid() + "." + arr[arr.length - 1];
  return true;
};

const beforeVideoUpload = (file) => {
  uploadVideoTipsVisible.value = false;
  let arr = file.name.split(".");
  uploadVideoPostData.key = "video/" + uuid() + "." + arr[arr.length - 1];
  return true;
};

const handlerPosterUploadSuccess = (res) => {
  form.posterUrl = res.key;
};
const handlerVideoUploadSuccess = (res) => {
  form.videoUrl = res.key;
};

const form = reactive({
  id: null,
  title: "",
  author: "",
  categoryId: "",
  posterUrl: "",
  videoUrl: "",
});

const rules = reactive({
  title: [{ required: true, message: "Please input Title", trigger: "blur" }],
  author: [{ required: true, message: "Please input Author", trigger: "blur" }],
  categoryId: [
    { required: true, message: "Please select Category", trigger: "blur" },
  ],
});

const uploadPosterTipsVisible = ref(false);
const uploadVideoTipsVisible = ref(false);

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      if (!form.posterUrl) {
        uploadPosterTipsVisible.value = true;
        return false;
      }
      if (!form.videoUrl) {
        uploadVideoTipsVisible.value = true;
        return false;
      }
      if (form.id === null) {
        axios.post("system/video/create", form).then((res) => {
          uploadRefPoster.value.clearFiles();
          uploadRefVideo.value.clearFiles();
          dialogVisible.value = false;
          ElMessage.success(res.msg);
          getList();
        });
      } else {
        axios.put("system/video/update", form).then((res) => {
          dialogVisible.value = false;
          ElMessage.success("Update successfully");
          getList();
        });
      }
    } else {
      console.log("error submit!");
      return false;
    }
  });
};
const loading = ref(false);
const total = ref(0);

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
});

const dataList = ref([]);

const getList = () => {
  loading.value = true;
  axios.get("system/video/page", { params: queryParams }).then((res) => {
    total.value = res.data.total;
    dataList.value = res.data.list;
    loading.value = false;
  });
};

onMounted(() => {
  getList();
});

const handlerDisplayChange = (row) => {
  axios.put("system/video/update", row).then((res) => {
    ElMessage.success("Update successfully");
  });
};

const showAddDialog = () => {
  dialogVisible.value = true;
  form.id = null;
};

const showEditDialog = (row) => {
  dialogVisible.value = true;
  form.id = row.id;
  form.title = row.title;
  form.author = row.author;
  form.categoryId = row.categoryId;
  form.posterUrl = row.posterUrl;
  form.videoUrl = row.videoUrl;
};

const handleDelete = (row) => {
  ElMessageBox.confirm(`Are you sure to delete ${row.title}?`)
    .then(() => {
      axios.delete("system/video/delete?id=" + row.id).then((res) => {
        ElMessage.success(res.msg);
        getList();
      });
    })
    .catch(() => {});
};

const router = useRouter();
const categoryControlAction = () => {
  router.push("/categoryControl");
};
</script>

<style lang='less' scoped>
.container {
  padding: 10px 15px;

  .table {
    margin-top: 10px;
    width: 100%;
    font-weight: bold;
  }

  .pagination {
    margin-top: 10px;
  }

  .dialog-footer button:first-child {
    margin-right: 10px;
  }

  .el-upload__tip {
    color: red;
  }

  .dialog-poster {
    width: 50%;
    height: 120px;
  }
}
</style>
<template>
  <div class="header">
    <img class="top-img" src="@/assets/image/project-top-bg.jpeg" />
    <el-card class="top-card" shadow="always"
      >Welcome to Khanclinic Stay ahead, stay healthy</el-card
    >
  </div>
  <div class="category">
    <div class="category-title">
      A broad selection of videos of different category
    </div>
    <div
      class="category-item"
      v-for="category in categoryList"
      :key="category.id"
    >
      <HorizontalGallery :category="category" />
    </div>
  </div>
  <div class="bottom">
    <div class="bottom-item">
      <img
        class="bottom-item0-img"
        src="@/assets/image/app-header.jpg"
        alt="header"
      />
      <div class="bottom-item0-right">
        <div class="bottom-item0-right-title">About us</div>
        <div class="bottom-item0-right-desc">content need to be added</div>
      </div>
    </div>
    <div class="bottom-item">
      <div class="bottom-item1-left">
        <div class="bottom-item1-left-title">How to use</div>
        <div class="bottom-item1-left-desc">content need to be added</div>
      </div>
      <div class="bottom-item1-right">
        <img
          class="bottom-item1-right-img"
          src="@/assets/image/app-header.jpg"
          alt="header"
        />
      </div>
    </div>
    <div class="bottom-pdf">
      <div class="bottom-pdf-text">Please click the button to download</div>
      <a href="https://www.ieway.cn/evcapture.html">
        <el-button class="bottom-pdf-button" type="primary">Download</el-button>
      </a>
      <div class="bottom-pdf-text">
        1. How to download the software – EVCapture If you cannot jump to the
        page by clicking the download button, please use the link below to
        download it.
        <a href="https://www.ieway.cn/evcapture.html"
          >https://www.ieway.cn/evcapture.html</a
        >
      </div>
      <img
        class="bottom-pdf-image"
        src="@/assets/image/bottom-pdf0.png"
        alt="pdf"
      />
      <div class="bottom-pdf-text">
        e.g. Click the Windows version and begin installing the application
        using the executable. Please make sure to check the user agreement box
        and click install as follows,
      </div>
      <img
        class="bottom-pdf-image"
        src="@/assets/image/bottom-pdf1.png"
        alt="pdf"
      />
      <div class="bottom-pdf-text">2. How to use the software – EVCapture</div>
      <img
        class="bottom-pdf-image"
        src="@/assets/image/bottom-pdf2.png"
        alt="pdf"
      />
      <div class="bottom-pdf-text">
        • You will also have a few extra options on the drop-down box under
        ‘Select Video Source’, where you can more accurately choose which part
        of the screen you wish to record.
      </div>
      <img
        class="bottom-pdf-image"
        src="@/assets/image/bottom-pdf3.png"
        alt="pdf"
      />
      <div class="bottom-pdf-text">
        • Under the Tools area, the app allows you to add a variety of things
        like pictures and text onto the recorded screen.
      </div>
      <img
        class="bottom-pdf-image"
        src="@/assets/image/bottom-pdf4.png"
        alt="pdf"
      />
      <div class="bottom-pdf-text">
        • At the bottom of the Evcapture app is where the play/pause buttons are
        located. The duration of the recording and the voice and volume meters
        are located here as well.
      </div>
      <img
        class="bottom-pdf-image"
        src="@/assets/image/bottom-pdf5.png"
        alt="pdf"
      />
    </div>
  </div>
  <div class="footer">
    <div class="footer-top">
      <div class="footer-top-box1">
        <div class="footer-top-logo-box">
          <img class="footer-top-logo" src="@/assets/image/logo.jpeg" />
          <div class="footer-top-logo-text">content need to be added</div>
        </div>
        <div class="footer-top-logo-bottom">Khan Klinics</div>
      </div>
      <div class="footer-top-box2">
        <!-- <div class="footer-top-box-tiem">About us</div> -->
        <el-button
          type="text"
          class="footer-top-box-item"
          @click="dialogAboutVisible = true"
          >About us
        </el-button>
        <br />
        <el-button
          type="text"
          class="footer-top-box-item"
          @click="dialogContactVisible = true"
          >Contact us
        </el-button>
      </div>
      <div class="footer-top-box3">
        <el-button
          type="text"
          class="footer-top-box-item footer-top-box-item-first"
          @click="router.push('/submission-policy')"
          >Submission policy
        </el-button>
        <el-button
          type="text"
          class="footer-top-box-item"
          @click="router.push('/privacy-policy')"
          >Privacy policy
        </el-button>
        <el-button
          type="text"
          class="footer-top-box-item"
          @click="router.push('/policies-standards')"
          >Policies & standards
        </el-button>
      </div>
    </div>
    <div class="footer-bottom">Copyright © 2022 Khan Klinics</div>
  </div>
  <el-dialog v-model="dialogAboutVisible" title="About Us" width="40%" center>
    <div class="about-us">
      American Muslim Today (AMT) is a dynamic and inclusive forum which
      celebrates the diversity and immense contribution of Muslims in the West,
      as well empowering new generations of Muslim voices to write their own
      narrative and inspiring the leaders of tomorrow.
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogAboutVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogAboutVisible = false"
          >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="dialogContactVisible" title="Contact us" width="50%">
    <el-form
      ref="dialogContactFormRef"
      :model="formContact"
      :rules="rulesContact"
      label-width="100px"
    >
      <el-form-item label="Name" prop="name">
        <el-input v-model="formContact.name"></el-input>
      </el-form-item>
      <el-form-item label="Email" prop="email">
        <el-input v-model="formContact.email"></el-input>
      </el-form-item>
      <el-form-item label="Phone" prop="phone">
        <el-input v-model="formContact.phone"></el-input>
      </el-form-item>
      <el-form-item label="Address" prop="address">
        <el-input v-model="formContact.address"></el-input>
      </el-form-item>
      <el-form-item label="Your Query" prop="query">
        <el-input v-model="formContact.query" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogContactVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitForm(dialogContactFormRef)"
          >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { onMounted, ref, reactive } from "vue";
import HorizontalGallery from "@/components/HorizontalGallery.vue";
import axios from "@/utils/axios";
import { ElForm, ElMessage, ElMessageBox } from "element-plus";
import { useRouter } from "vue-router";

const categoryList = ref([]);

const getList = () => {
  axios.get("system/category/list-parent?parentId=0").then((res) => {
    categoryList.value = res.data;
  });
};

onMounted(() => {
  getList();
});

const dialogContactVisible = ref(false);
const dialogAboutVisible = ref(false);

const formContact = reactive({
  name: "",
  email: "",
  phone: "",
  address: "",
  query: "",
});

const rulesContact = reactive({
  name: [{ required: true, message: "Please input Name", trigger: "blur" }],
  email: [{ required: true, message: "Please input Email", trigger: "blur" }],
  phone: [{ required: true, message: "Please input Phone", trigger: "blur" }],
  address: [
    { required: true, message: "Please input Address", trigger: "blur" },
  ],
});

type FormInstance = InstanceType<typeof ElForm>;
const dialogContactFormRef = ref<FormInstance>();

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      axios.post("system/sys/contact", formContact).then((res) => {
        dialogContactVisible.value = false;
        ElMessage.success(res.msg);
      });
    }
  });
};

const router = useRouter();
</script>

<style lang="less" scoped>
.header {
  overflow: hidden;
  position: relative;

  .top-img {
    width: 100%;
    float: right;
  }

  .top-card {
    width: 40%;
    font-size: 50px;
    top: 30px;
    left: 60px;
    position: absolute;
  }
}

.category {
  padding: 10px 25px;

  .category-title {
    font-size: 35px;
    font-weight: 500;
  }

  .category-item {
    width: 100%;
  }
}

.bottom {
  margin: 50px 0 50px;

  .bottom-item {
    height: 200px;
    margin-top: 20px;
    text-align: center;
  }

  .bottom-item0-img {
    height: 100%;
  }

  .bottom-item0-right {
    float: right;
    width: 50%;
    text-align: left;
  }

  .bottom-item1-left {
    width: 50%;
  }

  .bottom-item1-right {
    width: 50%;
    height: 200px;
    float: right;
    text-align: center;

    .bottom-item1-right-img {
      height: 100%;
    }
  }
}

.bottom-pdf {
  margin: 50px 20px 50px;
  text-align: center;

  .bottom-pdf-text {
    text-align: left;
  }

  .bottom-pdf-button {
    margin-bottom: 20px;
  }
}

.footer {
  padding: 0 100px;
  background-color: #eee;

  .footer-top {
    display: flex;
    padding-top: 30px;

    .footer-top-box1 {
      flex: 3;

      .footer-top-logo-box {
        display: flex;

        .footer-top-logo {
          width: 200px;
          height: 200px;
        }

        .footer-top-logo-text {
          padding: 30px 0 0 30px;
          font-size: 26px;
        }
      }

      .footer-top-logo-bottom {
        font-size: 26px;
        margin-top: 20px;
      }
    }

    .footer-top-box2 {
      flex: 1;
      font-size: 22px;

      .footer-top-box-item {
        margin-bottom: 15px;
        color: black;
        font-size: 22px;
      }
    }

    .footer-top-box3 {
      flex: 1;
      font-size: 22px;

      .footer-top-box-item {
        margin-bottom: 15px;
        color: black;
        font-size: 22px;
      }

      .footer-top-box-item-first {
        margin-left: 12px;
      }
    }
  }

  .footer-bottom {
    text-align: center;
    margin-top: 50px;
    padding: 20px 0;
  }
}
.about-us {
  font-size: 20px;
}
</style>

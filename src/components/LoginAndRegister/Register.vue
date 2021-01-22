<template>
<div>
  <h2>注册</h2>
  <el-form
    class="form"
    :model="registerForm"
    :rules="registerRules"
    v-loading="registerLoading"
    ref="registerForm">
    <el-form-item class="form-item" label="用户名" prop="username">
      <el-input size="medium" v-model="registerForm.username"></el-input>
    </el-form-item>
    <el-form-item class="form-item" label="邮箱" prop="email">
      <el-input size="medium" v-model="registerForm.email"></el-input>
    </el-form-item>
    <el-form-item class="form-item" label="密码" prop="password">
      <el-input size="medium" type="password" v-model="registerForm.password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button class="submit-btn" size="medium" type="primary" @click="submit">提交注册</el-button>
      <el-button class="change-page-btn" size="mini" type="text" @click="changePage">已有账号，直接登录></el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
export default {
  name: 'Register',
  props: {
    page: String
  },
  data () {
    return {
      registerForm: {
        username: '',
        email: '',
        password: ''
      },
      registerRules: {
        username: [
          { required: true, message: '请填写用户名', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请填写邮箱地址', trigger: 'blur' },
          { pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '请正确填写邮箱地址', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请填写密码', trigger: 'blur' },
          { pattern: /^(?=.*[a-zA-Z])(?=.*\d)[^]{8,18}$/, message: '请确保密码包含数字和英文，长度8-18', trigger: 'blur' }
        ]
      },
      formName: 'registerForm',
      registerLoading: false
    }
  },
  methods: {
    changePage () {
      this.$refs[this.formName].resetFields()
      this.$emit('update:page', 'login')
    },
    submit () {
      this.$refs[this.formName].validate(valid => {
        if (valid) {
          this.registerLoading = true
          this.$axios
            .post('/register', this.registerForm)
            .then(response => {
              if (response.data.code === 200) {
                this.$message({
                  showClose: true,
                  message: response.data.message,
                  type: 'success',
                  duration: 1000,
                  offset: 60
                })
                this.changePage()
              } else {
                this.$message({
                  showClose: true,
                  message: response.data.message,
                  type: 'error',
                  duration: 1000,
                  offset: 60
                })
                this.$refs[this.formName].resetFields()
              }
              this.registerLoading = false
            })
            .catch(() => {
              this.$message({
                showClose: true,
                message: '服务器未响应',
                type: 'error',
                duration: 1000,
                offset: 60
              })
              this.registerLoading = false
            })
        } else {
          this.$message({
            showClose: true,
            message: '请正确填写表单',
            type: 'warning',
            duration: 1000,
            offset: 60
          })
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.form >>> .el-form-item__label {
  margin-bottom: -0.6rem;
}

.form-item {
  margin-bottom: 0.6rem;
}

.submit-btn {
  width: 100%;
  margin-top: 1rem;
}

.change-page-btn {
  float: right;
}

</style>

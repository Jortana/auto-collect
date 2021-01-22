<template>
<div>
  <h2>登录</h2>
  <el-form
    class="form"
    :model="loginForm"
    :hide-required-asterisk="true"
    :show-message="false"
    @keyup.enter.native="login"
    ref="loginForm">
    <el-form-item class="form-item" label="用户名" prop="username">
      <el-input size="medium" v-model="loginForm.username"></el-input>
    </el-form-item>
    <el-form-item class="form-item" label="密码" prop="password">
      <el-input size="medium" type="password" v-model="loginForm.password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-button class="form-btn" size="medium" @click="changePage">注册</el-button>
        </el-col>
        <el-col :span="12">
          <el-button class="form-btn" size="medium" type="primary" @click="login">登录</el-button>
        </el-col>
      </el-row>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
export default {
  name: 'Login',
  props: {
    page: String
  },
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      formName: 'loginForm'
    }
  },
  methods: {
    changePage () {
      this.$refs[this.formName].resetFields()
      this.$emit('update:page', 'register')
    },
    login () {
      let valid = this.loginForm.username !== '' && this.loginForm.password !== ''
      if (valid) {
        this.$axios
          .post('/login', this.loginForm)
          .then(response => {
            if (response.data.code === 200) {
              this.$message({
                showClose: true,
                message: response.data.message,
                type: 'success',
                duration: 1000,
                offset: 60
              })
              let path = this.$route.query.redirect
              console.log(path)
              this.$router.replace({
                path: (path === '/' || path === undefined) ? '/home' : path
              })
            } else {
              this.$message({
                showClose: true,
                message: response.data.message,
                type: 'error',
                duration: 1000,
                offset: 60
              })
            }
          })
          .catch(() => {
            this.$message({
              showClose: true,
              message: '服务器未响应',
              type: 'error',
              duration: 1000,
              offset: 60
            })
          })
      } else {
        this.$message({
          showClose: true,
          message: '请填写登录信息',
          type: 'warning',
          duration: 1000,
          offset: 60
        })
        return false
      }
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

.form-btn {
  width: 100%;
  margin-top: 1rem;
}
</style>

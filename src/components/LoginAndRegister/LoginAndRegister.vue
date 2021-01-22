<template>
<main>
  <!-- 内容区域 -->
  <el-row class="main-container">
    <el-col :xs="24" :md="12">content</el-col>
    <!-- 登录框和注册框 -->
    <el-col class="half-col" :xs="24" :md="12">
      <div class="form-container">
        <login v-show="page !== 'register'" :page.sync="page"></login>
        <register v-show="page === 'register'" :page.sync="page"></register>
      </div>
    </el-col>
  </el-row>
  <div class="hero-glow-container">
    <img class="hero-glow" src="../../assets/img/hero-glow.svg" alt="">
  </div>
</main>
</template>

<script>
import Login from '@/components/LoginAndRegister/Login'
import Register from '@/components/LoginAndRegister/Register'
export default {
  name: 'LoginAndRegister',
  created () {
  },
  watch: {
    $route: {
      handler (route) {
        this.page = route.path.slice(1)
      },
      immediate: true
    }
  },
  data () {
    return {
      page: ''
    }
  },
  components: {
    Login,
    Register
  }
}
</script>

<style scoped>
main {
  background-color: #040d21;
  height: 100vh;
  overflow: hidden;
}

.main-container {
  margin: 0 auto;
  max-width: 1280px;
}

.half-col {
  position: relative;
  height: 100vh;
}

/*@media only screen and (min-width: 992px) {*/
@media only screen and (min-width: 767px) {
  .main-container {
    width: 60%;
  }

  .form-container {
    width: 280px;
    max-width: 280px;
    left: 40%;
    right: 1rem;
  }
}

@media only screen and (max-width: 767px) {
  .main-container {
    width: 90%;
  }

  .form-container {
    width: 260px;
    max-width: 260px;
    left: 1rem;
    right: 1rem;
  }
}

.form-container {
  position: absolute;
  top: 20vh;
  z-index: 1;
  padding: 0 1.5rem 0.2rem 1.5rem;
  background-color: #fff;
  border-radius: 0.8rem;
}

.hero-glow {
  position: absolute;
  top: -50vh;
  left: 50vw;
  width: 200%;
  transform: translate(-50%,-50%);
  pointer-events: none;
  border-style: none;
}

.hero-glow-container {
  position: relative;
}
</style>

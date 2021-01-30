<template>
<div class="content">
  <div class="status">
    <h1 @click="addTask">添加收集</h1>
  </div>
  <div class="card">
    <el-form
      class="form"
      :model="taskForm"
      ref="taskForm"
      novalidate>
      <el-form-item class="unset" prop="taskName">
        <div class="form-item">
          <input v-model="taskForm.taskName" required />
          <label class="form-label">
            <span class="label-content">任务名称</span>
          </label>
        </div>
      </el-form-item>
      <el-form-item class="unset" prop="taskDescribe">
        <div class="form-item">
          <input v-model="taskForm.taskDescribe" required />
          <div class="form-label">
            <span class="label-content">任务描述（可选）</span>
          </div>
        </div>
      </el-form-item>
      <el-form-item class="unset" prop="exceptedNumber">
        <div class="form-item">
          <input v-model="taskForm.exceptedNumber" required />
          <div class="form-label">
            <span class="label-content">期望人数</span>
          </div>
        </div>
      </el-form-item>
      <el-form-item class="date-picker" prop="time">
        <el-date-picker
          class="full-width"
          v-model="taskForm.time"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="截止日期"
          format="yyyy-MM-dd HH:mm"
          value-format="timestamp">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <button class="submit" @click="addTask">
          <div v-if="submitLoading">
            <i class="el-icon-loading"></i>
          </div>
          <div v-else><span>提 交</span></div>
        </button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
export default {
  name: 'AddTask',
  data () {
    return {
      taskForm: {
        taskName: '',
        taskDescribe: '',
        time: '',
        exceptedNumber: ''
      },
      submitLoading: false
    }
  },
  methods: {
    addTask (e) {
      e.preventDefault()
      // 验证是否填写完成
      let formItem
      let valid = true
      for (formItem in this.taskForm) {
        if (formItem === 'taskDescribe') {
          continue
        }
        if (this.taskForm[formItem] === '') {
          this.$message({
            showClose: true,
            message: '请填写必要的信息',
            type: 'warning',
            duration: 1000,
            offset: 60
          })
          valid = false
          break
        } else if (formItem === 'exceptedNumber') {
          // 验证数字
          if (!/^[0-9]*$/.test(this.taskForm[formItem])) {
            this.$message({
              showClose: true,
              message: '期望人数请填写数字',
              type: 'warning',
              duration: 1000,
              offset: 60
            })
            valid = false
            break
          }
        }
      }
      if (valid) {
        this.submit()
      }
    },
    submit () {
      let { taskName, taskDescribe, exceptedNumber, time } = this.taskForm
      let startTime = time[0]
      let endTime = time[1]
      this.submitLoading = true
      this.$axios
        .post('/addTask', {
          taskName: taskName,
          taskDescribe: taskDescribe,
          exceptedNumber: exceptedNumber,
          startTime: startTime,
          endTime: endTime
        })
        .then(response => {
          console.log(response)
          this.submitLoading = false
        })
        .catch(() => {
          this.$message({
            showClose: true,
            message: '服务器未响应',
            type: 'error',
            duration: 1000,
            offset: 60
          })
          this.submitLoading = false
        })
    }
  }
}
</script>

<style scoped src="../../css/glass-common.css"></style>
<style scoped>
.unset >>> * {
  /*margin: 0;*/
  /*padding: 0;*/
  /*border: 0;*/
  font: inherit;
  font-size: 100%;
  /*vertical-align: baseline;*/
}

.date-picker {
  margin-top: 1rem;
  margin-bottom: 1rem;
}

.date-picker >>> input::placeholder {
  color: #426696;
}

.full-width {
  border: 1px solid #91b9e3;
}

.form {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  width: 100%;
}

.form-item {
  position: relative;
  height: 70px;
  overflow: hidden;
  margin-top: -0.3rem;
}

.form-item input {
  width: 100%;
  height: 100%;
  color: #595f6e;
  background: transparent;
  padding: 40px 0 0 0;
  border: none;
  outline: none;
  font-size: 1.2rem;
}

.form-label {
  /*text-align: left;*/
  color: #426696;
  font-size: 1.2rem;
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  border-bottom: 1px solid #6a97cf;
  /*transform: translateY(-100%);*/
}

.form-label::after {
  content: "";
  position: absolute;
  left: 0;
  bottom: -1px;
  height: 100%;
  width: 100%;
  border-bottom: 3px solid #5fa8d3;
  transform: translateX(-100%);
  transition: transform 0.3s ease;
}

.label-content {
  position: absolute;
  bottom: 0;
  left: 0;
  transition: all 0.3s ease;
}

.form-item input:focus + .form-label .label-content,
.form-item input:valid + .form-label .label-content {
  transform: translateY(-100%);
  font-size: 16px;
  color: #5fa8d3;
}

.form-item input:focus + .form-label::after,
.form-item input:valid + .form-label::after {
  transform: translateX(0);
}

.submit {
  width: 3rem;
  height: 3rem;
  border: none;
  outline: none;
  margin-top: 1rem;
  background: linear-gradient(to right top, #65dfc9, #6cdbeb);
  border-radius: 50%;
  color: white;
  box-shadow: 1px 1px 3px rgba(122, 122, 122, 0.212);
}

.submit:hover {
  background: linear-gradient(to right top, #72e7d0, #8ee5f5);
}

.submit:active {
  background: linear-gradient(to right top, #65dfc9, #6cdbeb);
}
</style>

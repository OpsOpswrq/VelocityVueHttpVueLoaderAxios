<template>
  <div class="body">
    <br/>
    <br/>
    <div class="weather-search">
      <input type="text" id="value" placeholder="请输入学生学号" v-model="sno"/>
      <button class="search" title="搜索位置" @click="searchSchoolRollOne">
        <img src="/assets/search.png" style="width: 30%">
      </button>
    </div>
    <div>
      <table>
        <caption>信息汇总表</caption>
        <thead>
        <tr>
          <td>表名</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>学生学籍表</td>
          <td><button @click="downloadSchoolRoll">下载数据</button><button @click="searchSchoolRoll">展示数据</button></td>
        </tr>
        <tr>
          <td>专业信息表</td>
          <td><button @click="downloadProfession">下载数据</button><button @click="searchProfession">展示数据</button></td>
        </tr>
        <tr>
          <td>课程信息表</td>
          <td><button @click="downloadCourse">下载数据</button><button @click="searchCourse">展示数据</button></td>
        </tr>
        <tr>
          <td>学生成绩表</td>
          <td><button @click="downloadCourseScore">下载数据</button><button @click="searchCourseScore">展示数据</button></td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>
      <table v-if="this.SchoolRoll.length !== 0">
        <caption>部分学生学籍表</caption>
        <thead>
        <tr>
          <td>学号</td>
          <td>姓名</td>
          <td>密码</td>
          <td>专业</td>
          <td>性别</td>
          <td>权限</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(value,key) in this.SchoolRoll" :key="key">
          <td style="font-size: 6px">{{value["sno"]}}</td>
          <td style="font-size: 6px">{{value["name"]}}</td>
          <td style="font-size: 6px">{{value["password"]}}</td>
          <td style="font-size: 6px">{{value["professionId"]}}</td>
          <td style="font-size: 6px">{{value["sex"]}}</td>
          <td style="font-size: 6px">{{value["auth"]}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>
      <table v-if="this.SchoolRollOne !== ''">
        <caption>查询的学生学籍表</caption>
        <thead>
        <tr>
          <td>学号</td>
          <td>姓名</td>
          <td>密码</td>
          <td>专业</td>
          <td>性别</td>
          <td>权限</td>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td style="font-size: 6px">{{this.SchoolRollOne["sno"]}}</td>
          <td style="font-size: 6px">{{this.SchoolRollOne["name"]}}</td>
          <td style="font-size: 6px">{{this.SchoolRollOne["password"]}}</td>
          <td style="font-size: 6px">{{this.SchoolRollOne["professionId"]}}</td>
          <td style="font-size: 6px">{{this.SchoolRollOne["sex"]}}</td>
          <td style="font-size: 6px">{{this.SchoolRollOne["auth"]}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>
      <table v-if="this.Profession.length !== 0">
        <caption>部分专业信息表</caption>
        <thead>
        <tr>
          <td>专业代码</td>
          <td>专业名称</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(value,key) in this.Profession" :key="key">
          <td style="font-size: 6px">{{value["pno"]}}</td>
          <td style="font-size: 6px">{{value["name"]}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>
      <table v-if="this.Course.length !== 0">
        <caption>部分课程信息表</caption>
        <thead>
        <tr>
          <td>课程代码</td>
          <td>课程名称</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(value,key) in this.Course" :key="key">
          <td style="font-size: 6px">{{value["cno"]}}</td>
          <td style="font-size: 6px">{{value["name"]}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div>
      <table v-if="this.CourseScore.length !== 0">
        <caption>部分学生成绩表</caption>
        <thead>
        <tr>
          <td>学号</td>
          <td>课程代码</td>
          <td>成绩</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(value,key) in this.CourseScore" :key="key">
          <td style="font-size: 6px">{{value["studentId"]}}</td>
          <td style="font-size: 6px">{{value["courseId"]}}</td>
          <td style="font-size: 6px">{{value["score"]}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
module.exports = {
  name:"App",
  data(){
    return {
      SchoolRoll:[],
      Profession:[],
      Course:[],
      CourseScore:[],
      SchoolRollOne:'',
      sno:"",
    }
  },
  methods:{
    searchSchoolRollOne(){
      axios({
        method:"GET",
        url:"/permitSearchStudent",
      }).then((res)=>{
        if(res.data.object){
          axios({
            method:"POST",
            url:"/searchSchoolRollOne",
            data:{
              sno:this.sno
            }
          }).then((res)=>{
            this.SchoolRollOne = res.data.object
          })
        }else{
          alert("你没有查询的权限")
        }
      })
    },
    downloadSchoolRoll(){
      axios({
        method:"POST",
        url:"/permitDownload",
      }).then((res)=>{
        if(res.data.object){
          axios({
            method:"POST",
            url:"/downloadSchoolRoll",
            responseType:'arraybuffer',
          }).then((res)=>{
            let blob = new Blob([res.data],{type:"application/octet-stream;charset=UTF-8",});
            let url = window.URL.createObjectURL(blob);
            let aLink = document.createElement("a");
            aLink.style.display = "none";
            aLink.download =res.headers["content-disposition"].split('=')[1];
            aLink.href = url;
            document.body.appendChild(aLink);
            aLink.click();
          })
        }else{
          alert("你没有下载数据的权限")
        }
      })
    },
    downloadProfession(){
      axios({
        method:"POST",
        url:"/permitDownload",
      }).then((res)=>{
        if(res.data.object){
          axios({
            method:"POST",
            url:"/downloadProfession",
            responseType:'arraybuffer',
          }).then((res)=>{
            let blob = new Blob([res.data],{type:"application/octet-stream;charset=UTF-8",});
            let url = window.URL.createObjectURL(blob);
            let aLink = document.createElement("a");
            aLink.style.display = "none";
            aLink.download =res.headers["content-disposition"].split('=')[1];
            aLink.href = url;
            document.body.appendChild(aLink);
            aLink.click();
          })
        }else{
          alert("你没有下载数据的权限")
        }
      })
    },
    downloadCourse(){
      axios({
        method:"POST",
        url:"/permitDownload",
      }).then((res)=>{
        if(res.data.object){
          axios({
            method:"POST",
            url:"/downloadCourse",
            responseType:'arraybuffer',
          }).then((res)=>{
            let blob = new Blob([res.data],{type:"application/octet-stream;charset=UTF-8",});
            let url = window.URL.createObjectURL(blob);
            let aLink = document.createElement("a");
            aLink.style.display = "none";
            aLink.download =res.headers["content-disposition"].split('=')[1];
            aLink.href = url;
            document.body.appendChild(aLink);
            aLink.click();
          })
        }else{
          alert("你没有下载数据的权限")
        }
      })

    },
    downloadCourseScore(){
      axios({
        method:"POST",
        url:"/permitDownload",
      }).then((res)=>{
        if(res.data.object){
          axios({
            method:"POST",
            url:"/downloadCourseScore",
            responseType:'arraybuffer',
          }).then((res)=>{
            let blob = new Blob([res.data],{type:"application/octet-stream;charset=UTF-8",});
            let url = window.URL.createObjectURL(blob);
            let aLink = document.createElement("a");
            aLink.style.display = "none";
            aLink.download =res.headers["content-disposition"].split('=')[1];
            aLink.href = url;
            document.body.appendChild(aLink);
            aLink.click();
          })
        }else{
          alert("你没有下载数据的权限")
        }
      })
    },
    searchSchoolRoll(){
      axios({
        method:"POST",
        url:"/permitSearch",
      }).then((res)=> {
        if (res.data.object) {
          axios({
            method: "POST",
            url: "/searchSchoolRoll",
            data:{
              start:0
            }
          }).then((res) => {
            this.SchoolRoll = res.data.object
          })
        } else {
          alert("你没有查询数据的权限")
        }
      })
    },
    searchCourse(){
      axios({
        method:"POST",
        url:"/permitSearch",
      }).then((res)=> {
        if (res.data.object) {
          axios({
            method: "POST",
            url: "/searchCourse",
            data:{
              start:0
            }
          }).then((res) => {

            this.Course = res.data.object
          })
        } else {
          alert("你没有查询数据的权限")
        }
      })
    },
    searchProfession(){
      axios({
        method:"POST",
        url:"/permitSearch",
      }).then((res)=> {
        if (res.data.object) {
          axios({
            method: "POST",
            url: "/searchProfession",
            data:{
              start:0
            }
          }).then((res) => {
            this.Profession = res.data.object
          })
        } else {
          alert("你没有查询数据的权限")
        }
      })
    },
    searchCourseScore(){
      axios({
        method:"POST",
        url:"/permitSearch",
      }).then((res)=> {
        if (res.data.object) {
          axios({
            method: "POST",
            url: "/searchCourseScore",
            data:{
              start:0
            }
          }).then((res) => {
            console.log(res.data.object)
            this.CourseScore = res.data.object
          })
        } else {
          alert("你没有查询数据的权限")
        }
      })
    },
  },
  beforeCreate() {
    axios({
      method:"GET",
      url:"/permitMain",
    }).then((res)=>{
      if(res.data.object){
        // axios({
        //   method:"POST",
        //   url:"/serialize",
        // }).then(()=>{
        //   console.log("Hello")
        // })
      }else{
        alert("你还没登录，请先登录")
        window.location.href = "http://localhost:7878/"
      }
    })
  }
}
</script>
<style scoped>
.body{
  min-height: 98vh;
  background-color: skyblue;
}
table{
  width: 100%;
  border-collapse: collapse;
  font-size: 20px;
}

table caption{
  font-size: 2em;
  font-weight: bold;
  margin: 1em 0;
}

th,td{
  border: 1px solid #999;
  text-align: center;
  padding: 20px 0;
}

table thead tr{
  background-color: #008c8c;
  color: #fff;
}

table tbody tr:nth-child(odd){
  background-color: #eee;
}

table tbody tr:hover{
  background-color: #ccc;
}

table tbody tr td:first-child{
  color: #f40;
}

table tfoot tr td{
  text-align: right;
  padding-right: 20px;
}
button{
  width: 150px;
  height: 50px;
  border: none;
  background-color: green;
  line-height: 50px;
  font-size: 20px;
  text-align: center;
  color: white;
  border-radius: 50px;
  margin-left: 20px;
  margin-right: 20px;
}
.weather-search{
  display:flex;
  width:320px;
  height:32px;
  border:1px solid white;
  border-radius:30px;
  justify-content:space-between;
  align-items:center;
  position:relative;
}

.weather-search input:-webkit-autofill , textarea:-webkit-autofill, select:-webkit-autofill {
  -webkit-text-fill-color: #000 !important;
  -webkit-box-shadow: 0 0 0px 1000px transparent inset !important;
  background-color: transparent;
  background-image: none;
  transition: background-color 50000s ease-in-out 0s;
}
.weather-search input{
  width:270px;
  height:32px;
  border:0;
  font-size:14px;
  outline:none;
  margin:0 10px;
  background-color:transparent;
}

.search{
  border:none;
  outline:none;
  cursor:pointer;
  background:inherit;
  min-width:16px;
}

.search span{
  display:flex;
  justify-content:center;
  align-items:center;
  width:100%;
  height:100%;
  outline:none;
}
</style>
<template>
  <view class="post-creation">
	  
	  
    <form @submit="submitForm" class="newPostShow">
      <input v-model="form.title" type="text" placeholder="标题"  
	  style="border: 1px solid #ccc; padding-left: 5px; margin: 5px; font-size: 30px; margin-bottom: 15px;"/>
      <textarea v-model="form.content" placeholder="正文"
	  style="border: 1px solid #ccc; padding-left: 5px; margin: 5px; font-size: 25px; "></textarea>
      <!-- <input type="file" @change="handleFileUpload" accept="image/*" /> -->
	  <!-- <input v-model="form.imageUrls" maxlength="1000" placeholder="图片url"/> -->
	  <button class="fab-button " @click="toAddImage">
	        <image class="fab-icon" src="../../static/plus.png"></image>
	  </button>
      <button type="submit" @click="submitForm" class="buttonPosition styleButton">发布</button>
    </form>
	
	<view class="previewImage" v-show="addImageNum" >
		 <image v-for="(item,index) in imageUploadedUrls" :key="index"
		  :src="item" mode="heightFix" v-show="item"
		  style="height: 80px;"></image>
	</view>
	
	
	<view v-if="showAddImage" class="addImage">
		<input v-model="newImgUrl" type="text" placeholder="请输入图片URL" style="font-size: 17px;"
		 class="inputUrl">
		 <text class="prompt">对应宠物:</text>
		<picker v-model="petType" mode="selector" :range="petTypes" style="font-size: 25px ;border: 1px solid #ccc;"
		class="pickerStyle" @change="changePet">
		 <view class="picker" >
		   {{ petTypes[petType] }}
		 </view>
		<image src="../../static/triangle.png" class="toggleIcon"></image>
		</picker>
		<button type="default" class="assertButton" style="right: 110px;"
		@click="cancelAddImage">取消</button>
		<button class="assertButton assert " @click="addNewImage">添加</button>
	</view>
	
	
  </view>
</template>

<script>
export default {
  data() {
    return {
	  addImageNum:0,
	  username:'',
	  form: {
		userId:undefined,
		title: '',
		content: '',
		imageUrls:[],
		petIds:[String],
		petNames:[],
		price: 100
		},
      title: '',
      content: '',
      file: null,
	  imageUrls:'',
	  imageUploadedUrls:[],
	  imagePetTypes:[],
	  imagePetIds:[],
	  newImgUrl:'',
	  showAddImage:false,
	  petType: 1, // 存储宠物种类
	  petTypes: [] ,// 可供选择的宠物种类
	  petList:[],
	  petIdList:[String]
    };
  },
  onLoad() {
	this.form.userId = uni.getStorageSync('userid');
	//console.log('发帖时的用户id为:'+this.form.userId);
  	this.username = uni.getStorageSync('username');
	//console.log('发帖时的username:'+this.username);
	this.getMyPets();
  },
  methods: {
	  addNewImage(){
		  console.log("正在添加图片")
		  if (this.addImageNum > 8)
		  {
			  uni.showToast({
			  	icon:'error',
				title:'超过最大添加图片数量！',
			  })
			  this.showAddImage = false
			  return
		  }
		  this.imageUploadedUrls.push(this.newImgUrl);
		  this.imagePetTypes.push(this.petTypes[this.petType]);
		  this.imagePetIds.push(this.petIdList[this.petType]);
		  this.showAddImage = false;
		  this.addImageNum +=1;
	  },
	  getMyPets()
	  {
		  uni.request({
		    url: 'http://localhost:8080/pet/getByOwnerId/'+this.form.userId,
		  	method:'GET',
		  	header:{
		  		'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
		  	},
		    success: (res) => {
		      // 请求成功，将数据保存到petList中
		  			this.petList = res.data;
					this.petTypes = this.petList.map(item => item.name);
					this.petIdList = this.petList.map(item => item.petId)
		  			// console.log(res)  				   
		    },
		    fail: (err) => {
				console.log(err)
		    }
		  });
	  },
	cancelAddImage()
	{
		this.showAddImage = false;
	},
	changePet(event)
	{
		  this.petType = event.mp.detail.value; 
	},
	toAddImage(){
		this.showAddImage = true;
	},
    handleFileUpload(e) {
      // 获取用户上传的图片文件
      this.file = e.target.files[0];
    },
    submitForm() {
		// console.log(this.form)
	  if(this.form.title =='')
	  {uni.showToast({icon:'error',	title:'标题不能为空'  })
		  return}
	  if(this.form.content =='')
	  { uni.showToast({icon:'error',title:'正文不能为空'})
		  return }
	  if (this.addImageNum < 1)
	  {uni.showToast({icon:'error',title:'至少添加一张图片！',})
	  	  return}
		
		this.form.imageUrls = this.imageUploadedUrls;
		// this.form.petIds = this.imagePetTypes;
		this.form.petIds = this.imagePetIds;
      uni.request({
        url: 'http://localhost:8080/posts/create',
        method: 'POST',
        data: this.form,
		header:{
			'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
		},
        success: (res) => {
			uni.showToast({
				title:res.data,
				icon:'success'
			})
			uni.navigateTo({
				url:"/pages/find/find"
			})
          // 请求成功的处理逻辑
          // 例如显示发布成功提示，跳转到帖子详情页等
        },
        fail: (err) => {
          // 请求失败的处理逻辑
			uni.showToast({
				icon:"",
				title:"发帖失败"
			})
        }
      });
    }
  }
};
</script>

<style>
	.previewImage{
		display:  flex;
		/* flex-direction: column; */
		justify-content: space-around;
		flex-wrap: wrap;
		margin: 5px;
		padding: 2px;
	}
	
	.toggleIcon{
		width: 25px;
		height: 25px;
		position:fixed;
		top:370px;
		right: 20px;
	}
	
	.prompt{
		background-color: aliceblue;
		position: fixed;
		top: 360px;
		font-size: 25px;
		width: 450px;
		height: 45px;
		border: 1px solid #ccc;
		/* left: 20px; */
	}
	.pickerStyle{
		position: fixed;
		top: 400px;
		width: 400px;
		height: 40px;
		font-size: 24px;
		align-content: center;
		display: flex;
		justify-content: center;
		padding-left: 14px;
		padding-right: 14px;
		background-color: aliceblue;
	}
	
	.inputUrl{
		position: fixed;
		top: 290px;
		width: 400px;
		height: 45px;
		padding: 14px;
		background-color: aliceblue;
	}
	
	.assert{
		background-color: #ff557f;
		right: 20px;
	}
	.assertButton{
		display: flex;
		position:fixed;
		justify-content: center;
		font-size: 20px;
		width: 80px;
		height: 45px;
		bottom: 300px;
	}
	.addImage{
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: 750; /* 设置一个较高的 z-index 值 */
		background-color: rgba(198, 198, 198, 0.5); /* 添加半透明背景，以突出显示筛选框 */
	}
	
	.buttonPosition{
		margin-bottom: 30px;
	}
	
	.newPostShow{
		height: 500px;
	}
	
	.fab-button {
	  /* position: fixed; */
	  /* bottom: 130px;*/
	  margin: 0;
	  right: 10px; 
	  width: 100px;
	  background-color: #ffffff; /* 设置按钮的背景颜色 */
	  display: flex;
	  align-items: left;
	  justify-content: left;
	  z-index: 700; /* 设置一个较高的 z-index 值，确保按钮在其他元素上方 */
	  padding: 10px;
	}
	
	.fab-icon {
	  width: 80px;
	  height: 80px;
	}
	
    .post-creation {
	  margin-top:15px;
	  height:250px;
	  padding-bottom: 10px;
	}

form {
  display: flex;
  flex-direction: column;
}

input{
	height: 40px;
	width: 408px;
	margin-bottom: 10px;
}
textarea {
  margin-bottom: 10px;
  /* padding: 10px; */
  width: 408px;
  height: 250px;
}

/* input[type="file"] {
  margin-bottom: 10px;
} */

.styleButton {
  background-color: #ff557f;
  color: #fff;
  padding: 10px;
  border: none;
  border-radius: 4px;
}
</style>
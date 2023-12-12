<template>
	<view @click="cancelPhotoChange" style="height: 800px;">
		<view class="personalInfo">
			<view class="">
				<!-- https://cdn0.iconfinder.com/data/icons/multimedia-solid-30px/30/user_account_profile-64.png -->
				<image :src="avatarUrl" class="user_photo avatar-border"
				@click="toggleDropdown"></image>
			</view>
			
			 <view class="dropdown" v-show="showDropdown">
<!-- 			       <text class="option" @click="viewAvatar">查看头像</text> -->
			       <text class="option" @click="changeAvatar">更改头像</text>
			</view>
			<view style="margin-top: 30px; margin-left: 20px; 
			font-size: 25px;" >用户：{{username}}</view>
		</view >
		  <view class="icon-container" >
		    <view class="icon-item" @click="goToMyOrders">
		      <image class="icon" src="../../static/order.png"></image>
		      <text class="icon-label">我的订单</text>
		    </view>
		    <view class="icon-item" @click="goToMyCollections">
		      <image class="icon" src="../../static/mark.png"></image>
		      <text class="icon-label">我的收藏</text>
		    </view>
		    <view class="icon-item" @click="goToMyPosts">
		      <image class="icon" src="../../static/myPost.png"></image>
		      <text class="icon-label">我的帖子</text>
		    </view>
		    <view class="icon-item" @click="goToMyPets">
		      <image class="icon" src="../../static/animal.png"></image>
		      <text class="icon-label">我的宠物</text>
		    </view>
		  </view>
		  <!-- <image src="file:///C:/Users/17310/Desktop/强弱链.png"></image> -->
		  <!-- 不能直接访问电脑的地址 -->
	</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				defaultAvatarUrl:"https://cdn0.iconfinder.com/data/icons/multimedia-solid-30px/30/user_account_profile-64.png",
				avatarUrl:undefined,
				imgPath:undefined,
				userid:undefined,
				username:'',
				showDropdown: false,
				ifNeedDelay:false,//防止下滑框被快速取消
			}
		},
		onShow() {
			
			
		},
		onLoad() {
			this.username = uni.getStorageSync('username');
			this.userid = uni.getStorageSync('userid');
			this.getAvatarUrl();		
		},
		methods: {
			delay(ms) {
			  return new Promise(resolve => setTimeout(resolve, ms));
			},
			async mydelay() {
			  await this.delay(500); // 在这里设置延迟的时间，单位为毫秒
			  console.log("等待结束，延迟执行的代码");
			  if (this.avatarUrl==undefined)
			  {
				  this.avatarUrl =this.defaultAvatarUrl;
			  }		  
			  console.log("当前显示的头像url",this.avatarUrl)
			},
			getAvatarUrl(){
				uni.request({
				url: 'http://localhost:8080/user/id/'+this.userid,
				  method:'GET',
				  header:{'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)},
				  success: (res) => {
					  console.log("返回的头像信息",res)
						this.avatarUrl =  res.data.profilePictureUrl;
						console.log(this.avatarUrl)
				  },
				  fail: (err) => {
				  }
				});
				this.mydelay();
			},
			goToMyOrders()
			{
				uni.navigateTo({
					url:"/pages/myOrders/myOrders"
				})
			},
			goToMyCollections()
			{
				uni.navigateTo({
					url:"/pages/myCollections/myCollections"
				})
			},
			goToMyPosts()
			{
				uni.navigateTo({
					url:"/pages/myPosts/myPosts"
				})
			},
			goToMyPets()
			{
				uni.navigateTo({
					url:"/pages/myPets/myPets"
				})
			},
			delay(ms) {
			  return new Promise(resolve => setTimeout(resolve, ms));
			},
			async myFunction() {
			  await this.delay(500); // 在这里设置延迟的时间，单位为毫秒
			  console.log("等待结束，延迟执行的代码");
			  this.ifNeedDelay = false;
			},
			cancelPhotoChange(){
				if (!this.ifNeedDelay)
				this.showDropdown = false;
			},
			toggleDropdown() {
			      this.showDropdown = !this.showDropdown;
				  if (!this.ifNeedDelay){
					  this.ifNeedDelay =true;
					  this.myFunction();
				  }
			    },
			viewAvatar() {
			// 查看头像
			},
			changeAvatar() {
			// 更改头像
			
			 uni.showModal({
			        title: '更改头像',
			        content: '请输入头像URL',
					editable:true,
					//placeholderText:"在此输入图片URL",
			        success: (res) => {
			          if (res.confirm) {
						 const newAvatarUrl = res.content; 
						 console.log(res.content)
						 const photo=newAvatarUrl;
						 console.log(photo)
						 const username =this.username
						  uni.request({
						  	url:'http://localhost:8080/user/photo/'+this.username,
							method:'POST',
							data: photo,
							header:{
								'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
							},
							success: (res) => {
								console.log('更新头像的返回信息：',res)
							}
						  })	            
			            // 更新头像
						this.avatarUrl = newAvatarUrl
			          }
			        },
			      });
			
			}
		}
	}
	
</script>

<style>
	
	.dropdown {
	  position: absolute;
	  top: 40px;
	  left: 30px;
	  width: 66px;
	  background-color: #ffffff;
	  border-radius: 5px;
	  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
	  padding: 10px;
	  /* display: none; */
	}
	
	.option {
	  margin-bottom: 10px;
	  cursor: pointer;
	}
	
	.icon-container {
	  border: 2px solid #d8d8d8;
	  border-radius: 5%;
	  padding: 10px;
	  margin-top: 20px;
	  margin-left: 15px;
	  margin-right: 15px;
	  display: flex;
	  justify-content: space-between;
	}
	
	.icon-item {
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	}
	
	.icon {
	  width: 50px;
	  height: 50px;
	}
	
	.icon-label {
	  margin-top: 10px;
	  font-size: 18px;
	  text-align: center;
	}
	
	.personalInfo{
		top: 20px;
		margin-top: 20px;
		margin-left: 15px;
		margin-right: 15px;
		padding-bottom: 5px;
		display: flex;
		align-items: flex-start;
		border-bottom: 2px dashed #d8d8d8;
		/* flex-wrap: nowrap; */
		/* flex-direction: row; */
		
	}
	
	.user_photo{
		  width: 100px;
		  height: 100px;
		  border-radius: 50%;
	}
	.avatar-border {
	  border: 2px solid #d8d8d8; /* 添加灰色边框 */
	  box-shadow: 0 0 5px rgba(0, 0, 0, 0.3); /* 添加阴影效果 */
	}
</style>

<template>
	<view>
		<!-- 展示自己的发帖 -->
		<view class="plstyle">
		   <scroll-view scroll-y="true" v-for="pair in postPairs" class="image-container">
		     <view v-for="item in pair" @click="checkDetail(item.postId)" class="image-row">
		       <image :src="item.imageUrls[0]"  
						class="image-item">	
		       </image>
		       <view class="text-container">
		         <text>{{item.title}}</text>
		         <!-- <text>预计价格：{{item.price}}</text> -->
		       </view>  
		     </view>
		   </scroll-view>
		 </view>
		
		<!-- 跳转到发布新帖子 -->
		<button class="fab-button" @click="goToNewPostPage">
		      <image class="fab-icon" src="../../static/add.png"></image>
		    </button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				postList:[],
				postPairs:[],
				username:'',
				userid:''
			}
		},
		onLoad() {
			this.userid = uni.getStorageSync('userid');
			this.username = uni.getStorageSync('username');
			this.fetchPostList();
		},
		methods: {
			goToNewPostPage(){
				uni.navigateTo({
					url:"/pages/newPost/newPost"
				})
			},
			checkDetail(id)
			{
				uni.navigateTo({
					url: '/pages/detail/detail?postId='+id
				})
			},
			fetchPostList() {
			 uni.request({
			   url: 'http://localhost:8080/posts/getByUserId/'+this.userid,
			 		method:'GET',
			 		header:{
			 			'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
			 		},
			   success: (res) => {
			     // 请求成功，将数据保存到imageList中
			 		  console.log("我的帖子：",res)
			     this.postList = res.data;
			 		  //console.log(this.imageList)
			 		   this.postPairs = [];
			 		    for (let i = 0; i < this.postList.length; i += 2) {
			 		    this.postPairs.push(this.postList.slice(i, i + 2));
			 			 }
			   },
			   fail: (err) => {
			 			console.log(err)
			     // 请求失败的处理逻辑
			   }
			 });
			}
		}
	}
</script>

<style>
	.plstyle{
		/* display: flex; */
		justify-content: space-between;
	}
	
	.image-container {
	   display: flex;
	   
	  /* flex-wrap: wrap; */
	  flex-direction: row; 
	  justify-content: space-between;
		flex: 1; 
	  padding: 5px;
	  
	}
	
	.container {
	  position: bottom
	
	}
	
	.image-row {
		display: inline-block;
		width: 48%;
		margin-bottom: 10px;
		padding: 2px;
		box-shadow: 2px 2px 5px rgba(255, 0, 0, 0.2);
	  /* display: flex; */
	  /*  align-items: flex; */
	   justify-content: space-between; 
	  /* flex-direction: column; */
	}
	
	.image-item {
	  width: 100%; 
	  /* height: auto; */
	  /* object-fit: cover; */
	  
	  box-sizing: border-box; /* 防止添加 padding 导致超出容器宽度 */
	  position: relative;
	  margin-bottom: 10px;
	}
	
	.text-container {
	  display: flex;
	  flex-direction: column; /* 将文本垂直排列 */
	  align-items: center;
	}
	
	.fab-button {
	  position: fixed;
	  bottom: 60px;
	  right: 10px;
	  border-radius: 50%; 
	  background-color: #ffffff; /* 设置按钮的背景颜色 */
	  display: flex;
	  align-items: center;
	  justify-content: center;
	  z-index: 999; /* 设置一个较高的 z-index 值，确保按钮在其他元素上方 */
	}
	
	.fab-icon {
	  width: 80px;
	  height: 80px;
	}
</style>

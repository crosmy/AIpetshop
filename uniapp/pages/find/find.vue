<template>
  <view >
	<!-- 搜索栏容器 -->
    <view class="search-bar">
      <input type="text" v-model="keyword" placeholder="输入关键词">
		<button @click="cancelFilter" type="default" style="border: 5px; margin-right: 3px;">筛选</button>
		<view v-if="showFilter" class="search-overlay">
			<view  class="search-dropdown">
	        <radio-group>
				<radio  style="font-size: 23px;"  v-for="option in filterOptions"
				 :key="option.id" :value="option.value"
				  @click="myFilter(option.value)">
	          {{ option.label }}
				</radio>
	        </radio-group>
			<!-- <button @click="myFilter" style="width: 20%;">确定</button>  v-model="selectedOption"-->
			</view>
		</view>
		
		<button @click="search">搜索</button>
    </view>
	
	   <view class="plstyle">
	      <scroll-view scroll-y="true" v-for="pair in imagePairs" class="image-container">
	        <view v-for="item in pair" @click="checkDetail(item.postId)" class="image-row">
	          <image :src="item.imageUrls[0]"  
				class="image-item">	
	          </image>
	          <view class="text-container">
				  <view  @click="goToUserDetail(userIds[item.postId])">
						<image :src="userAvatars[item.postId]" 
						style="width: 35px; height: 35px; border-radius: 50%;"></image>
						<text class="showName">{{userNames[item.postId]}}</text>
				  </view>
				
	            <text style="font-size: 20px;">{{item.title}}</text>
	          </view>  
	        </view>
	      </scroll-view>
	    </view>
	
	
	<view class="container">
		<navigator url="/pages/newPost/newPost"  class="post-button">
			<button :style="{top: buttonTop + 'px', left: buttonLeft + 'px' }">发帖</button>
		</navigator>
	</view>
	
	
  </view>
  
</template>

<script>
export default {
  data() {
    return {
		userid:undefined,
		username:'',
		buttonTop: 0, // 发帖按钮的初始垂直位置
		buttonLeft: 0, // 发帖按钮的初始水平位置
		keyword:'',
		imageList: [],
		imagePairs: [],
		showFilter: false,
		filterOptions: [
		        { id: 1, label: '按时间', value: 'option1' },//关于价格后续应该添加到宠物的筛选中，
		        { id: 2, label: '按评分', value: 'option2' },//帖子中后续无需保留
		 ],
		 defaultAvatarUrl:
		 "https://cdn0.iconfinder.com/data/icons/multimedia-solid-30px/30/user_account_profile-64.png",
		 userAvatars: [],
		 userNames:[],
		 userIds:[],
		 Field:'',
		 selectedOption:''
	};

  },
  onShow() {
  	
  },
  onLoad() {
    // 在页面加载时获取图片和信息数据
	this.username = uni.getStorageSync('username');
	this.userid = uni.getStorageSync('userid');
	if (this.imagePairs.length ==0)
    this.fetchImageList();
	// this.fetchUserAvatar();
  },
	methods: {
		goToUserDetail(id){
			uni.navigateTo({
				url:'/pages/userDetail/userDetail?userId='+id
			})
		},
	  fetchUserAvatar(userid,postid){ 
		  uni.request({
		url: 'http://localhost:8080/user/id/'+userid,
		  method:'GET',
		  header:{'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)},
		  success: (res) => {
			  let ret = res.data.profilePictureUrl;
			  ret = ret===undefined? this.defaultAvatarUrl:ret;	
			  this.$set(this.userAvatars,postid,ret)
			  ret = res.data.userId
			  this.$set(this.userIds,postid,ret)
			  //顺便把用户名也带上
			  ret = res.data.username
			  this.$set(this.userNames,postid,ret)
		  },
		  fail: (err) => {
		  }
		 });
	  },
	  cancelFilter()
	  {		  
	  this.showFilter = !this.showFilter;		 
	  // console.log('点击筛选了')
	  },
	  myFilter(option){
		  console.log("值：",option)
		  if (option=='option1'){
			  this.Field = 'updatedAt'
		  }
		  if (option=='option2'){
			  this.Field = 'stars'
			  console.log("按照评分排序")
		  }
		  
		  uni.request({
		    url: 'http://localhost:8080/posts/query?orderField='+this.Field,
		  		method:'GET',
		  		header:{
		  			'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
		  		},
		    success: (res) => {
		  		this.imageList = res.data.records;
		      // 请求成功，将数据保存到imageList中
		  		  console.log(res)
		  		 this.imagePairs = [];
		         for (let i = 0; i < this.imageList.length; i += 2) {
		            this.imagePairs.push(this.imageList.slice(i, i + 2));
		      	  }
				  // this.$forceUpdate()
				  console.log('刷新')
		  		  //console.log(this.imageList)
		    },
		    fail: (err) => {
				console.log(err)
		    }
		  });
	  },
	  search(){
		  uni.request({
		    url: 'http://localhost:8080/posts/query?keyword='+this.keyword,
		  		method:'GET',
		  		header:{
		  			'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
		  		},
		    success: (res) => {
				this.imageList = res.data.records;
		      // 请求成功，将数据保存到imageList中
		  		  console.log(res)
				this.imagePairs = [];
		         for (let i = 0; i < this.imageList.length; i += 2) {
		            this.imagePairs.push(this.imageList.slice(i, i + 2));
		      	  }
		  		  //console.log(this.imageList)
		    },
		    fail: (err) => {
		      // 请求失败的处理逻辑
		    }
		  });
	  },
	  checkDetail(id)
	  {
		uni.navigateTo({
		  url: '/pages/detail/detail?postId='+id
		})
	  },
    fetchImageList() {
	  console.log(uni.getStorageSync('user'))
      uni.request({
        url: 'http://localhost:8080/posts/all',
		method:'GET',
		header:{
			'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
		},
        success: (res) => {
		  console.log('页面加载',res)
          this.imageList = res.data;
		  //console.log(this.imageList)
		   this.imagePairs = [];
		    for (let i = 0; i < this.imageList.length; i += 2) 
			{this.imagePairs.push(this.imageList.slice(i, i + 2));}
			for(let i=0;i<res.data.length;++i)
			{
				this.fetchUserAvatar(res.data[i].userId,res.data[i].postId);
			}
        },
        fail: (err) => {
			console.log(err)
        }
      });
    }
	}
};
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
	
	.showName{
		bottom: 13px;
		position: relative;
		font-size: medium;
	}
	
	.text-container {
	  display: flex;
	  flex-direction: column; /* 将文本垂直排列 */
	  align-items: center;
	}
	
	.post-button {
	  position: fixed; /* 设置发帖按钮为固定定位 */
	  left: 650rpx;
	  bottom: 0;
	  width: 80rpx;
	  padding: 5px;
	  /* border-radius: 50%; */
	  background-color: #ff557f;
	  color: white;
	  text-align: center;
	  /* z-index: 999; */
	}

	.search-bar {
	  display: flex;
	  align-items: center;
	  justify-content: space-between;
	  padding: 10px;
	  background-color: #f2f2f2;
	}
	
	.search-overlay {
	  position: fixed;
	  top: 70px;
	  left: 0;
	  width: 100%;
	  height: 10%;
	  z-index: 9999; /* 设置一个较高的 z-index 值 */
	  background-color: rgba(255, 170, 255, 0.5); /* 添加半透明背景，以突出显示筛选框 */
	}
	
	.search-dropdown {
	display: flex;
	 position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 80%;
	max-width: 400px; /* 设置筛选框的最大宽度 */
	background-color: rgba(255, 255, 255, 0.5);
	padding: 10px;
	}
	
	.search-bar input {
	  flex: 1;
	  margin-right: 10px;
	  padding: 5px;
	  border: 1px solid #ccc;
	}
	
	.search-bar button {
	  padding: 5px 10px;
	  background-color: #ff557f;
	  color: #fff;
	  border: none;
	}
	
uni-list-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

image {
  /* width: 100px; */
  /* height: 100px; */
  margin-right: 10px;
}

text {
  flex: 1;
  line-height: 1.5;
}
</style>

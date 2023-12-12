<template>
	<view >
	<scroll-view style="height: 750px;">
		<view class="image-container">
		<!-- 图片文字信息展示 -->
		
			<swiper :autoplay="true" :indicator-dots="true" style="height: 250px;"
			@change="swiperChange">
			      <swiper-item v-for="(item, index) in image.imageUrls" :key="index">
			        <image :src="item" mode="widthFix" class="detail-image"></image>
			      </swiper-item>
			    </swiper>
			<!-- <image :src="image.imageUrls" mode="widthFix" class="detail-image"></image> -->
			
			<!-- <view class="userInfo" @click="goToUserDetail">
					<image :src="userAvatars[item.postId]" 
							style="width: 35px; height: 35px; border-radius: 50%;"></image>
					<text class="showName">{{userNames[item.postId]}}</text>
			</view> -->
			
			<div class="text-container">
				<h1 style="border-bottom: 1px solid #d1d1d1;">{{ image.title }}</h1>
				<p style="font-size: 16px;">{{ image.content }}</p>
			</div>
			
		<!-- 动物名链接 -->
			<view style="margin-left: 10px;">
				宠物链接：
			</view>
			<view class="chooseText">
				<text v-for="(item, index) in petName" :key="index" 
				:class="{ active: currentIndex === index }"
				style="border: 1px solid #d1d1d1;padding: 4px;"
				@click="toConcretePet">
			      #{{ item }}
				</text>
			</view>
			
			<!-- 打分 -->
			<view class="scoreContainer">
				<text style="font-size: 20px;">评价：</text>
				<button class="scoreButton" @click="rate(1)">1</button>
				<button class="scoreButton" @click="rate(2)">2</button>
				<button class="scoreButton" @click="rate(3)">3</button>
				<button class="scoreButton" @click="rate(4)">4</button>
				<button class="scoreButton" @click="rate(5)">5</button>
			</view>
			
			<!-- 评分 -->
			<view class="starShow">
				<text style="font-size: 20px;">当前帖子评价：{{stars}}</text>
			</view>		
		
		<!-- 收藏按钮 -->
			<view class="reserve-button" @click="collect">
				<image v-if="if_collect"  src="../../static/star_collected.png"class="reserve-star"></image>
				<image v-else  src="../../static/star_uncollected.png"class="reserve-star"></image>
				<!-- <button>收藏</button> -->
			</view>
		
		</view>
	</scroll-view>
	
	<!-- 咨询界面入口 -->
	<view class="floating-button" :style="{ top: buttonTop + 'px', left: buttonLeft + 'px' }" @click="result"
	@touchstart="handleTouchStart"
	@touchmove="handleTouchMove"
	@touchend="handleTouchEnd">
		<image src="../../static/ask.png" class="button-icon"></image>
	</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username:'',
				userId:undefined,
				if_collect:false,
				buttonTop: 100, // 按钮的初始垂直位置
				buttonLeft: 370, // 按钮的初始水平位置
				touchStartX: 0, // 触摸起始位置的水平坐标
				touchStartY: 0, // 触摸起始位置的垂直坐标
				postId:'',
				image:[],//实际上image中保存的是pet的所有信息
				petName:[],
				currentIndex:0,
				stars:'0'
			}
		},
		onShow() {			
		},
		onLoad(e) {
			console.log('传入的参数id是：',e.postId);
			this.postId = e.postId
			this.username = uni.getStorageSync('username');
			this.userId = uni.getStorageSync('userid');
			this.fetchImage();
			this.mydelay();
			this.getCollection(e.postId);
			this.fetchPostStars();
		},
		methods: {
			goToUserDetail(id){
				uni.navigateTo({
					url:'/pages/userDetail/userDetail?userId='+id,
				})
			},
			fetchPostStars(){
				uni.request({
					url: 'http://localhost:8080/posts/'+this.postId+'/averageRating',
					method:'GET',
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						console.log("返回评分结果",res)
						this.stars = res.data
					}
					});
			},
			rate(star) {
			      // 在这里可以处理打分逻辑，例如发送请求保存分数到数据库等
			      // console.log('打分：', star);
				  uni.request({
				  	url: 'http://localhost:8080/star/ratePost?postId='+this.postId+'&userId='+this.userId+'&star='+star,
				  	method:'PUT',
				  	header:{
				  		'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
				  	},
				  	success: (res) => {
				  		console.log("返回评分结果",res)
				  		uni.showToast({
				  			icon:'success',
							title:res
				  		})
						this.fetchPostStars();
				  	},
				  	fail: (err) => {
				  		console.log(err)
				  	}
				  })
			    },
			getCollection(postId){
				const userId = this.userId
				uni.request({
					url: 'http://localhost:8080/favorites/favorite/',
					method:'GET',
					data:{userId,postId},
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						console.log("收藏返回结果",res.data)
						// this.$set(this.if_collect,res.data)
						this.if_collect = res.data
					},
					fail: (res) => {
						console.log(res)
					}
				})
			},
			toConcretePet(){
				uni.navigateTo({
					url:'/pages/petDetail/petDetail?petId='+this.image.petIds[this.currentIndex]				
				})
			},
			delay(ms) {
			  return new Promise(resolve => setTimeout(resolve, ms));
			},
			async mydelay() {
			  await this.delay(500); // 在这里设置延迟的时间，单位为毫秒
			  console.log("等待结束，延迟执行的代码");
			  this.fetchPetName();
			},
			swiperChange(event)
			{
				this.currentIndex = event.detail.current;
			},
			collect(){
				this.if_collect = !this.if_collect ;
				const userId = this.userId
				let myMethod = ''
				let myRequest =''
				let myTitle = ''
				let myIcon = ''
				if (this.if_collect) //从false => true
				{
					myMethod="POST"
					myRequest = 'add'
					myTitle = '收藏成功'
					myIcon = 'success'
				}
				else{
					myMethod="DELETE"
					myRequest ='delete'
					myTitle = '取消收藏'
					myIcon = 'error'
				}
				console.log(myMethod,myRequest)
				uni.request({
					url: 'http://localhost:8080/favorites/favorite/'+myRequest+'?userId='+userId+'&postId='+this.postId,
					method:myMethod,
					// data:{userId,postId},
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						console.log(myTitle)
						uni.showToast({
							icon:myIcon,
							title:myTitle,
							duration:1000
						})
					},
					fail: (res) => {
						console.log('报错信息：',res)
					}
				})
			},
			handleTouchStart(event) {
			      // 记录触摸起始位置
			      this.touchStartX = event.touches[0].clientX;
			      this.touchStartY = event.touches[0].clientY;
			    },
			    handleTouchMove(event) {
			      // 计算触摸移动的距离
			      const touchMoveX = event.touches[0].clientX - this.touchStartX;
			      const touchMoveY = event.touches[0].clientY - this.touchStartY;
			      
			      // 更新按钮的位置
			      this.buttonTop += touchMoveY;
			      this.buttonLeft += touchMoveX;
			      
			      // 更新触摸起始位置
			      this.touchStartX = event.touches[0].clientX;
			      this.touchStartY = event.touches[0].clientY;
			    },
			    handleTouchEnd() {
			      // 触摸结束时的处理
			    },
			result(){
				uni.navigateTo({
					url:"/pages/result/result?postId="+this.image.postId
				})
			},
			fetchImage(){
				uni.request({
					url: 'http://localhost:8080/posts/'+this.postId,
					method:'GET',
					header:{
							'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
						},
				  success: (res) => {
				    this.image = res.data;
				  },
				  fail: (err) => {
				    // 请求失败的处理逻辑
				  }
				});
			},
			fetchPetName()
			{
				 console.log("输出image的内容");
				 console.log(this.image);
				for (let i = 0;i<this.image.petIds.length;i++)
				{
					uni.request({
					url: 'http://localhost:8080/pet/get/'+this.image.petIds[i],
					method:'GET',
					header:{
							'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
						},
					success: (res) => {
						  // console.log(res)
				    this.petName.push(res.data.name) ;
					},
					fail: (err) => {
				    // 请求失败的处理逻辑
					console.log("出现的错误：")
					console.log(err)
					}
					});
				}
				// console.log("完成查询之后的petname：",this.petName)
			}
		}
	}
</script>

<style>
	.starShow{
		margin-top: 20px;
		margin-left: 15px;
		margin-right: 50px;
		font-size: 20px;
		border: 2px dashed #000000;
	}
	
	.scoreContainer{
		display: flex;
		flex-direction: row;
	}
	.scoreButton{
		width: 15%;
		margin-right: 3px;
		background-color: #ffdbfb
	}
	
	.chooseText{
		padding: 10px;
		display: flex;
		justify-content: space-between;
	}
	
	text.active {
	  background-color: #ffc0fc; /* 高亮文字的样式 */
	}
	
	.reserve-button {
		position: fixed;
		/* flex-wrap: nowrap; */
		display: flex;
		align-items: center;
		right: 0px;
		bottom: 0px;
	}
	
	.reserve-star{
		width: 46px;
		height: 46px;
		margin-right: 5px;
	}
	
	.buy-button {
	  position: fixed;
	  bottom: 20px; /* 距离底部的距离，根据需要调整 */
	  left: 50%;
	  transform: translateX(-50%);
	  z-index: 999;
	  transition: opacity 0.3s ease; /* 添加过渡效果 */
	}
	
	.buy-button.fixed {
	  opacity: 0; /* 固定时隐藏按钮 */
	}
	
	button {
	  /* 购买按钮样式 */
	}
	
	.image-container {
	  height: 750rpx;
	  /* display: flex;
	  flex-wrap: wrap;
	  justify-content: flex-start;
	  align-items: flex-start; */
	  padding: 10px;
	}
	
	.floating-button {
	  position: absolute;
	  right: 15px;
	  z-index: 999;
	  /* 其他样式... */
	}
	
	.button-icon {
	  width: 50px;
	  height: 50px;
	  /* 其他样式... */
	}
	
	.detail-image {
	  width: 98%; /* 根据需要调整图片宽度 */
	  height: auto;
	  margin-bottom: 10px;
	  border: 1px solid #b3b3b3;
	  box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.2);
	}
	
	.text-container {
	  padding-bottom: 80px;
	  margin-left: 10px;
	}
	
	h1 {
	  font-size: 24px;
	  font-weight: bold;
	  margin-bottom: 10px;
	}
	
	p {
	  font-size: 16px;
	  line-height: 1.5;
	}
</style>

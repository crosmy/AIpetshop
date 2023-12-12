<template>
	<view>
		<view class="container">
			<view class="collectionPosts" v-for="(item,index) in postPreviewImageList">
				<image :src="item" class="previewImage"></image>
				<text class="previewTitle"  >{{postPreviewTitleList[index]}}</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username:'',
				userId:undefined,
				collectionList:[],
				postIds:[],
				postPreviewImageList:[],
				postPreviewTitleList:[]
			}
		},
		onLoad() {
			this.username = uni.getStorageSync('username');
			this.userId = uni.getStorageSync('userid');
			this.fetchCollectionList()
		},
		methods: {
			delay(ms) {
			  return new Promise(resolve => setTimeout(resolve, ms));
			},
			async mydelay() {
			  await this.delay(500); // 在这里设置延迟的时间，单位为毫秒
			  console.log("等待结束，延迟执行的代码");
			  this.fetchPosts();
			},
			fetchCollectionList(){
				uni.request({
					url: 'http://localhost:8080/favorites/favorite/list?userId='+this.userId,
					method:'GET',
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						console.log('用户所有收藏信息：',res)
						this.postIds = res.data.map(item => item.postId)
					},
					fail: (res) => {
						console.log(res)
						
					}
				})
				this.mydelay()
			},
			fetchPosts(){
				for (let i =0;i<this.postIds.length;++i)
				{
					uni.request({
						url: 'http://localhost:8080/posts/'+this.postIds[i],
						method:'GET',
						header:{
							'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
						},
						success: (res) => {
								this.postPreviewImageList.push(res.data.imageUrls[0])
								 this.postPreviewTitleList.push(res.data.title)
								// console.log(res)  				   
						},
						fail: (err) => {
							console.log(err)
						}
					});
				}
			}
		}
	}
</script>

<style>
	.container{
		display: flex;
		padding: 5px;
	}
	
	.collectionPosts{
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		width: 200px;
		padding: 5px;
	}
	
	.previewImage{
		height: 200px;
		height: 200px;
	}
	
	.previewTitle{
		align-items: center;
		left: 40px;
		position: relative;
		font-size: 20px;
	}
</style>

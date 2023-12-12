<template>
	<view>
		<view class="container">
			<view class="collectionorders" v-for="(item,index) in orderPreviewImageList">
				<image :src="item" class="previewImage"></image>
				<text class="previewTitle"  >{{orderPreviewTitleList[index]}}</text>
				<text v-if="orderSellerIds==userId" class="previewTitle">[已售出]</text>
				<text v-else class="previewTitle">[已购买]</text>
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
				transactionList:[],
				orderPetIds:[],
				orderPreviewImageList:[],
				orderPreviewTitleList:[],
				orderSellerIds:[]
			}
		},
		onLoad() {
			this.username = uni.getStorageSync('username');
			this.userId = uni.getStorageSync('userid');
			this.fetchTransactionList()
		},
		methods: {
			delay(ms) {
			  return new Promise(resolve => setTimeout(resolve, ms));
			},
			async mydelay() {
			  await this.delay(500); // 在这里设置延迟的时间，单位为毫秒
			  console.log("等待结束，延迟执行的代码");
			  this.fetchorders();
			},
			fetchTransactionList(){
				uni.request({
					url: 'http://localhost:8080/transaction/order/list?userId='+this.userId,
					method:'GET',
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						console.log('用户所有订单信息：',res)
						 this.orderPetIds = res.data.map(item => item.petId)
						 this.orderSellerIds = res.data.map(item => item.sellerId)
					},
					fail: (res) => {
						console.log(res)
						
					}
				})
				 this.mydelay()
			},
			fetchorders(){
				for (let i =0;i<this.orderPetIds.length;++i)
				{
					uni.request({
						url: 'http://localhost:8080/pet/get/'+this.orderPetIds[i],
						method:'GET',
						header:{
							'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
						},
						success: (res) => {
								this.orderPreviewImageList.push(res.data.photoUrl)
								 this.orderPreviewTitleList.push(res.data.name)
								 console.log('查询宠物：',res)  				   
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
	
	.collectionorders{
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

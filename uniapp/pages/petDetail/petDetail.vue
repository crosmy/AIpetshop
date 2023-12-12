<template>
	<view>
		<view class="container">
		    <image :src="form.imageUrl" class="image"></image>
		    <view class="info">
		      <text class="name">宠物名称：{{ form.name }}</text>
			  <text class="age">宠物年龄：{{ form.age }}周岁</text>
		      <text class="price">售卖情况：{{ form.price === '0' ? '非卖' : '估价：'+form.price }}</text>
		      <text class="type">种类：{{ form.type }}</text>
		      <text class="breed">品种：{{ form.breed }}</text>
		      <text class="description">具体描述：{{ form.description }}</text>
		      <text class="health">健康状况：{{ form.health===undefined ? '未填写':form.health }}</text>
		      <text class="vaccination">疫苗接种：{{ form.vaccination===undefined ? '未填写':form.vaccination }}</text>
		    </view>
		  </view>
		  <view class="purchase">
			<button @click="buyPet" :disabled="petOfMyself">购买</button>
		  </view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form:{
					age:'',
					imageUrl:'',
					name:'',
					ownerId:'',//用来获取头像信息以及用户名
					price:'',//price为0为非卖
					type:'',
					breed:'',
					description:'',
					health:'',
					vaccination:''
				},
				petBuyForm:{
					  buyerId: 0,
					  sellerId: 0,
					  petId:0,
					  price:0
				},
				petId:'',
				petOfMyself : false
			}
		},
		onLoad(e) {
			this.form.userId = uni.getStorageSync('userid');
			this.username = uni.getStorageSync('username');
			this.petId = e.petId;
			// console.log("接收到了petId：",e.petId)
			this.fetchPetInfo();
		},
		methods: {
			buyPet(){
				if (this.petOfMyself)
				{
					return;
				}
				this.petBuyForm.buyerId=this.form.userId;
				this.petBuyForm.sellerId=this.form.ownerId;
				this.petBuyForm.petId=this.petId;
				this.petBuyForm.price=this.form.price;
				uni.request({
					// url: 'http://localhost:8080/transaction?buyerId='+this.form.userId+'&sellerId='+this.form.ownerId,
					url: 'http://localhost:8080/transaction',
					method:'POST',
					data:this.petBuyForm,
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						uni.showToast({
							icon:'success',
							title:"购买成功",
						})
					},
					fail: (res) => {
						console.log('报错信息：',res)
					}
				})
			},
			fetchPetInfo(){
				uni.request({
					url:'http://localhost:8080/pet/get/'+this.petId,
					method:'GET',
					header:{
							'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
						},
					success: (res) => {
						console.log(res)
						this.form.name = res.data.name;
						this.form.age = res.data.age;
						this.form.breed = res.data.breed
						this.form.description = res.data.description
						this.form.ownerId = res.data.ownerId
						this.form.price = res.data.price
						this.form.type = res.data.type
						this.form.health = res.data.health
						this.form.vaccination = res.data.vaccination
						this.form.imageUrl = res.data.photoUrl
						
						this.petBuyForm.buyerId = this.form.userId
						this.petBuyForm.sellerId = this.form.ownerId
						if (this.petBuyForm.buyerId == this.petBuyForm.sellerId)
						{
							 this.petOfMyself = true;
						}
					},
					fail: (err) => {
					// 请求失败的处理逻辑
						console.log("出现的错误：")
						console.log(err)
					}
					});		
			}
		}
	}
</script>

<style>
	.purchase{
		position: relative;
	    padding: 20px;
	    background-color: crimson;
	    margin: 40px;
	}
	.container {
	  display: flex;
	  flex-direction: column;
	  justify-content: space-between;
	  align-items: center;
	  /* padding: 20px; */
	  border: 4px solid #ffaaff;
	  margin: 20px;
	}
	
	.image {
	  width: 100px;
	  height: 100px;
	  border-radius: 50%;
	}
	
	.info {
	  display: flex;
	  flex-direction: column;
	  margin-top: 16px;
	}
	
	.name {
	  font-size:25px;
	  font-weight: bold;
	}
	.age {
	  margin-top: 8px;
	  font-size: 20px;
	  color: #666666;
	}
	.price {
	  margin-top: 8px;
	  font-size: 18px;
	  color: #999999;
	}
	
	.type,
	.breed,
	.description,
	.health,
	.vaccination {
	  margin-top: 8px;
	  font-size: 19px;
	  color: #666666;
	}
</style>

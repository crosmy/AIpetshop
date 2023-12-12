<template>
	<view>
		<view class="petContainer" >
			<view v-for="(item,index) in petList" :key="index" class="petItems" 
			@click="toPetDetail(item.petId)">
				<image :src="item.photoUrl" class="itemImage"></image>
				<view class="textfield">
					<text>[{{item.breed}}]:{{item.name}}</text>
					<text>{{item.age}}岁</text>
				</view>			
			</view>
		</view>
		<button class="fab-button" @click="goToAddPet">
		      <image class="fab-icon" src="../../static/add.png"></image>
		</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				petList:[],
				username:'',
				userid:''
			}
		},
		onLoad() {		
			this.userid = uni.getStorageSync('userid');
			this.username = uni.getStorageSync('username');
			this.fetchPetList();
		},
		methods: {
			toPetDetail(id)
			{
				uni.navigateTo({
					url:'/pages/petDetail/petDetail?petId='+id			
				})
			},
			goToAddPet()
			{
				uni.navigateTo({
					url:"/pages/newPet/newPet"
				})
			},
			fetchPetList() {
			  uni.request({
			    url: 'http://localhost:8080/pet/getByOwnerId/'+this.userid,
				method:'GET',
				header:{
					'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
				},
			    success: (res) => {
			      // 请求成功，将数据保存到petList中
				  this.petList = res.data
				  console.log(this.username)
				  console.log(res)
				 
				   
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
	.textfield{
		display: flex;
		flex-direction: column;
		margin-top: 25px;
		font-size: 25px;
	}
	.itemImage{
		width: 120px;
		height: 100px;
		padding: 10px;
	}
	.petItems{
		border: 1px solid #e1e1e1;
		display: flex;
		width: 98%;
		margin: 5px;
		margin-bottom: 8px;
		box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.2);
		/* height: 100px; */
	}
	.petContainer{
		width: 100%;
		display: felx;
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

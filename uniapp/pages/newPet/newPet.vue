<template>
	<view>
		<!-- 存动物信息 -->
		<view class="">
			<text>请填入宠物姓名*</text>
			<input v-model="form.name"  type="text" placeholder="宠物姓名"/>
			<text>请填入种类（如猫、狗）</text>
			<input v-model="form.type"  type="text" placeholder="宠物种类"/>
			<text>请填入具体品种（波斯猫，金毛犬）</text>
			<input v-model="form.breed"  type="text" placeholder="宠物品种"/>
			<text>请填入宠物年龄</text>
			<input v-model="form.age"  type="text" placeholder="宠物年龄"/>
			<text>请填入具体描述</text>
			<input v-model="form.description"  type="text" placeholder="宠物描述"/>
			<text>请填入宠物健康状况</text>
			<input v-model="form.healthInformation"  type="text" placeholder="宠物状况"/>
			<text>请填入宠物接种疫苗情况</text>
			<input v-model="form.vaccinations"  type="text" placeholder="宠物疫苗"/>
			<text>请填入宠物价格</text>
			<input v-model="form.price"  type="text" placeholder="宠物价格"/>
			<text>请填入宠物图片Url</text>
			<input v-model="inputPhotoUrl" placeholder="宠物url" />
			<button @click="imageUpload">上传图片</button>
		</view>
		
		<!-- 存图片信息 -->
		<view class="">
			<image :src="petPhotoUrl" mode="aspectFit"></image>
		</view>
		<button class="fab-button " @click="createPet">
		      创建
		</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username:'',
				inputPhotoUrl:'',
				petPhotoUrl:'https://pic.52112.com/180108/DOG/ztuo2nUs8z.jpg',
				form: {
						ownerId:undefined,
						name:'',
						type:'',
						breed:'',
						age:undefined,
						description:'',
						healthInformation:'',
						vaccinations:'',
						price: undefined,
						photoUrl:'https://pic.52112.com/180108/DOG/ztuo2nUs8z.jpg'
						},
			}
		},
		onLoad() {
			this.form.ownerId = uni.getStorageSync('userid');
			this.username = uni.getStorageSync('username');
		},
		methods: {
			imageUpload()
			{
				this.form.photoUrl = this.inputPhotoUrl;
				this.petPhotoUrl = this.inputPhotoUrl;
			},
			createPet()
			{
				uni.request({
				    url: 'http://localhost:8080/pet/add',
				    method: 'POST',
				    data: this.form,
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
				    success: (res) => {
						
						if (res.statusCode == 200)
						{
							uni.showToast({
							title:"创建成功",
							icon:'success'
						})
						uni.redirectTo({
							url:"/pages/myPets/myPets"
						})
				      // 请求成功的处理逻辑
				      // 例如显示发布成功提示，跳转到帖子详情页等
						}
						else
						{
							uni.showToast({
								title:"创建失败"
							})
							console.log(res.errMsg);
						}
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
	}
</script>

<style>
	
</style>

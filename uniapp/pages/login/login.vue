<template>
	<view>
		
		<view style="width: 90%; margin: 200rpx auto;">  <!-- 200rpx  == 100px -->
		<view style=" margin-bottom: 70rpx; font-size: 60rpx; color: royalblue; text-align: center;">登 录</view>
		<uni-forms ref="form" :modelValue="form" :rules="rules">
			<uni-forms-item name="username">
				<uni-easyinput v-model="form.username" prefixIcon="person" placeholder="请输入登录用户名"></uni-easyinput>
			</uni-forms-item>
		
			<uni-forms-item name="password">
				<uni-easyinput type="password" v-model="form.password" prefixIcon="locked" placeholder="请输入登录密码"></uni-easyinput>
			</uni-forms-item>
		</uni-forms>
			
		<view>
			<button type="primary" @click="login">登 录</button>
		</view>
		<navigator url="/pages/register/register" style="margin: 40rpx 0; color: deepskyblue;">前往注册</navigator>
	</view>
	</view>
</template>

<script>
import loginVue from './login.vue'
	export default {
		data() {
			return {
				form: {username: 'user1',email:'abc@qq.com', password: 'abc'},
				rules: {	
					username: {
						rules: [{required: true, errorMessage: '请输入用户名'}],
						validateTrigger:'submit'
					},
					password: {
						rules: [{required: true, errorMessage: '请输入密码'}],
						validateTrigger:'submit'
					},
				}
			}
		},
		methods: {
			login() {
				this.$refs.form.validate().then(res=>{
					this.request({url: '/auth/login', method: 'POST', data: this.form}).then(res => {
						//console.log(res)//返回值只有一个token
							// 跳转页面
							// uni.redirectTo({
							// 	url: '/pages/find/find'
							// })
							uni.switchTab({
								url:"/pages/find/find",
							})
							uni.showToast({
								title: '登录成功'
							})
							// 存储用户的数据到 storage
							uni.setStorageSync('userid',res.userId)
							uni.setStorageSync('username',this.form.username)
							uni.setStorageSync('user'+this.form.username, res.token)
							//uni.setStorageSync('user'+this.form.username, res)
						
					})
				}).catch(err =>{
					uni.showToast({
						title: '登录失败，请检查你的用户名和密码！',
						icon:''
					})
					console.log('表单错误信息：', err);
				})
				
			}
		}
	}
</script>

<style>

</style>

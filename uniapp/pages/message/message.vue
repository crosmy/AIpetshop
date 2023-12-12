<template>
	<view style="padding: 20rpx;">
		<uni-easyinput type="textarea" v-model="message.content" placeholder="请输入内容"></uni-easyinput>
		<view style="text-align: right; margin: 20rpx 0">
			<button size="mini" type="primary" @click="submitMsg">提交</button>
		</view>


		<view>
			<uni-list :border="true">
				<!-- 显示圆形头像 -->
				<uni-list-chat v-for="item in messageList" :key="item.id" :avatar-circle="true" :title="item.username + '的留言'"
					avatar="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/460d46d0-4fcc-11eb-8ff1-d5dcf8779628.png"
					:note="item.content" :time="item.time.slice(0, 16)"  link :to="'/pages/messageDetail/messageDetail?id=' + item.id"></uni-list-chat>
			</uni-list>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				message: {},
				messageList: []
			}
		},
		onShow() {
			this.load()
		},
		methods: {
			load() { // 获取留言列表
				this.request({
					url: '/message',
					method: 'GET'
				}).then(res => {
					this.messageList = res.data
				})
			},
			submitMsg() {
				if (!this.message.content) {
					uni.showToast({
						icon: 'none',
						title: '请填写留言内容'
					})
					return false
				}
				this.request({
					url: '/message',
					method: 'POST',
					data: this.message
				}).then(res => {
					if (res.code === '200') {
						uni.showToast({
							title: '提交成功'
						})
						this.message = {}
						this.load()
					} else {
						uni.showToast({
							icon: 'none',
							title: res.msg
						})
					}
				})
			}
		}
	}
</script>

<style>

</style>

<template>
	<view style="padding: 20rpx;">
		<view  v-html="detail.content" style="padding: 20rpx; background-color: #eee; color: cornflowerblue"></view>
		
		<view style="margin-top: 40rpx; padding: 20rpx 0; color: #666">
			回复列表
		</view>
		<view v-for="item in reply" :key="item" style="margin-top: 40rpx; border-bottom: 1rpx solid #ddd; padding: 20rpx 0;">
			<view>{{ item }}</view>
			<view style="text-align: right; ">
				<text style="color: red; font-size: 24rpx" @click="del(item.id)">删除</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				detail: {},
				reply: [
					'1122',
					'223344'
				]
			}
		},
		onShow() {
			let pages = getCurrentPages();
			let currentPage = pages[pages.length-1];
			console.log(currentPage.options)	
			const id = currentPage.options.id
			this.load(id)
		},
		methods: {
			load(id) {
				this.request({
					url: '/message/' + id,
					method: 'GET'
				}).then(res => {
					this.detail = res.data
				})
			},
			del(id) {
				this.request({
					url: '/message/' + id,
					method: 'DELETE'
				}).then(res => {
					uni.showToast({
						title: '删除成功'
					})
				})
			}
		}
	}
</script>

<style>

</style>

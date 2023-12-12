<template>
	<view>
		<view class="container">
	    <view v-for="(message, index) in messages" :key="index" class="message-container">
	      <view v-if="index%2==0" class="message message-me">
	        <text class="message-text">{{ messages[index] }}</text>
	      </view>
	      <view v-else class="message message-other">
	        <text class="message-text">{{ messages[index] }}</text>
	      </view>
	    </view>
	  </view>
	  
	  <!-- 消息输入框 -->
	  <view class="inputMessage">
			<input  v-model="newInputMessage"  type="text" placeholder="输入消息" class="inputText"/>
			<button @click="sendMessage" class="sendButton">发送</button>
	  </view>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				messages:['helloWorld!','你好，世界！'],//onload的时候需要加载所有的历史记录
				messageIsMe:[true,false],
				form:{
					message:'',
					userId:'',
					sellerId:''
				},
				username:'',
				newInputMessage:''
			}
		},
		onLoad(e) {
			this.form.userId = uni.getStorageSync('userid');
			this.username = uni.getStorageSync('username');
			this.form.sellerId = e.postId
			this.loadMessageRecord();
			
		},
		methods: {
			loadMessageRecord(){
				uni.request({
					url:'http://localhost:8080/chat/record',
					data:this.form,
					method:"GET",
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						console.log("返回的聊天记录：",res)
						for (let i =res.data.length-1;i>=0;--i)
						{
							this.messages.push(res.data[i].userMessage) 
							this.messages.push(res.data[i].modelResponse)
						}
						console.log("当前所有的聊天记录：",this.messages)
					}
				})
			},
			sendMessage(){
				this.form.message = this.newInputMessage
				uni.request({
					url:'http://localhost:8080/chat/message',
					data:this.form,
					method:"POST",
					header:{
						'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
					},
					success: (res) => {
						console.log("返回的聊天信息：",res)
					}
					
				})
			}
		}
	}
</script>

<style>
	.sendButton{
		border: 2px solid #ffaaff;
		margin-left: 2px;
		margin-right: 2px;
	}
	
	.inputText{
		border: 1px solid #969696;
		margin-left: 5px;
		height: 45px;
		width: 340px;
	}
	
	.inputMessage{
		display: flex;
		flex-direction: row;
		position: fixed;
		bottom:0;
		left: 0;
		padding-bottom: 5px;
		padding-top: 3px;
		border-top: 2px solid #969696;
		width: 100%;
		z-index:  999;
		opacity: 1;
		background-color: rgba(255, 255, 255, 1); /* 不透明的白色背景 */
	}
	.container {
	  padding: 16px;
	  /* margin-bottom:60px; */
	  padding-bottom: 60px;
	  /* position: relative; */
	}
	
	.message-container {
	  margin-bottom: 16px;  
	  /* position: relative; */
	 /* display: flex; */
	}
	
	.message {
	  /* position: absolute; */
	  bottom: 15%;
	  padding: 8px;
	  border-radius: 8px;
	  max-width: 80%;
	  font-size: 20px;
	  /* display: flex; */
	}
	
	.message-me {
	  background-color: #e2f0cb;
	  display: flex;
	  align-content: flex-end;
	  margin-right: 50px;
	}
	
	.message-other {
	  background-color: #f5f5f5;
	  display: flex;
	  align-content: flex-start;
	}
	
	.message-text {
	  font-size: 20px;
	  color: #333333;
	}
</style>

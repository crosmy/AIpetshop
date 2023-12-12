<template>
	<view>
		<!-- 创建新的宠物 -->
		<button class="fab-button" @click="goToNewPetPage">
		    <image class="fab-icon" src="../../static/add.png"></image>
		</button>
		
		<!-- 搜索栏容器 -->
	<!-- 	<view class="search-bar">
		  <input type="text" v-model="keyword" placeholder="输入关键词">
			<button @click="filter" type="default" style="border: 5px;margin-right: 3px;">筛选</button>
			<view v-if="showFilter" class="search-overlay">
				<view  class="search-dropdown">
		        <checkbox-group>
		        <checkbox  style="font-size: 23px;"  v-for="option in filterOptions" :key="option.id" :value="option.value">
		          {{ option.label }}
		        </checkbox>
		        </checkbox-group>
				</view>
			</view>
			
			<button @click="search">搜索</button>
		</view> -->
		
		   <view class="plstyle">
		      <scroll-view scroll-y="true" v-for="pair in imagePairs" class="image-container">
		        <view v-for="item in pair" @click="checkDetail(item.petId)" class="image-row">
		          <image :src="item.photoUrl"
					class="image-item">	
		          </image>
		          <view class="text-container">
		            <text>{{item.name}}</text>
					<text>{{item.age}}周岁</text>
		          </view>  
		        </view>
		      </scroll-view>
		    </view>
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userid:undefined,
				username:'',
				keyword:'',
				imageList: [],
				imagePairs: [],
				showFilter: false,
				filterOptions: [
				        { id: 1, label: '最低价格', value: 'option1' },//关于价格后续应该添加到宠物的筛选中，
				        { id: 2, label: '最高价格', value: 'option2' },//帖子中后续无需保留
						{ id: 3, label: '宠物种类', value: 'option3' },
						{ id: 4, label: '收藏数', value: 'option4' },
				        // 其他选项...
				 ],
			}
		},
		onLoad() {
			this.username = uni.getStorageSync('username');
			this.userid = uni.getStorageSync('userid');
			this.fetchImageList();
		},
		methods: {
			goToNewPetPage()
			{
				uni.navigateTo({
					url:"/pages/newPet/newPet"
				})
			},
			search(){
				//这里还需要更改，改成根据关键字查询宠物，暂时不要点击，否则会覆盖掉imageList中
				//现在存的东西
			uni.request({
				url: 'http://localhost:8080/posts/query?keyword='+this.keyword,
		  		method:'GET',
		  		header:{
		  			'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
		  		},
				success: (res) => {
				this.imageList = res.data.records;
				// 请求成功，将数据保存到imageList中
		  		  console.log(res)
					this.imagePairs = [];
		         for (let i = 0; i < this.imageList.length; i += 2) {
		            this.imagePairs.push(this.imageList.slice(i, i + 2));
		      	  }
		  		  //console.log(this.imageList)
		    },
		    fail: (err) => {
		      // 请求失败的处理逻辑
		    }
		  });
	  },
	  checkDetail(id)
	  {
		uni.navigateTo({
		  url: '/pages/petDetail/petDetail?petId='+id
		})
	  },
    fetchImageList() {
      // 发起网络请求获取图片和信息数据
      // 例如使用uni.request方法
	  console.log(uni.getStorageSync('user'))
      uni.request({
        url: 'http://localhost:8080/pet/getAll',
		method:'GET',
		header:{
			'Authorization':'Bearer ' + uni.getStorageSync('user'+this.username)
		},
        success: (res) => {
          // 请求成功，将数据保存到imageList中
		  console.log(res)
          this.imageList = res.data;
		  console.log(this.imageList)
		   this.imagePairs = [];
		            for (let i = 0; i < this.imageList.length; i += 2) {
		              this.imagePairs.push(this.imageList.slice(i, i + 2));
					  }
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
	.plstyle{
		/* display: flex; */
		justify-content: space-between;
	}
	
	.image-container {
	   display: flex;
	  flex-direction: row; 
	  justify-content: space-between;
		flex: 1; 
	  padding: 5px;
	  
	}
	
	.image-row {
		display: inline-block;
		width: 48%;
		margin-bottom: 10px;
		padding: 2px;
		box-shadow: 2px 2px 5px rgba(255, 0, 0, 0.2);
	   justify-content: space-between; 
	}
	
	.image-item {
	  width: 100%;   
	  box-sizing: border-box; /* 防止添加 padding 导致超出容器宽度 */
	  position: relative;
	  margin-bottom: 10px;
	  padding-left: 3px;
	  padding-right: 3px;
	  border-radius: 10%;
	}
	
	.text-container {
	  display: flex;
	  flex-direction: column; /* 将文本垂直排列 */
	  align-items: center;
	}
	
	.search-bar {
	  display: flex;
	  align-items: center;
	  justify-content: space-between;
	  padding: 10px;
	  background-color: #f2f2f2;
	}
	.search-overlay {
	  position: fixed;
	  top: 70px;
	  left: 0;
	  width: 100%;
	  height: 10%;
	  z-index: 9999; /* 设置一个较高的 z-index 值 */
	  background-color: rgba(255, 170, 255, 0.5); /* 添加半透明背景，以突出显示筛选框 */
	}
	
	.search-dropdown {
	 position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 80%;
	max-width: 400px; /* 设置筛选框的最大宽度 */
	background-color: rgba(255, 255, 255, 0.5);
	padding: 10px;
	}
	
	.search-bar input {
	  flex: 1;
	  margin-right: 10px;
	  padding: 5px;
	  border: 1px solid #ccc;
	}
	
	.search-bar button {
	  padding: 5px 10px;
	  background-color: #ff557f;
	  color: #fff;
	  border: none;
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

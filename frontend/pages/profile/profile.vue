<template>
  <view class="profile">
    <text class="nickname">{{ nickname }}</text>
  </view>
</template>

<script>
export default {
  data() {
    return {
      nickname: ''
    };
  },
  mounted() {
    // 在页面加载时获取昵称信息
    this.fetchNickname();
  },
  methods: {
    fetchNickname() {
      // 发起网络请求获取昵称信息
      // 例如使用uni.request方法
      uni.request({
        url: '你的后端接口地址',
        success: (res) => {
          // 请求成功，将昵称信息保存到nickname中
          this.nickname = res.data.nickname;
        },
        fail: (err) => {
          // 请求失败的处理逻辑
        }
      });
    }
  }
};
</script>

<style>
.profile {
  padding: 10px;
}

.nickname {
  font-size: 18px;
  font-weight: bold;
}
</style>
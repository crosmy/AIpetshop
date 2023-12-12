const BASE_URL = `http://localhost:8080`

export const request = (options) => {  // {url: '', method: '', data: {}}
	return new Promise((resolve, reject) => {
		const username = uni.getStorageSync('username')
		
		console.log("testMessage")
		console.log(uni.getStorageSync('user'))
		console.log(uni.getStorageSync('user'+username))
		uni.request({
			url: BASE_URL + options.url,
			method: options.method || 'GET',
			header: {token: uni.getStorageSync('user'+username) ? uni.getStorageSync('user'+username) : ''},
			data: options.data || {},
			success: (res) => {
				console.log(res.statusCode)
				const data = res.data
				/*if (res.statusCode  === 401) {  // 401表示无权限
					
				} else */
				if(res.statusCode !== 200) {
					uni.showToast({
						icon: 'error',
						title: '操作错误'
					})
					uni.navigateTo({
						url: '/pages/login/login'
					})
					return
				}
				resolve(data) 
			},
			fail: (error) => {
				uni.showToast({
					icon: 'error',
					title: '系统错误'
				})
				reject(error)
			}
		})
	})
}
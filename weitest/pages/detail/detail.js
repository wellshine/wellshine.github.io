// pages/detail/detail.js
Page({
  data: {
    info: {
      id: 1,
      title: "aaaa",
      img: "../../little_animal/little_animal_05.png",
      cTime: "2017-02-09",
      content: "在昨天的京东集团开年大会上，刘强东第一次系统全面地大谈了一番他对人工智能这个当红辣子鸡技术的理解和认知。不过，当说到“未来机器人将取代我们百分之七八十以上的蓝领工作”时，估计台下一部分人是倒吸了一口凉气！"
    }
  },
  
  onLoad: function (options) {

    var that = this
console.log(options);
    // 页面初始化 options为页面跳转所带来的参数
    wx.request({
      url: 'http://localhost/weicms/index.php?s=/addon/Mycms/Mycms/getDetail', //仅为示例，并非真实的接口地址
      data: {id:options.id},//带参数的
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          info: res.data
        })
      }
    })
  },
  onPullDownRefresh: function(){
    wx.stopPullDownRefresh()
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})
// pages/lists/lists.js
Page({
  data: {
    newsList: [

    ],
    lastid: 0,
    isfirst: 1
  },

  onPullDownRefresh: function () {
    wx.showToast({
      title: 'loading...',
      icon: 'loading'
    })
  },
  loadData: function (lastid) {
    var limit = 1;//每页最多显示条数
    var that = this


    wx.request({
      url: 'http://localhost/weicms/index.php?s=/addon/Mycms/Mycms/getlist', //仅为示例，并非真实的接口地址
      data: {
        lastid: lastid,
        limit: limit
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (!res.data) {
          wx.showToast({
            title: '没有更多文章了',
            icon: 'success',
            duration: 1500
          })
          return false;
        }

        var len = res.data.length;

        that.setData({ lastid: res.data[len - 1].id })

        var dataArr = that.data.newsList;
        var newData = dataArr.concat(res.data);
        that.setData({
          newsList: newData
        })
      }
    })
  },
  loadMore: function (event) {
    var id = event.currentTarget.dataset.lastid;
    var isfirst = event.currentTarget.dataset.isfirst;
    wx.getNetworkType({
      success: function (res) {
        // 返回网络类型, 有效值：
        // wifi/2g/3g/4g/unknown(Android下不常见的网络类型)/none(无网络)
        var networkType = res.networkType
        if (networkType !== 'wifi' && isfirst == '1') {
          wx.showModal({
            title: '温馨提示',
            content: '当前非WiFi网络，土豪请继续~',
            confirmText: '继续',
            success: function (res) {
              if (res.confirm) {
                return false
              }
            }
          })

        }
      }
    })
    this.setData({ isfirst: 0 });
    this.loadData(id);

  },


  onLoad: function (/*不带参数*/) {

    // 页面初始化 options为页面跳转所带来的参数
    this.loadData(0);

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
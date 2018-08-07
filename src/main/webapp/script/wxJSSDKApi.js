/*
 * 生成微信JSSDK配置文件
 * funcs：需要的功能，字符串形式，如:func1,func2,func3...
 */
function createWxJsApiConfig(funcs){
	$.post(root+'/wxapi/createWxJsApiConfig',{
		url:window.location.href.split('#')[0],funcs:funcs
	},function(result){
		wx.config(result);
	},'json');
}
/*
 * 分享给朋友
 */
function wxShare2AppMessage(config){
	wx.onMenuShareAppMessage(config);
}
/*
 * 分享到朋友圈
 */
function wxShare2Timeline(config){
	wx.onMenuShareTimeline(config);
}
/*
 * 分享到微博
 */
function wxShare2Weibo(config){
	wx.onMenuShareWeibo(config);
}
/*
 * 分享到QQ
 */
function wxShare2QQ(config){
	wx.onMenuShareQQ(config);
}
/*
 * 分享到QQ空间
 */
function wxShare2QZone(config){
	wx.onMenuShareQZone(config);
}
<?php


define("TOKEN", "qijixuan");
$wechatObj = new wechatCallbackapiTest();
if (isset($_GET['echostr'])) {
    $wechatObj->valid();
}else{
    $wechatObj->responseMsg();
}

class wechatCallbackapiTest
{
    public function valid()
    {
        $echoStr = $_GET["echostr"];
        if($this->checkSignature()){
            header('content-type:text');
            echo $echoStr;
            exit;
        }
    }

    private function checkSignature()
    {
        $signature = $_GET["signature"];
        $timestamp = $_GET["timestamp"];
        $nonce = $_GET["nonce"];

        $token = TOKEN;
        $tmpArr = array($token, $timestamp, $nonce);
        sort($tmpArr, SORT_STRING);
        $tmpStr = implode( $tmpArr );
        $tmpStr = sha1( $tmpStr );

        if( $tmpStr == $signature ){
            return true;
        }else{
            return false;
        }
    }

    public function responseMsg()
    {
        $postStr = isset($GLOBALS['HTTP_RAW_POST_DATA']) ? $GLOBALS['HTTP_RAW_POST_DATA'] : file_get_contents("php://input");


    if (!empty($postStr)){
            $postObj = simplexml_load_string($postStr, 'SimpleXMLElement', LIBXML_NOCDATA);
		    $RX_TYPE = trim($postObj->MsgType);

            //用户发送的消息类型判断
            switch ($RX_TYPE)
            {
                case "text":    //文本消息
                    $result = $this->receiveText($postObj);
                    break;
         		case "image":
                    $result = $this->receiveImage($postObj);
					break;
                case "voice":   //语音消息
                    $result = $this->receiveText($postObj);
                    break;
                case "video":   //视频消息
                    $result = $this->receiveVideo($postObj);
                    break;
                case "location"://位置消息
                    $result = $this->receiveLocation($postObj);
                    break;
                case "event":	//事件（关注）
                    $result = $this->receiveEvent($postObj);
                    break;
             
                default:
                    $result = "unknow msg type: ".$RX_TYPE;
                    break;
            }
            
            echo $result;
      }else {
            echo "";
            exit;
        	}
   		 }
   		 private function receiveLocation($object)
   		 {
   		 	//回复位置
   		 	$content = "你发送的是位置，纬度为：".$object->Location_X."；经度为：".$object->Location_Y."；缩放级别为：".$object->Scale."；位置为：".$object->Label;
   		 	$result = $this->transmitText($object, $content);
   		 	return $result;
   		 }
   		 private function receiveEvent($object)
   		 {
   		 	$content = "";
   		 	switch ($object->Event)
   		 	{
   		 		case "subscribe":   //关注事件
   		 			$content = '欢迎关注';
   		 			break;
   		 		case "unsubscribe": //取消关注事件
   		 			$content = "";
   		 			break;
   		 		case"SCAN":
   		 			 
   		 			$content = '扫码关注事件';
   		 			break;
   		 
   		 		case "CLICK":
   		 			switch($object->EventKey)
   		 			{
   		 				case"history": //历史上的今天
   		 					include("history.php");
   		 					$content = getHistoryInfo();
   		 					break;
   		 				default:
   		 					$concent="点击菜单:".$object->EventKey;
   		 					break;
   		 			}
   		 
   		 			break;
   		 		default:
   		 			$concent="receive a new event:".$object->Event;
   		 			break;
   		 
   		 	}
   		 	$result = $this->transmitText($object, $content);
   		 	return $result;
   		 }
   		 
   		  
   		 
   		 
   		 private function receiveText($object)
   		 {
   		 	if (!empty($object->Recognition)){
   		 		$keyword = trim($object->Recognition);
   		 		$mediaid = trim($object->MediaID);
   		 	}else{
   		 		$keyword = trim($object->Content);
   		 	}
   		 
   		 
   		 
   		 	//  $keyword = trim($object->Content);
   		 	 
   		 
   		 
   		 	if (strstr($keyword,"授权")){
   		 		 
   		 		$content = '<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx580911443e70184b&redirect_uri=http://znss.applinzi.com/oauth2_openid.php&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect">单击体验授权</a>';
   		 
   		 
   		 
   		 	}
   		 	else if(strstr($keyword,"weui")){
   		 		$content='<a href="http://1.znss.applinzi.com/dist/example/index.html">测试weui</a>';
   		 	}
   		 
   		 	else if(strstr($keyword,"本地测试")){
   		 		$content='<a href="http://192.168.1.105:8080/TestReport">测试本地</a>';
   		 	}
   		 
   		 	else if(strstr($keyword,"web")){
   		 		$content='<a href="http://1.znss.applinzi.com/dist/example/testweb.html">测试web</a>';
   		 	}
   		 
   		 	else if(strstr($keyword,"交通")){
   		 		$content[] = array("Title" =>"交通信息","Description" =>"", "PicUrl" =>"", "Url" =>"http://m.8684.cn/bus");
   		 		$content[] = array("Title" =>"【公交线路】\n全车公交查询", "Description" =>"", "PicUrl" =>"http://photo.candou.com/ai/114/09caed4a27c56000bb870c68ab028850", "Url" =>"http://m.8684.cn/shenzhen_bus");
   		 		$content[] = array("Title" =>"【汽车班次】\n长途汽车班车", "Description" =>"", "PicUrl" =>"http://photo.candou.com/i/175/4951bd2a2f368cafc5ad09ff95ce591d", "Url" =>"http://touch.trip8080.com/");
   		 		$content[] = array("Title" =>"【火车时刻】\n火车时刻查询", "Description" =>"", "PicUrl" =>"http://photo.candou.com/ai/114/26a9407dcedda5f4a30b195f78ec3680", "Url" =>"http://m.ctrip.com/html5/Trains/");
   		 		$content[] = array("Title" =>"【飞 机 票】\n机票查询", "Description" =>"", "PicUrl" =>"http://photo.candou.com/i/175/a1bd6303a8bde7da50166aa8dafb7568", "Url" =>"http://touch.qunar.com/h5/flight/");
   		 		$content[] = array("Title" =>"【城市路况】\n重点城市实时路况", "Description" =>"", "PicUrl" =>"http://photo.candou.com/i/175/0d8488edf94574651d048025596a6190", "Url" =>"http://dp.sina.cn/dpool/tools/citytraffic/city.php");
   		 		$content[] = array("Title" =>"【违章查询】\n全国违章查询", "Description" =>"", "PicUrl" =>"http://g.hiphotos.bdimg.com/wisegame/pic/item/9e1f4134970a304eab30503cd0c8a786c8175ce2.jpg", "Url" =>"http://app.eclicks.cn/violation2/webapp/index?appid=10");
   		 	}
   		 
   		 
   		 	else{//$content = "你发送的是文本，内容为：".$object->Content;
   		 
   		 		/* require_once('music.php');
   		 		 $content = getMusicInfo($keyword);
   		 		 $result = $this->transmitMusic($object, $content);*/
   		 
   		 		include_once("xiaoi.php");
   		 		$content = getXiaoiInfo($object->FromUserName, $keyword);
   		 		//return $result;
   		 	}
   		 	if(is_array($content)){
   		 		$result = $this->transmitNews($object, $content);
   		 	}else{
   		 		$result = $this->transmitText($object, $content);
   		 	}
   		 	return $result;
   		 }
   		 
   		 
   		 private function receiveImage($object)
   		 {
   		 	/* $content = "你发送的是图片，地址为：".$object->PicUrl;
   		 	 $result = $this->transmitText($object, $content);
   		 	 return $result;*/
   		 
   		 	include_once('faceplusplus.php');
   		 	$content = getImageInfo((string)$object->PicUrl);
   		 	$result = $this->transmitText($object, $content);
   		 	return $result;
   		 }
   		 
   		 
   		 /*
   		  * 回复图片消息
   		  */
   		 private function transmitImage($object, $imageArray)
   		 {
   		 	$itemTpl = "<Image>
    <MediaId><![CDATA[%s]]></MediaId>
</Image>";
   		 
   		 	$item_str = sprintf($itemTpl, $imageArray['MediaId']);
   		 
   		 	$textTpl = "<xml>
   		 	<ToUserName><![CDATA[%s]]></ToUserName>
   		 	<FromUserName><![CDATA[%s]]></FromUserName>
   		 	<CreateTime>%s</CreateTime>
   		 	<MsgType><![CDATA[image]]></MsgType>
   		 	$item_str
   		 	</xml>";
   		 
   		 	$result = sprintf($textTpl, $object->FromUserName, $object->ToUserName, time());
   		 	return $result;
   		 }
   		  
   		 
   		 //回复音乐
   		 private function transmitMusic($object, $musicArray)
   		 {
   		 	$itemTpl = "<Music>
    <Title><![CDATA[%s]]></Title>
    <Description><![CDATA[%s]]></Description>
    <MusicUrl><![CDATA[%s]]></MusicUrl>
    <HQMusicUrl><![CDATA[%s]]></HQMusicUrl>
</Music>";
   		 
   		 	$item_str = sprintf($itemTpl, $musicArray['Title'], $musicArray['Description'], $musicArray['MusicUrl'], $musicArray['HQMusicUrl']);
   		 
   		 	$textTpl = "<xml>
   		 	<ToUserName><![CDATA[%s]]></ToUserName>
   		 	<FromUserName><![CDATA[%s]]></FromUserName>
   		 	<CreateTime>%s</CreateTime>
   		 	<MsgType><![CDATA[music]]></MsgType>
   		 	$item_str
   		 	</xml>";
   		 
   		 	$result = sprintf($textTpl, $object->FromUserName, $object->ToUserName, time());
   		 	return $result;
   		 }
   		 
   		 
   		 
   		 
   		 private function transmitNews($object, $arr_item)//新闻格式消息
   		 {
   		 	if(!is_array($arr_item))
   		 		return;
   		 
   		 	$itemTpl = "    <item>
        <Title><![CDATA[%s]]></Title>
        <Description><![CDATA[%s]]></Description>
        <PicUrl><![CDATA[%s]]></PicUrl>
        <Url><![CDATA[%s]]></Url>
    </item>
";
   		 	$item_str = "";
   		 	foreach ($arr_item as $item)
   		 		$item_str .= sprintf($itemTpl, $item['Title'], $item['Description'], $item['PicUrl'], $item['Url']);
   		 
   		 	$newsTpl = "<xml>
   		 	<ToUserName><![CDATA[%s]]></ToUserName>
   		 	<FromUserName><![CDATA[%s]]></FromUserName>
   		 	<CreateTime>%s</CreateTime>
   		 	<MsgType><![CDATA[news]]></MsgType>
   		 	<Content><![CDATA[]]></Content>
   		 	<ArticleCount>%s</ArticleCount>
   		 	<Articles>
   		 	$item_str</Articles>
   		 	</xml>";
   		 
   		 	$result = sprintf($newsTpl, $object->FromUserName, $object->ToUserName, time(), count($arr_item));
   		 	return $result;
   		 }
   		 
   		 private function transmitText($object, $content)//向用户回复消息
   		 {
   		 	$textTpl = "<xml>
<ToUserName><![CDATA[%s]]></ToUserName>
<FromUserName><![CDATA[%s]]></FromUserName>
<CreateTime>%s</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[%s]]></Content>
</xml>";
   		 	$result = sprintf($textTpl, $object->FromUserName, $object->ToUserName, time(), $content);
   		 	return $result;
   		 }
   		 //https请求（支持GET和POST）
   		 protected function https_request($url, $data = null)
   		 {
   		 	$curl = curl_init();
   		 	curl_setopt($curl, CURLOPT_URL, $url);
   		 	curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE);
   		 	curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, FALSE);
   		 	if (!empty($data)){
   		 		curl_setopt($curl, CURLOPT_POST, 1);
   		 		curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
   		 	}
   		 	curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
   		 	$output = curl_exec($curl);
   		 	curl_close($curl);
   		 	return $output;
   		 }
   		 
}
?>
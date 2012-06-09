package test

import twitter4j._
import twitter4j.TwitterFactory
import auth.AccessToken
import twitter4j.ResponseList 

class TwitterOAuthSampleWithAccessToken {
 
 	def oauthTest = {

    
    // http://twitter.com/oauth_clients/new にてアプリケーションを登録した際の
    // Consumer key と Consumer secret を使用
    val consumerKey = "PXstu9lHsgTlQ9bDuSbDQ"; // Consumer key をセット
    val consumerSecret = "RbXcJDt5MsbVnHxDxWbRmZ7MPRJ3atPVZmEAS3c7mE"; // Consumer secret をセット
    
    // TwitterOAuthAccessTokenGetter で取得した Token と TokenSecret を使用
    val token = "95870366-41fsWZsj1XF8zOlYAJJ8V1FUX3rPXIOcKevW74eTC"; // Token をセット
    val tokenSecret = "cljUVWlcwtnF67V05dUlPKg3BWZxjvvQA80pgP0NrY"; // TokenSecret をセット
    
    // Twitterオブジェクトを生成
    val factory = new TwitterFactory();
    val accessToken = new AccessToken(token, tokenSecret);
    val twitter = factory.getInstance()
    twitter.setOAuthConsumer(consumerKey, consumerSecret)
    twitter.setOAuthAccessToken(accessToken)
 
    // ツイートを出力するサンプル
    val statusList = twitter.getUserTimeline();
    for(i <- 0 to statusList.size()-1){
      val s = statusList.get(i);
      val text = s.getText();
      System.out.println(text);
    }
  }
 
}

package info.wataboh

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
    val token = "95870366-aAhuCI9Cw52ajE2F5vp4ovgrMdUX2CI4xfjKzA"; // Token をセット
    val tokenSecret = "ju5YNWK2v5917lE2HTr9P50v6aDVC5ELknLjHA"; // TokenSecret をセット
    
    // Twitterオブジェクトを生成
    val factory = new TwitterFactory();
    val accessToken = new AccessToken(token, tokenSecret);
    val twitter = factory.getInstance()
   twitter.setOAuthConsumer(consumerKey, consumerSecret)
   twitter.setOAuthAccessToken(accessToken)
   
   //twitter.updateStatus("testtest")
 
   // ツイートを出力するサンプル
    val statusList = twitter.getDirectMessages()
    for(i <- 0 to statusList.size()-1){
      val s = statusList.get(i);
      val text = s.getText();
      System.out.println(text);
    }
  }
 
}

package info.wataboh

import java.io._
import twitter4j._
import twitter4j.TwitterFactory
import auth.AccessToken

class TwitterOAuthAccessTokenGetter {
  
  def operate() = {
    
    // http://twitter.com/oauth_clients/new にてアプリケーションを登録した際の
    // Consumer key と Consumer secret を使用
    val consumerKey = "PXstu9lHsgTlQ9bDuSbDQ" // Consumer key をセット
    val consumerSecret = "RbXcJDt5MsbVnHxDxWbRmZ7MPRJ3atPVZmEAS3c7mE" // Consumer secret をセット
    
    // 認証用URLを取得
    val factory = new TwitterFactory()
    val twitter = factory.getInstance()
    twitter.setOAuthConsumer(consumerKey, consumerSecret)
    val requestToken = twitter.getOAuthRequestToken()
    val authorizationURL = requestToken.getAuthorizationURL()
    
    System.out.println(authorizationURL + " にウェブブラウザでアクセスして認証を許可してください。")
    System.out.println("認証を許可したらウェブブラウザにPINコードが表示されます。")
    System.out.println("PINコードを入力して[Enter]キーを押してください。")
    
    // 入力待ち
    var r = new BufferedReader(new InputStreamReader(System.in))
    var pin = r.readLine()
    
    // AccessTokenオブジェクトを生成 
    var accessToken:AccessToken = null
    if(pin.length() > 0){
      accessToken = twitter.getOAuthAccessToken(requestToken, pin)
    }else{
      accessToken = twitter.getOAuthAccessToken()
    }
 
    // 認証情報を表示
    val userId = twitter.verifyCredentials().getId()
    val token = accessToken.getToken()
    val tokenSecret = accessToken.getTokenSecret()
    System.out.println("UserId=" + userId)
    System.out.println("Token=" + token)
    System.out.println("TokenSecret=" + tokenSecret)
  }
}

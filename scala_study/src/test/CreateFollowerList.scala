package test


import twitter4j.IDs
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.UserList
import twitter4j.auth.AccessToken


class CreateFollowerList {

    /**
     * twitterの自分のフォロワーのリストを作成する
     * 
     * @param args
     * @throws Exception 
     */
    def makeList = {

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

    val cursor:Long = -1
        // フォロワーのIDを取得
        val followersIds:IDs = twitter.getFollowersIDs("wataboh",cursor)

        // リスト作成 true：公開リスト false：非公開リスト
      val createdList = twitter.createUserList("test", true, "test");

        val createdListId = createdList.getId();
        for (each <- followersIds.getIDs()) {
           twitter.addUserListMember(createdListId, each);
            System.out.println(each);
        }
        // 残りのAPIリクエスト数を表示
        System.out.println(twitter.getRateLimitStatus().getRemainingHits());
    }

}
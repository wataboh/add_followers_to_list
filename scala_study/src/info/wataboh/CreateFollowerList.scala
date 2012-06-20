package info.wataboh

import twitter4j.IDs
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.UserList
import twitter4j.auth.AccessToken

import scala.collection.JavaConversions._

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
    val token = "95870366-aAhuCI9Cw52ajE2F5vp4ovgrMdUX2CI4xfjKzA"; // Token をセット
    val tokenSecret = "ju5YNWK2v5917lE2HTr9P50v6aDVC5ELknLjHA"; // TokenSecret をセット

    // Twitterオブジェクトを生成
    val factory = new TwitterFactory();
    val accessToken = new AccessToken(token, tokenSecret);
    val twitter = factory.getInstance()
    twitter.setOAuthConsumer(consumerKey, consumerSecret)
    twitter.setOAuthAccessToken(accessToken)

    var cursor: Long = -1
    // フォロワーのIDを取得
    val followersIds: IDs = twitter.getFollowersIDs("wataboh", cursor)

    val lists = twitter.getUserLists("wataboh", cursor)

    var testlist: UserList = null

    for (each <- lists) {
      printf("%s\n", each)
      if (each.getId() == 72087192) {
        testlist = each
        each.getId()
        printf("test\n")
      }
    }

    val userListBuffer = new collection.mutable.ListBuffer[Long]

    do {
      val tempUserList = twitter.getUserListMembers(72087192, cursor)
      for (each <- tempUserList) {
        userListBuffer.append(each.getId())
      }

      cursor = tempUserList.getNextCursor() //次のcursorを取得
//      } while ( 0 != 0)
    } while (cursor != 0);

    for(each <- userListBuffer) printf("%d\n",each)
    for(each <- followersIds.getIDs()) printf("%d\n",each.toLong)

    //リストのチェック
    //フォローされているが、ユーザリストに入っていないもの
    for(each <- followersIds.getIDs()){
      if(userListBuffer.exists(_ != each)) printf("%d ",each.toLong)
    }

    printf("\n%d\n%d\n",followersIds.getIDs().length,userListBuffer.length)
    //ユーザリストに入っているがフォローされていないもの
    for(each <- userListBuffer){
      if(followersIds.getIDs().exists(_ != each)) print("%d ",each.toLong)
    }

    //どうも比較が全くうまくいっていないみたいだ．

    // リスト作成 true：公開リスト false：非公開リスト
    //      val createdList = twitter.createUserList("privatelist", false, "test");

    //        val createdListId = createdList.getId();
    //        for (each <- followersIds.getIDs()) {
    //           twitter.addUserListMember(createdListId, each);
    //            System.out.println(each);
    //        }
    // 残りのAPIリクエスト数を表示
    System.out.println(twitter.getRateLimitStatus().getRemainingHits());
  }

}
package test

object Main {
	
	def main(args:Array[String])={
	  println("Hello World")
	 
	  
	  val t = new TwitterOAuthAccessTokenGetter()
	  
	  //t.operate()
	  
	  
	  val tweet = new TwitterOAuthSampleWithAccessToken()
	  tweet.oauthTest
	 }

	  
}
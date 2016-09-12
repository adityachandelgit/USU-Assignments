__author__ = 'Aditya'

import tweepy
import csv

auth = tweepy.OAuthHandler("paHY2eshhxrBAwY6laiZCSeVy", "7lH0BTCPwFtJvcavIRjXWkCeq2NgHLSC7aQ8phTIxDKxbTQH7S")
auth.set_access_token("4493024472-Ip6tGzAID8uq9JznDYwM5ecdiN92obmhqV6yi5c", "dC1UeiZOXgcchMtd3cg2obuNJtqOLwtvSS3PYjdiUph33")

api = tweepy.API(auth)
places = api.geo_search(query="USA", granularity="country")
place_id = places[0].id

max_tweets = 150

searched_tweets = []
last_id = -1

csvFile = open('D:\\z.csv', 'a')
csvWriter = csv.writer(csvFile)

while len(searched_tweets) < max_tweets:
    count = max_tweets - len(searched_tweets)
    for tweet in tweepy.Cursor(api.search, q="place:%s trump" % place_id, lang="en", count=count, max_id=str(last_id - 1)).items():
        csvWriter.writerow([tweet.created_at, tweet.id, tweet.text.encode('utf-8', 'ignore'), tweet.author._json['id'], tweet.author._json['screen_name'].encode('utf-8', 'ignore'), tweet.author._json['lang'].encode('utf-8', 'ignore'), tweet.author._json['friends_count'], tweet.author._json['followers_count'], tweet.author._json['location'].encode('utf-8', 'ignore')])
        print tweet.created_at, tweet.text.encode('utf-8', 'ignore'), tweet.author._json['screen_name'].encode('utf-8', 'ignore'), tweet.author._json['lang'].encode('utf-8', 'ignore'), tweet.author._json['friends_count'], tweet.author._json['followers_count'], tweet.author._json['location'].encode('utf-8', 'ignore')


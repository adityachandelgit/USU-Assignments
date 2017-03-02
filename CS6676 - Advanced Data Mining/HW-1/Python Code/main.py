import csv
import json
import sys
from random import randint

import jsonpickle
import numpy as np
import preprocessor
import tweepy
from sklearn.cluster import KMeans
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis
from sklearn.ensemble import RandomForestClassifier, AdaBoostClassifier
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.tree import DecisionTreeClassifier
from textblob import TextBlob

FILENAME_NO_DUPES = "nodupes-tweets.csv"
FILENAME_CLEANED_TWEETS = 'cleaned_tweets.csv'
FILENAME_SAVED_TWEETS = 'raw_tweets.txt'


def tweet_downloader(api_key, api_secret, search_query, output_filename, lang, geocode, since):
    auth = tweepy.AppAuthHandler(api_key, api_secret)
    api = tweepy.API(auth, wait_on_rate_limit=True, wait_on_rate_limit_notify=True)
    if not api:
        print ("Can't Authenticate")
        sys.exit(-1)
    search_query = search_query
    max_tweets = 10000000
    tweets_per_qry = 100
    f_name = output_filename
    since_id = None
    max_id = -1L
    tweet_count = 0
    print("Downloading max {0} tweets".format(max_tweets))
    with open(f_name, 'w') as f:
        while tweet_count < max_tweets:
            try:
                if max_id <= 0:
                    if not since_id:
                        new_tweets = api.search(q=search_query, lang=lang, geocode=geocode,
                                                count=tweets_per_qry, since=since)
                    else:
                        new_tweets = api.search(q=search_query, count=tweets_per_qry, lang=lang,
                                                geocode=geocode,
                                                since_id=since_id, since=since)
                else:
                    if not since_id:
                        new_tweets = api.search(q=search_query, count=tweets_per_qry, lang=lang,
                                                geocode=geocode,
                                                max_id=str(max_id - 1), since=since)
                    else:
                        new_tweets = api.search(q=search_query, count=tweets_per_qry, lang=lang,
                                                geocode=geocode,
                                                max_id=str(max_id - 1),
                                                since_id=since_id, since=since)
                if not new_tweets:
                    print("No more tweets found")
                    break
                for tweet in new_tweets:
                    f.write(jsonpickle.encode(tweet._json, unpicklable=False) +
                            '\n')
                tweet_count += len(new_tweets)
                print("Downloaded {0} tweets".format(tweet_count))
                max_id = new_tweets[-1].id
            except tweepy.TweepError as e:
                print("some error : " + str(e))
                break
    print ("Downloaded {0} tweets, Saved to {1}".format(tweet_count, f_name))


def fields_selection_cleaning_and_sentiment(input_file, output_file):
    print 'Pre-processing the tweets! Please wait...'
    writer = csv.writer(open(output_file, 'wb'))
    writer.writerow(['Created_At',
                     'Tweet_Id',
                     'Text',
                     'GeoLocation',
                     'Coordinates',
                     'User_Id',
                     'User_Name',
                     'User_Location',
                     'Language',
                     'Time_Zone',
                     'Country',
                     'Friends_Count',
                     'Followers_Count',
                     'Sentiment_Polarity',
                     'Label',
                     'Location_Id'])
    with open(input_file) as f:
        for line in f:
            data = json.loads(line)
            if data['lang'] == 'en' \
                    and data['user'] is not None \
                    and data['user']['location'] != '':
                if data['place'] is not None:
                    country = data['place']['country']
                else:
                    country = ''
                try:
                    cleaned_tweet = preprocessor.clean(data['text'])
                    location = clean_location(data['user']['location'])[0]
                    location_id = clean_location(data['user']['location'])[1]
                    sentiment = TextBlob(cleaned_tweet).sentiment.polarity
                    if sentiment < 0:
                        label = 0
                    elif sentiment == 0:
                        label = 1
                    else:
                        label = 2
                    if location != 'NA':
                        writer.writerow([data['created_at'],
                                         data['id'],
                                         cleaned_tweet,
                                         data['geo'],
                                         data['coordinates'],
                                         data['user']['id'],
                                         data['user']['name'],
                                         location,
                                         data['lang'],
                                         data['user']['time_zone'],
                                         country,
                                         data['user']['friends_count'],
                                         data['user']['followers_count'],
                                         sentiment,
                                         label,
                                         location_id
                                         ])
                except Exception:
                    pass

    print 'Tweets pre-processing completed.\n'


def clean_location(location):
    if 'AL' in location:
        return ['AL', 1]
    elif 'AK' in location:
        return ['AK', 2]
    elif 'AZ' in location:
        return ['AZ', 3]
    elif 'AR' in location:
        return ['AR', 4]
    elif 'CA' in location:
        return ['CA', 5]
    elif 'CO' in location:
        return ['CO', 6]
    elif 'CT' in location:
        return ['CT', 7]
    elif 'DE' in location:
        return ['DE', 8]
    elif 'DC' in location:
        return ['DC', 9]
    elif 'FL' in location:
        return ['FL', 10]
    elif 'GA' in location:
        return ['GA', 11]
    elif 'HI' in location:
        return ['HI', 12]
    elif 'ID' in location:
        return ['ID', 13]
    elif 'IL' in location:
        return ['IL', 14]
    elif 'IN' in location:
        return ['IN', 15]
    elif 'IA' in location:
        return ['IA', 16]
    elif 'KS' in location:
        return ['KS', 17]
    elif 'KY' in location:
        return ['KY', 18]
    elif 'LA' in location:
        return ['LA', 19]
    elif 'ME' in location:
        return ['ME', 20]
    elif 'MD' in location:
        return ['MD', 21]
    elif 'MA' in location:
        return ['MA', 22]
    elif 'MI' in location:
        return ['MI', 23]
    elif 'MN' in location:
        return ['MN', 24]
    elif 'MS' in location:
        return ['MS', 25]
    elif 'MO' in location:
        return ['MO', 26]
    elif 'MT' in location:
        return ['MT', 27]
    elif 'NE' in location:
        return ['NE', 28]
    elif 'NV' in location:
        return ['NV', 29]
    elif 'NH' in location:
        return ['NH', 30]
    elif 'NJ' in location:
        return ['NJ', 31]
    elif 'NM' in location:
        return ['NM', 32]
    elif 'NY' in location:
        return ['NY', 33]
    elif 'NC' in location:
        return ['NC', 34]
    elif 'ND' in location:
        return ['ND', 35]
    elif 'OH' in location:
        return ['OH', 36]
    elif 'OK' in location:
        return ['OK', 37]
    elif 'OR' in location:
        return ['OR', 38]
    elif 'PA' in location:
        return ['PA', 39]
    elif 'RI' in location:
        return ['RI', 40]
    elif 'SC' in location:
        return ['SC', 41]
    elif 'SD' in location:
        return ['SD', 42]
    elif 'TN' in location:
        return ['TN', 43]
    elif 'TX' in location:
        return ['TX', 44]
    elif 'UT' in location:
        return ['UT', 45]
    elif 'VT' in location:
        return ['VT', 46]
    elif 'VA' in location:
        return ['VA', 47]
    elif 'WA' in location:
        return ['WA', 48]
    elif 'WV' in location:
        return ['WV', 49]
    elif 'WI' in location:
        return ['WI', 50]
    elif 'WY' in location:
        return ['WY', 51]
    else:
        return ['NA', -1]


def remove_duplicate_tweets():
    print 'Removing duplicate tweets! Please wait...'
    with open(FILENAME_CLEANED_TWEETS, 'rb') as f:
        reader = csv.reader(f)
        tweets = list(reader)
    seen = set()
    uniques = [x for x in tweets if x[2] not in seen and not seen.add(x[2])]
    with open("%s" % FILENAME_NO_DUPES, "wb") as f:
        writer = csv.writer(f)
        writer.writerows(uniques)
    print 'Duplicate tweets removed.\n'


def k_means(input_file, k):
    print 'Running K-means on tweets! Please wait...'
    documents = []
    with open(input_file, 'rb') as f:
        reader = csv.reader(f)
        for tweet in reader:
            try:
                documents.append(tweet[2])
            except:
                pass
    vectorizer = TfidfVectorizer(stop_words='english')
    X = vectorizer.fit_transform(documents)
    true_k = k
    model = KMeans(n_clusters=true_k, init='k-means++', max_iter=100, n_init=1)
    model.fit(X)
    print("Top terms per cluster:")
    order_centroids = model.cluster_centers_.argsort()[:, ::-1]
    terms = vectorizer.get_feature_names()
    for i in range(true_k):
        print "Cluster %d:" % i,
        for ind in order_centroids[i, :10]:
            print ' %s' % terms[ind],
        print
    print 'Finished running K-means.\n'


def tweet_classifier(input_file):
    print 'Running tweet classifier! Please wait...'
    data = []
    labels = []
    with open(input_file, 'rb') as f:
        reader = csv.reader(f)
        next(reader, None)
        for tweet in reader:
            try:
                data.append([int(tweet[11]), int(tweet[12]), int(tweet[11])/int(tweet[12]), float(int(tweet[15]))])
                labels.append(int(tweet[14]))
            except:
                pass
    data = np.array(data)
    labels = np.array(labels)
    X_train, X_test, y_train, y_test = train_test_split(data, labels, test_size=0.4, random_state=randint(0, 100))
    names = ["Nearest Neighbors",
             "Decision Tree", "Random Forest", "Neural Net", "AdaBoost",
             "Naive Bayes", "QDA"]
    classifiers = [
        KNeighborsClassifier(3),
        DecisionTreeClassifier(max_depth=5),
        RandomForestClassifier(max_depth=5, n_estimators=10, max_features=1),
        MLPClassifier(alpha=1),
        AdaBoostClassifier(),
        GaussianNB(),
        QuadraticDiscriminantAnalysis()]
    for idx, val in enumerate(classifiers):
        clf = val.fit(X_train, y_train)
        print names[idx] + ': ' + str(clf.score(X_test, y_test))
    print 'Finished running the classification algorithms.\n'


# ////////////////////////////////////////////////////////////////////////////////////////////////
# Run all the methods - Tweet Collection, Pre-Processing, Clustering, Classification
# ////////////////////////////////////////////////////////////////////////////////////////////////

# tweet_downloader('', '', 'Donald Trump', FILENAME_SAVED_TWEETS, 'en', '39.198205,-97.646484,2000mi', '2016-11-08')
fields_selection_cleaning_and_sentiment(FILENAME_SAVED_TWEETS, FILENAME_CLEANED_TWEETS)
remove_duplicate_tweets()
k_means(FILENAME_NO_DUPES, 10)
# tweet_classifier(FILENAME_NO_DUPES)

import csv
import json
import string
from collections import OrderedDict
from time import time

import matplotlib.pyplot as plt
import nltk
from nltk.corpus import stopwords
from sklearn.decomposition import LatentDirichletAllocation
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from wordcloud import WordCloud


def load_business(city):
    print 'Loading business from JSON to list...'
    t0 = time()
    business = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            json_line = json.loads(line)
            if json_line['categories'] and 'Restaurants' in json_line['categories'] and json_line['city'] == city:
                business[json_line['business_id']] = json_line
    print("done in %0.3fs." % (time() - t0))
    return business


def load_checkins(business):
    print 'Loading checkins from JSON to list...'
    t0 = time()
    checkins = {}
    with open('D://Yelp/yelp_academic_dataset_checkin.json') as f2:
        for line in f2:
            json_line = json.loads(line)
            if json_line['business_id'] in business:
                total_checkins = 0
                for t in json_line['time']:
                    total_checkins += int(t.split(':')[1])
                    checkins[json_line['business_id']] = total_checkins
    print("done in %0.3fs." % (time() - t0))
    return checkins


def load_reviews(business):
    print 'Loading reviews from JSON to list...'
    t0 = time()
    reviews = {}
    with open('D://Yelp/yelp_academic_dataset_review.json') as f2:
        for line in f2:
            json_line = json.loads(line)
            if json_line['business_id'] in business:
                if json_line['business_id'] not in reviews:
                    reviews[json_line['business_id']] = [json_line['text']]
                else:
                    review = reviews[json_line['business_id']]
                    review.append(json_line['text'])
                    reviews[json_line['business_id']] = review
    print("done in %0.3fs." % (time() - t0))
    return reviews


def count_checkins_reviews_for_restaurant_types(business, checkins, reviews):
    print 'Calculating count for restaurant types...'
    t0 = time()
    count_types = {}
    for business_id, business_meta in business.iteritems():
        for category in business_meta['categories']:
            review_plus_checkin_count = (checkins[business_id] if business_id in checkins else 0) + (len(reviews[business_id]) if business_id in reviews else 0)
            if category not in count_types:
                count_types[category] = review_plus_checkin_count
            else:
                count = count_types[category]
                count += review_plus_checkin_count
                count_types[category] = count

    s = OrderedDict(sorted(count_types.items(), key=lambda t: t[1], reverse=True))
    print 'Top restaurant types are:'
    for r in s:
        print str(r) + " : " + str(count_types[r])
    print("done in %0.3fs." % (time() - t0))
    return s


def export_top10_food_location(all_business, city):
    print 'Saving top 10 food location to csv...'
    t0 = time()
    # Got these lists from calculate_popular_food_types()
    charlotte_top = ['Mexican', 'Southern', 'Italian', 'Japanese', 'Chinese', 'Vietnamese', 'Latin American', 'Thai', 'Greek', 'French']
    pittsburgh_top = ['Italian', 'Mexican', 'Chinese', 'Thai', 'Japanese', 'Mediterranean', 'Latin American', 'French', 'Middle Eastern', 'Indian']

    top_business = []
    for business_id, business_meta in all_business.iteritems():
        if 4.5 <= business_meta['stars'] <= 5.0:
            for cat in business_meta['categories']:
                if city == 'Charlotte':
                    top_list = charlotte_top
                elif city == 'Pittsburgh':
                    top_list = pittsburgh_top
                else:
                    raise ValueError('Invalid city')
                if cat in top_list:
                    top_business.append([
                        business_meta['name'].encode('ascii', 'ignore').decode('ascii'),
                        business_meta['city'],
                        business_meta['latitude'],
                        business_meta['longitude'],
                        business_meta['stars'],
                        cat
                    ])
    with open('output' + city + '.csv', 'wb') as f:
        writer = csv.writer(f)
        writer.writerows(top_business)
    print("done in %0.3fs." % (time() - t0))


def most_used_words_in_reviews(business, reviews, stars_range, city):
    print 'Getting most used words in reviews...'
    t0 = time()
    all_words_in_n_star_reviews = []
    for business_id, business_meta in business.iteritems():
        if stars_range[0] <= business_meta['stars'] <= stars_range[1]:
            if business_id in reviews:
                words = []
                for a_review in reviews[business_id]:
                    words.extend(nltk.word_tokenize(a_review))
                filtered_words = [word for word in words if word.lower() not in stopwords.words('english') + list(string.punctuation) + ["n't"]]
                all_words_in_n_star_reviews.extend(filtered_words)

    wordcloud = WordCloud(width=1920, height=1080).generate(' '.join(all_words_in_n_star_reviews))
    plt.figure(figsize=(39.6, 21.6))
    plt.imshow(wordcloud)
    plt.axis("off")
    plt.savefig(city + '_' + str(stars_range[0]) + '-' + str(stars_range[1]) + '.png')
    print("done in %0.3fs." % (time() - t0))


def get_chinese_restaurants():
    print 'Adding chinese restaurants from JSON to list...'
    t0 = time()
    chinese = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            line_json = json.loads(line)
            if line_json['categories']:
                for category in line_json['categories']:
                    if category == 'Chinese':
                        chinese[line_json['business_id']] = line_json
    print("done in %0.3fs." % (time() - t0))
    return chinese


def get_chinese_restaurants_reviews(chinese):
    print 'Getting reviews of all the chinese restaurants...'
    t0 = time()
    reviews = []
    with open('D://Yelp/yelp_academic_dataset_review.json') as f2:
        for line in f2:
            line_json = json.loads(line)
            if line_json['business_id'] in chinese:
                reviews.append(line_json['text'])
    print("done in %0.3fs." % (time() - t0))
    return reviews


def print_top_words(model, feature_names, n_top_words):
    for topic_idx, topic in enumerate(model.components_):
        print("Topic #%d:" % topic_idx)
        print(" ".join([feature_names[i]
                        for i in topic.argsort()[:-n_top_words - 1:-1]]))
    print()


def calculate_lda_for_chinese_restaurants():
    print 'Calculating LDA...'
    n_features = 1000
    n_topics = 10
    n_top_words = 5

    t0 = time()
    chinese_reviews = get_chinese_restaurants_reviews(get_chinese_restaurants())
    print("done in %0.3fs." % (time() - t0))

    # Use tf-idf features for Non-negative matrix factorization.
    print("Extracting tf-idf features for NMF...")
    tfidf_vectorizer = TfidfVectorizer(max_df=0.95,
                                       min_df=2,
                                       max_features=n_features,
                                       stop_words='english')
    t0 = time()
    tfidf = tfidf_vectorizer.fit_transform(chinese_reviews)
    print("done in %0.3fs." % (time() - t0))

    # Use tf (raw term count) features for LDA.
    print("Extracting tf features for LDA...")
    tf_vectorizer = CountVectorizer(max_df=0.95,
                                    min_df=2,
                                    max_features=n_features,
                                    stop_words='english')
    t0 = time()
    tf = tf_vectorizer.fit_transform(chinese_reviews)
    print("done in %0.3fs." % (time() - t0))

    # Fit LDA model for tf features
    print("Fitting LDA models with tf features, " "n_features=%d..." % n_features)
    lda = LatentDirichletAllocation(n_topics=n_topics,
                                    max_iter=5,
                                    learning_method='online',
                                    learning_offset=50.,
                                    random_state=0)
    t0 = time()
    lda.fit(tf)
    print("done in %0.3fs." % (time() - t0))

    print("\nTopics in LDA model:")
    tf_feature_names = tf_vectorizer.get_feature_names()
    print_top_words(lda, tf_feature_names, n_top_words)


if __name__ == "__main__":
    # -------------- Task I.1 for Charlotte -----------------
    # city = 'Charlotte'
    # business = load_business(city)
    # checkins = load_checkins(business)
    # reviews = load_reviews(business)
    # type_count = count_checkins_reviews_for_restaurant_types(business, checkins, reviews)

    # -------------- Task I.2 for Charlotte -----------------
    # export_top10_food_location(business, city)

    # -------------- Task I.3 for Charlotte -----------------
    # star_ranges = [[0.0, 1.5], [2.0, 3.5], [4.5, 5.0]]
    # for star_range in star_ranges:
    #     most_used_words_in_reviews(business, reviews, star_range, city)

    # -------------- Task I.1 for Pittsburgh -----------------
    # city = 'Pittsburgh'
    # business = load_business(city)
    # checkins = load_checkins(business)
    # reviews = load_reviews(business)
    # type_count = count_checkins_reviews_for_restaurant_types(business, checkins, reviews)

    # -------------- Task I.2 for Pittsburgh -----------------
    # export_top10_food_location(business, city)

    # -------------- Task I.3 for Pittsburgh -----------------
    # star_ranges = [[0.0, 1.5], [2.0, 3.5], [4.5, 5.0]]
    # for star_range in star_ranges:
    #     most_used_words_in_reviews(business, reviews, star_range, city)

    # -------------- Task II.1 -----------------
    # chinese_restaurants = get_chinese_restaurants()
    # chinese_reviews = load_reviews(chinese_restaurants)
    # most_used_words_in_reviews(chinese_restaurants, chinese_reviews, [0.0, 5.0], 'All-Cities-Chinese')

    # -------------- Task II.2 -----------------
    calculate_lda_for_chinese_restaurants()

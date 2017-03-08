import csv
import json
import string
from collections import Counter
from collections import OrderedDict
from time import time

import matplotlib.pyplot as plt
import nltk
from nltk.corpus import stopwords
from sklearn.decomposition import LatentDirichletAllocation
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from wordcloud import WordCloud


def load_checkins_to_dictionary():
    print 'Loading checkins from JSON to list...'
    all_checkins = {}
    with open('D://Yelp/yelp_academic_dataset_checkin.json') as f2:
        for line in f2:
            json_line = json.loads(line)
            total_checkins = 0
            for t in json_line['time']:
                total_checkins += int(t.split(':')[1])
                all_checkins[json_line['business_id']] = total_checkins
    return all_checkins


def review_count(all_checkins):
    print 'Counting the number of reviews for each restaurant...'
    count_business_reviews = {}
    with open('D://Yelp/yelp_academic_dataset_review.json') as f3:
        for line in f3:
            business_id = json.loads(line)['business_id']
            if business_id in all_checkins:
                if business_id in count_business_reviews:
                    count_business_reviews[business_id] += 1
                else:
                    count_business_reviews[business_id] = 1
    return count_business_reviews


def business_with_checkin_count(all_checkins, city):
    print 'Adding count of checkins to business_id..'
    business_checkins = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            json_line = json.loads(line)
            if json_line['categories'] \
                    and 'Restaurants' in json_line['categories'] \
                    and (json_line['city'] == city):
                if json_line['business_id'] in all_checkins:
                    business_checkins[json_line['business_id']] = [json_line['categories'], all_checkins[json_line['business_id']]]
    print 'For ', city
    return business_checkins


def calculate_popular_food_types(data, count_review):
    print 'Calculating popular restaurant types...'
    popular_food_types = {}
    for business_id, categories_and_checkins in data.iteritems():
        for category in categories_and_checkins[0]:
            if category != 'Restaurants':
                if category in popular_food_types:
                    popular_food_types[category] = popular_food_types[category] + categories_and_checkins[1] + count_review[business_id]
                else:
                    popular_food_types[category] = categories_and_checkins[1] + count_review[business_id]
    s = OrderedDict(sorted(popular_food_types.items(), key=lambda t: t[1], reverse=True))
    print 'Top restaurant types are:'
    for r in s:
        print str(r) + " : " + str(popular_food_types[r])
    return s


def export_top10_food_location(city):
    print 'Saving top 10 food location to csv...'
    # Got these lists from calculate_popular_food_types()
    charlotte_top = ['Mexican', 'Southern', 'Italian', 'Japanese', 'Chinese', 'Vietnamese', 'Latin American', 'Thai', 'Greek', 'French']
    pittsburgh_top = ['Italian', 'Mexican', 'Chinese', 'Thai', 'Japanese', 'Mediterranean', 'Latin American', 'French', 'Middle Eastern', 'Indian']

    with open('D://Yelp/yelp_academic_dataset_business.json') as f5:
        business = []
        for line in f5:
            json_line = json.loads(line)
            if json_line['city'] == city and (4.5 <= json_line['stars'] <= 5.0):
                for cat in json_line['categories']:
                    if city == 'Charlotte':
                        top_list = charlotte_top
                    elif city == 'Pittsburgh':
                        top_list = pittsburgh_top
                    else:
                        raise ValueError('Invalid city')
                    if cat in top_list:
                        business.append([
                            json_line['name'].encode('ascii', 'ignore').decode('ascii'),
                            json_line['city'],
                            json_line['latitude'],
                            json_line['longitude'],
                            json_line['stars'],
                            cat
                        ])
    with open("output.csv", "wb") as f:
        writer = csv.writer(f)
        writer.writerows(business)


def most_used_words_in_reviews(city, stars_range):
    print 'Getting most used words in reviews...'
    business = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            json_line = json.loads(line)
            if json_line['categories'] and 'Restaurants' in json_line['categories'] and (json_line['city'] == city):
                business[json_line['business_id']] = json_line

    business_reviews = {}
    with open('D://Yelp/yelp_academic_dataset_review.json') as f7:
        for line in f7:
            json_line = json.loads(line)
            if json_line['business_id'] in business:
                if json_line['business_id'] in business_reviews:
                    reviews = business_reviews[json_line['business_id']]
                    reviews.append(json_line['text'])
                    business_reviews[json_line['business_id']] = reviews
                else:
                    business_reviews[json_line['business_id']] = []

    all_words_in_n_star_reviews = []
    for key, value in business.iteritems():
        if stars_range[0] <= value['stars'] <= stars_range[1]:
            if key in business_reviews:
                words = []
                for a_review in business_reviews[key]:
                    words.extend(nltk.word_tokenize(a_review))
                filtered_words = [word for word in words if word.lower() not in stopwords.words('english') + list(string.punctuation) + ["n't"]]
                all_words_in_n_star_reviews.extend(filtered_words)

    wordcloud = WordCloud(width=1920, height=1080).generate(' '.join(all_words_in_n_star_reviews))
    plt.figure(figsize=(39.6, 21.6))
    plt.imshow(wordcloud)
    plt.axis("off")
    plt.savefig(city + '-' + str(stars_range[0]) + '-' + str(stars_range[1]) + '.png')


def most_used_words_in_chinese_reviews():
    print 'Started storing chinese restaurants business ids...'
    business = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            json_line = json.loads(line)
            if json_line['categories'] and 'Chinese' in json_line['categories']:
                business[json_line['business_id']] = json_line

    print 'Started storing reviews for chinese restaurants...'
    business_reviews = {}
    with open('D://Yelp/yelp_academic_dataset_review.json') as f7:
        for line in f7:
            json_line = json.loads(line)
            if json_line['business_id'] in business:
                if json_line['business_id'] in business_reviews:
                    reviews = business_reviews[json_line['business_id']]
                    reviews.append(json_line['text'])
                    business_reviews[json_line['business_id']] = reviews
                else:
                    business_reviews[json_line['business_id']] = []

    print 'Started counting top words in reviews...'
    all_words_in_n_star_reviews = []
    for key, value in business.iteritems():
        if key in business_reviews:
            words = []
            for a_review in business_reviews[key]:
                words.extend(nltk.word_tokenize(a_review))
            filtered_words = [word for word in words if word.lower() not in stopwords.words('english') + list(string.punctuation) + ["n't"]]
            all_words_in_n_star_reviews.extend(filtered_words)

    print Counter(all_words_in_n_star_reviews).most_common(50)

    wordcloud = WordCloud(width=1920, height=1080).generate(' '.join(all_words_in_n_star_reviews))
    plt.figure(figsize=(39.6, 21.6))
    plt.imshow(wordcloud)
    plt.axis("off")
    plt.savefig("Chinese-Words" + '.png')


def get_chinese_restaurants():
    print 'Adding chinese restaurants from JSON to list...'
    chinese = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            line_json = json.loads(line)
            if line_json['categories']:
                for category in line_json['categories']:
                    if category == 'Chinese':
                        chinese[line_json['business_id']] = line_json
    return chinese


def get_chinese_restaurants_reviews(chinese):
    print 'Getting reviews of all the chinese restaurants...'
    reviews = []
    with open('D://Yelp/yelp_academic_dataset_review.json') as f2:
        for line in f2:
            line_json = json.loads(line)
            if line_json['business_id'] in chinese:
                reviews.append(line_json['text'])
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
    # -------------- Task I.1 -----------------
    # checkins = load_checkins_to_dictionary()
    # count_reviews = review_count(checkins)
    #
    # business_with_checkins_foods = business_with_checkin_count(checkins, 'Charlotte')
    # pop_food = calculate_popular_food_types(business_with_checkins_foods, count_reviews)
    #
    # business_with_checkins_foods = business_with_checkin_count(checkins, 'Pittsburgh')
    # pop_food = calculate_popular_food_types(business_with_checkins_foods, count_reviews)

    # -------------- Task I.2 -----------------
    # export_top10_food_location('Charlotte')
    # export_top10_food_location('Pittsburgh')

    # -------------- Task I.3 -----------------
    # cities = ['Pittsburgh', 'Charlotte']
    # star_range = [[0.0, 1.5], [2.0, 3.5], [4.5, 5.0]]
    # for city in cities:
    #     for r in star_range:
    #         print "In loop ", city, " - ", r
    #         most_used_words_in_reviews(city, r)

    # --------------- Task II.1 ----------------
    # most_used_words_in_chinese_reviews()

    # ------------- Task II.2 ------------------
    calculate_lda_for_chinese_restaurants()
    pass

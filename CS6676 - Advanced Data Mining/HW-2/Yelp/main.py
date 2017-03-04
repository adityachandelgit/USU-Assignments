import json
import csv
from collections import OrderedDict


def load_checkins_to_dictionary():
    c_ins = {}
    with open('D://Yelp/yelp_academic_dataset_checkin.json') as f2:
        for line in f2:
            total_checkins = 0
            for time in json.loads(line)['time']:
                total_checkins += int(time.split(':')[1])
                c_ins[json.loads(line)['business_id']] = total_checkins
    return c_ins


def review_count(c_ins1):
    count_business_reviews = {}
    with open('D://Yelp/yelp_academic_dataset_review.json') as f3:
        for line in f3:
            business_id = json.loads(line)['business_id']
            if business_id in c_ins1:
                if business_id in count_business_reviews:
                    count_business_reviews[business_id] += 1
                else:
                    count_business_reviews[business_id] = 1
    return count_business_reviews


def business_with_checkin_count(c_ins1):
    business_cins = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            if json.loads(line)['categories'] \
                    and 'Restaurants' in json.loads(line)['categories'] \
                    and (json.loads(line)['city'] == 'Charlotte'):
                if json.loads(line)['business_id'] in c_ins1:
                    business_cins[json.loads(line)['business_id']] = [json.loads(line)['categories'], c_ins1[json.loads(line)['business_id']]]
    return business_cins


def calculate_popular_food_types(data, cnt_reviews):
    popular_food_types = {}
    for key, d in data.iteritems():
        for rest_type in d[0]:
            if rest_type != 'Restaurants':
                if rest_type in popular_food_types:
                    popular_food_types[rest_type] = popular_food_types[rest_type] + d[1] + cnt_reviews[key]
                else:
                    popular_food_types[rest_type] = d[1] + cnt_reviews[key]
    s = OrderedDict(sorted(popular_food_types.items(), key=lambda t: t[1], reverse=True))
    for r in s:
        print str(r) + " : " + str(popular_food_types[r])
    return s


def export_top10_food_location():
    charlotte_top = ['Mexican', 'Southern', 'Italian', 'Japanese', 'Chinese', 'Vietnamese', 'Latin American', 'Thai', 'Greek', 'French']
    pittsburg_top = ['Italian', 'Mexican', 'Chinese', 'Thai', 'Japanese', 'Mediterranean', 'Latin American', 'French', 'Middle Eastern', 'Indian']

    with open('D://Yelp/yelp_academic_dataset_business.json') as f5:
        business = []
        for line in f5:
            if json.loads(line)['city'] == 'Pittsburgh' and (4.5 <= json.loads(line)['stars'] <= 5.0):
                for cat in json.loads(line)['categories']:
                    if cat in pittsburg_top:
                        business.append([
                            json.loads(line)['name'].encode('ascii', 'ignore').decode('ascii'),
                            json.loads(line)['city'],
                            json.loads(line)['latitude'],
                            json.loads(line)['longitude'],
                            json.loads(line)['stars'],
                            cat
                        ])
    with open("output.csv", "wb") as f:
        writer = csv.writer(f)
        writer.writerows(business)



if __name__ == "__main__":
    # checkins = load_checkins_to_dictionary()
    # count_reviews = review_count(checkins)
    # business_with_checkins_foods = business_with_checkin_count(checkins)
    #
    # pop_food = calculate_popular_food_types(business_with_checkins_foods, count_reviews)

    export_top10_food_location()

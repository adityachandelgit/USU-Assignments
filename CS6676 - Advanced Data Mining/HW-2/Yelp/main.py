import json
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


def business_with_checkin_count(c_ins1):
    business_cins = {}
    with open('D://Yelp/yelp_academic_dataset_business.json') as f2:
        for line in f2:
            if json.loads(line)['categories'] and 'Restaurants' in json.loads(line)['categories']:
                if json.loads(line)['business_id'] in c_ins1:
                    business_cins[json.loads(line)['business_id']] = [json.loads(line)['categories'], c_ins1[json.loads(line)['business_id']]]
    return business_cins


if __name__ == "__main__":
    checkins = load_checkins_to_dictionary()
    data = business_with_checkin_count(checkins)

    restaurant_type_count = {}
    for key, d in data.iteritems():
        for rest_type in d[0]:
            if rest_type != 'Restaurants':
                if rest_type in restaurant_type_count:
                    restaurant_type_count[rest_type] = restaurant_type_count[rest_type] + d[1]
                else:
                    restaurant_type_count[rest_type] = d[1]

    sorted = OrderedDict(sorted(restaurant_type_count.items(), key=lambda t: t[1]))

    for r in sorted:
        print str(r) + " : " + str(restaurant_type_count[r])

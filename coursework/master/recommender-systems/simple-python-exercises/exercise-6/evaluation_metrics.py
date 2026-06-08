import pandas as pd
import numpy as np
import metrics_lib as ml
import matplotlib.pyplot as plt
from surprise import Reader, SVD, Dataset
from surprise.model_selection import train_test_split

#Modify the location of the csv file accordingly
ratings = pd.read_csv('ratings.csv')
ratings = ratings.query('rating >=3')
ratings.reset_index(drop=True, inplace=True)

#only consider ratings from users who have rated over n movies
n=1000
users = ratings.userId.value_counts()
users = users[users>n].index.tolist()
ratings = ratings.query('userId in @users')
print(ratings.head(3))


# get movie features
rated_movies = ratings.movieId.tolist()
#Modify the location of the csv file accordingly
movies = pd.read_csv('movies.csv')
movies = movies.query('movieId in @rated_movies')
movies.set_index("movieId", inplace=True, drop=True)

movies = movies.genres.str.split("|", expand=True)
movies.reset_index(inplace=True)
movies = pd.melt(movies, id_vars='movieId', value_vars=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
movies.drop_duplicates("movieId", inplace=True)
movies.set_index('movieId', inplace=True)
movies = pd.get_dummies(movies.value)
#movies = movies[['Action', 'Romance', 'Western', 'Comedy', 'Crime']]
print(movies.head())

#LONG TAIl PLOT
fig = plt.figure(figsize=(15, 7))
ml.long_tail_plot(df=ratings,
             item_id_column="movieId",
             interaction_type="movie ratings",
             percentage=0.5,
             x_labels=False)

#*******************COLLABORATIVE FILTERING RECOMMENDER*************************
##format data for surprise
reader = Reader(rating_scale=(0, 5))
data = Dataset.load_from_df(ratings[['userId', 'movieId', 'rating']], reader)
trainset, testset = train_test_split(data, test_size=0.25)

#train SVD recommender
algo = SVD()
algo.fit(trainset)

#make predictions on test set.
test = algo.test(testset)
test = pd.DataFrame(test)
test.drop("details", inplace=True, axis=1)
test.columns = ['userId', 'movieId', 'current', 'cf_predictions']

cf_model = test.pivot_table(index='userId', columns='movieId', values='cf_predictions').fillna(0)


#get example prediction
#get_users_predictions(156, 10, cf_model)

#format test data
test = test.copy().groupby('userId')['movieId'].agg([ml.actual])

#make recommendations for all members in the test data
cf_recs = [] = []
for user in test.index:
    cf_predictions = ml.get_users_predictions(user, 10, cf_model)
    cf_recs.append(cf_predictions)

test['cf_predictions'] = cf_recs


#*******************POPULARITY RECOMMENDER*************************
# make recommendations for all members in the test data
popularity_recs = ratings.movieId.value_counts().head(10).index.tolist()

pop_recs = []
for user in test.index:
    pop_predictions = popularity_recs
    pop_recs.append(pop_predictions)

test['pop_predictions'] = pop_recs



#*******************RANDOM RECOMMENDER*************************

# make recommendations for all members in the test data

ran_recs = []
for user in test.index:
    random_predictions = ratings.movieId.sample(10).values.tolist()
    ran_recs.append(random_predictions)

test['random_predictions'] = ran_recs

#*********** Test results with movies IDs recommended by users and recommendations provided by the three methods
print("\n **** SOME OF THE  PREDICTIONS RESULTS **** \n")
print(test.head())

actual = test.actual.values.tolist()
cf_predictions = test.cf_predictions.values.tolist()
pop_predictions = test.pop_predictions.values.tolist()
random_predictions = test.random_predictions.values.tolist()

#Computing the minimum recall for every recommender system

pop_mark = []
for K in np.arange(1, 11):
    pop_mark.extend([ml.mark(actual, pop_predictions, k=K)])

random_mark = []
for K in np.arange(1, 11):
    random_mark.extend([ml.mark(actual, random_predictions, k=K)])

cf_mark = []
for K in np.arange(1, 11):
    cf_mark.extend([ml.mark(actual, cf_predictions, k=K)])

#MEAN AVERAGE RECALL @ K

mark_scores = [random_mark, pop_mark, cf_mark]
index = range(1,10+1)
names = ['Random Recommender', 'Popularity Recommender', 'Collaborative Filter']
fig = plt.figure(figsize=(15, 7))
ml.mark_plot(mark_scores, model_names=names, k_range=index)

#COVERAGE
catalog = ratings.movieId.unique().tolist()
random_coverage = ml.prediction_coverage(ran_recs, catalog)
pop_coverage = ml.prediction_coverage(pop_recs, catalog)
cf_coverage = ml.prediction_coverage(cf_recs, catalog)

# N=100 observed recommendation lists
random_cat_coverage = ml.catalog_coverage(ran_recs, catalog, 100)
pop_cat_coverage = ml.catalog_coverage(pop_recs, catalog, 100)
cf_cat_coverage = ml.catalog_coverage(cf_recs, catalog, 100)


# plot of prediction coverage
coverage_scores = [random_coverage, pop_coverage, cf_coverage]
model_names = ['Random Recommender', 'Popularity Recommender', 'Collaborative Filter']

fig = plt.figure(figsize=(7, 5))
ml.coverage_plot(coverage_scores, model_names)

#NOVELTY
print("\n **** NOVELTY METRIC **** \n")
nov = ratings.movieId.value_counts()
pop = dict(nov)
random_novelty,random_mselfinfo_list = ml.novelty(ran_recs, pop, len(users), 10)
pop_novelty,pop_mselfinfo_list = ml.novelty(pop_recs, pop, len(users), 10)
cf_novelty,cf_mselfinfo_list = ml.novelty(cf_recs, pop, len(users), 10)
print(random_novelty, pop_novelty, cf_novelty)
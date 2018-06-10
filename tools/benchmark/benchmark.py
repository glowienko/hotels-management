import glob
import os
import MySQLdb
import time
import datetime
import sys

db_hostname = 'localhost'
db_username = 'root'
db_password = 'root'
db_name = 'hotelsdb'

if not os.path.exists('results'):
    os.mkdir('results')
f_out = open('results/{}.log'.format(datetime.datetime.today().strftime('%Y-%m-%d %H:%M:%S')), mode='w')


def print_benchmark_results(index, filename, duration, query, query_results, file=sys.stdout):
    print('{}. {}'.format(index, filename, duration), file=file)
    print('Duration:')
    print(duration, file=file)
    print('Query:', file=file)
    print(query, file=file)
    print('Result:', file=file)
    print_query_results(query_results, file=file)
    print(file=file)
    print(file=file)
    print(file=file)


def print_query_results(query_results, file=sys.stdout):
    widths = []
    columns = []
    tavnit = '|'
    separator = '+'

    for cd in cursor.description:
        widths.append(max(cd[2], len(cd[0])))
        columns.append(cd[0])

    for w in widths:
        tavnit += " %-"+"%ss |" % (w,)
        separator += '-'*w + '--+'

    print(separator, file=file)
    print(tavnit % tuple(columns), file=file)
    print(separator, file=file)
    for row in query_results:
        print(tavnit % row, file=file)
    print(separator, file=file)


if __name__ == '__main__':
    db = MySQLdb.connect(db_hostname, db_username, db_password, db_name)
    for i, path in enumerate(glob.glob("queries/*.sql")):
        with open(path) as f_in:
            query = ''.join(f_in.readlines())

            begin = time.time()
            cursor = db.cursor()
            cursor.execute(query)
            query_results = cursor.fetchall()
            end = time.time()

            index = i + 1
            filename = os.path.basename(path)
            duration = str(datetime.timedelta(seconds=end - begin))
            print_benchmark_results(index, filename, duration, query, query_results)
            print_benchmark_results(index, filename, duration, query, query_results, file=f_out)

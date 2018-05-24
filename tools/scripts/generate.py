import argparse
import MySQLdb
import faker

generator = faker.Faker()
generator.random.seed(42)

db_hostname = 'localhost'
db_username = 'root'
db_password = 'root'
db_name = 'hotelsdb'

ids_categories = enumerate(['FIRST', 'SECOND', 'THIRD'])


def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('--batch-size', dest='batch_size', type=int, default=100)
    parser.add_argument('--num-of-clients', dest='num_of_clients', type=int, default=10000)
    parser.add_argument('--num-of-discounts', dest='num_of_discounts', type=int, default=1000)
    parser.add_argument('--num-of-prices', dest='num_of_prices', type=int, default=1000)
    return parser.parse_args()


def generate_base(db, args):
    cursor = db.cursor()
    for id, category in ids_categories:
        cursor.execute('INSERT INTO categories '
                       '(id, name) '
                       'VALUES ({}, \'{}\')'.format(id, category))
    db.commit()

    discounts_ids = range(args.num_of_discounts)
    for batch in range(int(len(discounts_ids) / args.batch_size)):
        begin = batch * args.batch_size
        end = (batch + 1) * args.batch_size
        for id in discounts_ids[begin:end]:
            start_date = generator.date_between(start_date='-20y', end_date='-1d')
            end_date = generator.date_between(start_date=start_date, end_date='today')
            percentage = generator.random_number(2)
            cursor.execute('INSERT INTO discounts '
                           '(id, start_date, end_date, percentage) '
                           'VALUES ({}, \'{}\', \'{}\', {})'.format(id, start_date, end_date, percentage))
        db.commit()

    prices_ids = range(args.num_of_prices)
    for batch in range(int(len(prices_ids) / args.batch_size)):
        begin = batch * args.batch_size
        end = (batch + 1) * args.batch_size
        for id in prices_ids[begin:end]:
            start_date = generator.date_between(start_date='-20y', end_date='-1d')
            end_date = generator.date_between(start_date=start_date, end_date='today')
            value = '{}.{}'.format(generator.random_number(4), generator.random_number(2))
            cursor.execute('INSERT INTO prices '
                           '(id, start_date, end_date, value) '
                           'VALUES ({}, \'{}\', \'{}\', \'{}\')'.format(id, start_date, end_date, value))
        db.commit()


def generate_clients(db, args):
    cursor = db.cursor()
    clients_ids = range(args.num_of_clients)
    for batch in range(int(len(clients_ids) / args.batch_size)):
        begin = batch * args.batch_size
        end = (batch + 1) * args.batch_size
        for id in clients_ids[begin:end]:
            first_name = generator.first_name()
            last_name = generator.last_name()
            cursor.execute(
                'INSERT INTO clients '
                '(id, first_name, last_name, phone, email, conference) '
                'VALUES ({}, \'{}\', \'{}\', \'{}\', \'{}\', {})'.format(
                    id,
                    first_name,
                    last_name,
                    generator.random_number(9, fix_len=True),
                    '{}.{}@gmail.com'.format(first_name, last_name),
                    1
                )
            )
            cursor.execute(
                'INSERT INTO client_fields '
                '(id, client_id, type, value) '
                'VALUES ({}, {}, \'{}\', \'{}\')'.format(
                    2 * id,
                    id,
                    'PESEL',
                    generator.random_number(11, fix_len=True)
                )
            )
            cursor.execute(
                'INSERT INTO client_fields '
                '(id, client_id, type, value) '
                'VALUES ({}, {}, \'{}\', \'{}\')'.format(
                    2 * id + 1,
                    id,
                    'ADDRESS',
                    generator.address()
                )
            )
        db.commit()


def generate_hotel(db, args):
    pass


def generate_reservation(db, args):
    pass


def clear_tables(db):
    cursor = db.cursor()
    cursor.execute('SHOW TABLES')
    tables = cursor.fetchall()
    cursor.execute('SET FOREIGN_KEY_CHECKS = 0')
    for table, in tables:
        cursor.execute('TRUNCATE TABLE {}'.format(table))
    cursor.execute('SET FOREIGN_KEY_CHECKS = 1')


if __name__ == '__main__':
    args = parse_args()
    db = MySQLdb.connect(db_hostname, db_username, db_password, db_name)
    clear_tables(db)
    # generate_base(db, args)
    generate_clients(db, args)

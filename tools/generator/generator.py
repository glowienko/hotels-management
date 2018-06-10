from datetime import datetime, timedelta

import MySQLdb
import faker

# TODO Reservations insertion, ensuring consistency (ranges of dates, rooms pricing - total reservation cost, etc.), refactoring

faker = faker.Faker()
faker.random.seed(42)

db_hostname = 'localhost'
db_username = 'root'
db_password = 'root'
db_name = 'hotelsdb'

hotels = 15
buildings = 1
floors = (8, 20)
rooms = (20, 40)
clients = 1000
history_years = 1
discounts = 0.1
discount_duration = 30
price_duration = 30
reservations = 0.8
reservation_duration = 10
batch_size = 100

ids_categories = {
    1: 'FIRST',
    2: 'SECOND',
    3: 'THIRD'
}
ids_reservation_states = {
    1: 'MADE',
    2: 'PAID',
    3: 'IN_PROGRESS',
    4: 'FINISHED'
}

hotels_counter = 0
buildings_counter = 0
rooms_counter = 0
clients_counter = 0
client_fields_counter = 0
discounts_counter = 0
prices_counter = 0
reservations_counter = 0

hotel_img_paths = [l.rstrip('\n') for l in open('img_paths/hotels.txt').readlines()]
room_img_paths = [l.rstrip('\n') for l in open('img_paths/rooms.txt').readlines()]


def clear_tables(db):
    cursor = db.cursor()
    cursor.execute('SHOW TABLES')
    tables = cursor.fetchall()
    cursor.execute('SET FOREIGN_KEY_CHECKS = 0')
    for table, in tables:
        cursor.execute('TRUNCATE TABLE {}'.format(table))
    cursor.execute('SET FOREIGN_KEY_CHECKS = 1')


def generate_base(db):
    cursor = db.cursor()
    for category_id, category in ids_categories.items():
        cursor.execute('INSERT INTO categories '
                       '(id, name) '
                       'VALUES ({}, \'{}\')'.format(category_id, category))
    db.commit()


def generate_hotels(db):
    cursor = db.cursor()
    for _ in range(1, hotels + 1):
        insert_random_hotel(cursor)
        for _ in range(1, buildings + 1):
            random_floors = faker.random_int(*floors)
            rooms_per_floor = faker.random_int(*rooms)
            insert_random_building(cursor, floors=random_floors)
            for i in range(random_floors):
                for j in range(1, rooms_per_floor + 1):
                    j = '{0}{1:02d}'.format(i, j)
                    insert_random_room(cursor, floor=i, number=j)
    db.commit()


def insert_random_hotel(cursor):
    global hotels_counter
    hotels_counter += 1

    hotel = {
        'id': hotels_counter,
        'name': faker.color_name(),
        'location': faker.country(),
        'img_path': faker.random.choice(hotel_img_paths),
        'stars_count': faker.random_number() % 5 + 1,
        'category_id': faker.random.choice(list(ids_categories.keys())),
    }

    cursor.execute(
        'INSERT INTO hotels ( id ,    name   ,    location   ,    img_path   ,  stars_count ,  category_id ) '
        'VALUES             ({id}, \'{name}\', \'{location}\', \'{img_path}\', {stars_count}, {category_id})'.format(**hotel)
    )


def insert_random_building(cursor, floors):
    global buildings_counter
    buildings_counter += 1

    building = {
        'id': buildings_counter,
        'name': faker.color_name(),
        'floors_count': floors,
        'category_id': faker.random.choice(list(ids_categories.keys())),
        'hotel_id': hotels_counter
    }

    cursor.execute(
        'INSERT INTO buildings ( id,     name   ,  floors_count ,  category_id ,  hotel_id ) '
        'VALUES                ({id}, \'{name}\', {floors_count}, {category_id}, {hotel_id})'.format(**building)
    )


def insert_random_room(cursor, floor, number):
    global rooms_counter
    rooms_counter += 1

    room = {
        'id': rooms_counter,
        'floor': floor,
        'number': number,
        'capacity': faker.random_number() % 5 + 1,
        'premium': faker.boolean(),
        'img_path': faker.random.choice(room_img_paths),
        'building_id': buildings_counter
    }

    cursor.execute(
        'INSERT INTO rooms ( id ,  floor ,  number ,  capacity ,  premium ,    img_path   ,  building_id ) '
        'VALUES            ({id}, {floor}, {number}, {capacity}, {premium}, \'{img_path}\', {building_id})'.format(**room)
    )


def generate_clients(db):
    cursor = db.cursor()
    clients_ids = range(clients)
    for batch in range(int(len(clients_ids) / batch_size)):
        begin = batch * batch_size
        end = (batch + 1) * batch_size
        for _ in clients_ids[begin:end]:
            insert_random_client(cursor)
        db.commit()


def insert_random_client(cursor):
    global clients_counter, client_fields_counter
    clients_counter += 1

    first_name = faker.first_name()
    last_name = faker.last_name()
    phone = faker.random_number(9, fix_len=True)
    email = '{}.{}@gmail.com'.format(first_name, last_name)
    conference = faker.boolean()
    pesel = faker.random_number(11, fix_len=True)
    address = faker.address()

    cursor.execute(
        'INSERT INTO clients '
        '(id, first_name, last_name, phone, email, conference) '
        'VALUES ({}, \'{}\', \'{}\', \'{}\', \'{}\', {})'.format(
            clients_counter,
            first_name,
            last_name,
            phone,
            email,
            conference
        )
    )

    client_fields_counter += 1
    cursor.execute(
        'INSERT INTO client_fields '
        '(id, client_id, type, value) '
        'VALUES ({}, {}, \'{}\', \'{}\')'.format(
            client_fields_counter,
            clients_counter,
            'PESEL',
            pesel
        )
    )

    client_fields_counter += 1
    cursor.execute(
        'INSERT INTO client_fields '
        '(id, client_id, type, value) '
        'VALUES ({}, {}, \'{}\', \'{}\')'.format(
            client_fields_counter,
            clients_counter,
            'ADDRESS',
            address
        )
    )


def generate_history(db):
    cursor = db.cursor()

    for i in range(1, hotels_counter + 1):
        for j in range(int(history_years * 365 / discount_duration)):
            if faker.random.random() < discounts:
                insert_random_discount(cursor, j, i, 'hotel')
        db.commit()
    for i in range(1, buildings_counter + 1):
        for j in range(int(history_years * 365 / discount_duration)):
            if faker.random.random() < discounts:
                insert_random_discount(cursor, j, i, 'building')
        db.commit()
    for i in range(1, rooms_counter + 1):
        for j in range(int(history_years * 365 / discount_duration)):
            if faker.random.random() < discounts:
                insert_random_discount(cursor, j, i, 'room')
        db.commit()

    cursor = db.cursor()
    for i in range(1, rooms_counter + 1):
        for j in range(int(history_years * 365 / price_duration)):
            insert_random_price(cursor, j, i)
        db.commit()

    cursor = db.cursor()
    for i in range(1, rooms_counter + 1):
        for _ in range(int(history_years * 365 / reservation_duration)):
            if faker.random.random() < reservations:
                insert_random_reservation(cursor, i, faker.random_int(1, clients_counter))
        db.commit()


def exact_period(number, duration):
    max_number = int(history_years * 365 / duration)
    start_date = datetime.today() - timedelta(days=(max_number - number) * duration)
    end_date = datetime.today() - timedelta((max_number - number - 1) * duration)
    return start_date, end_date


def insert_random_discount(cursor, period_id, type_id, type):
    global discounts_counter
    discounts_counter += 1

    start_date, end_date = exact_period(period_id, discount_duration)

    percentage = faker.random_number(2)
    cursor.execute('INSERT INTO discounts '
                   '(id, start_date, end_date, percentage) '
                   'VALUES ({}, \'{}\', \'{}\', {})'.format(
        discounts_counter,
        start_date,
        end_date,
        percentage
    ))

    cursor.execute('INSERT INTO {}s_discounts '
                   '(discount_id, {}_id) '
                   'VALUES ({}, {})'.format(
        type,
        type,
        discounts_counter,
        type_id
    ))


def insert_random_price(cursor, period_id, room_id):
    global prices_counter
    prices_counter += 1

    start_date, end_date = exact_period(period_id, price_duration)

    value = '{}0.00'.format(faker.random_number(3))
    cursor.execute('INSERT INTO prices '
                   '(id, start_date, end_date, value) '
                   'VALUES ({}, \'{}\', \'{}\', \'{}\')'.format(
        prices_counter,
        start_date,
        end_date,
        value))

    cursor.execute('INSERT INTO rooms_prices '
                   '(price_id, room_id) '
                   'VALUES ({}, {})'.format(
        prices_counter,
        room_id
    ))


def insert_random_reservation(cursor, room_id, client_id):
    global reservations_counter
    reservations_counter += 1

    start_date = faker.date_between(start_date='-20y', end_date='-1d')
    end_date = faker.date_between(start_date=start_date, end_date='today')
    cost = 0.0
    state = faker.random.choice(list(ids_reservation_states.values()))
    cursor.execute('INSERT INTO reservations '
                   '(id, check_in_date, check_out_date, cost, state, client_id) '
                   'VALUES ({}, \'{}\', \'{}\', {}, \'{}\', {})'.format(
        reservations_counter,
        start_date,
        end_date,
        cost,
        state,
        client_id
    ))

    cursor.execute('INSERT INTO rooms_reservations '
                   '(room_id, reservation_id) '
                   'VALUES ({}, {})'.format(
        room_id,
        reservations_counter
    ))


if __name__ == '__main__':
    db = MySQLdb.connect(db_hostname, db_username, db_password, db_name)
    print("Clearing tables...")
    clear_tables(db)
    print("Generating base...")
    generate_base(db)
    print("Generating hotels...")
    generate_hotels(db)
    print("Generating clients...")
    generate_clients(db)
    print("Generating history...")
    generate_history(db)
    print("Done!")

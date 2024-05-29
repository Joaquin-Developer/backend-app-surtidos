"""utils module"""
import os
import logging
from typing import List, Any

import pymysql
from pymysql.connections import Connection
from pymysql.cursors import Cursor

from db import DB_CONFIG


logging.basicConfig(level=logging.INFO)

ENV = os.getenv("environment") or "prod"


def get_connection() -> Connection:
    db_config = DB_CONFIG[ENV]

    return pymysql.connect(
        host=db_config["host"],
        user=db_config["user"],
        password=db_config["password"],
        database=db_config["database"]
    )


def execute_query(sql_query: str, data: tuple):
    connection = get_connection()
    cursor: Cursor = connection.cursor()

    try:
        cursor.execute(sql_query, data)
    except Exception as error:
        logging.warning(error)
        connection.rollback()
    else:
        connection.commit()
        cursor.close()
        connection.close()


def get_data(sql_query: str, data: tuple) -> List[Any]:
    connection = get_connection()
    cursor: Cursor = connection.cursor()
    cursor.execute(sql_query, data)
    rows = cursor.fetchall()
    cursor.close()
    connection.close()
    return rows

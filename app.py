"""Flask API"""
from typing import List
import json
import logging

from flask import Flask, request, jsonify
from flask_cors import CORS
import pymysql
from pymysql.connections import Connection
from pymysql.cursors import Cursor
from werkzeug.middleware.proxy_fix import ProxyFix

from db import DB_CONFIG


logging.basicConfig(level=logging.INFO)

app = Flask(__name__)
CORS(app)

app.wsgi_app = ProxyFix(app.wsgi_app, x_for=1, x_proto=1, x_host=1, x_port=1, x_prefix=1)


def get_connection() -> Connection:
    return pymysql.connect(
        host=DB_CONFIG["host"],
        user=DB_CONFIG["user"],
        password=DB_CONFIG["password"],
        database=DB_CONFIG["database"]
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


def get_data(sql_query: str, data: tuple) -> List:
    connection = get_connection()
    cursor: Cursor = connection.cursor()
    cursor.execute(sql_query, data)
    rows = cursor.fetchall()
    cursor.close()
    connection.close()
    return rows


@app.route("/", methods=["GET"])
def index():
    client_ip = request.headers.get('X-Forwarded-For', request.remote_addr)
    return jsonify({"ip_client": client_ip}), 200


@app.route("/save_surtido_data", methods=["POST"])
def add_data():
    if not request.json:
        return jsonify({"error": "Invalid input"}), 400
    req = request.json[0]
    print(req)

    username = req["username"]
    json_products = req["json_products"]
    total_price = req["total_price"]

    sql = """
        INSERT INTO data_surtidos (username, audit_date, json_products, total_price)
        VALUES (%s, now(), %s, %s)
    """
    try:
        execute_query(sql, (username, json_products, total_price))
        return jsonify({"message": "Data added successfully!"}), 201
    except:
        return jsonify({"error": "Internal server Error"}), 500


@app.route("/get_surtidos_data/<user>", methods=["GET"])
def get_surtidos_data_by_user(user: str):
    if not user:
        return jsonify({"error": "Invalid input"}), 400

    sql = """
        SELECT surtido_id, username, audit_date, json_products, total_price 
        FROM data_surtidos
        WHERE lower(username) = lower(%s)
    """

    try:
        data = get_data(sql, (user))
        json_data = [
            {
                "surtido_id": row[0],
                "username": row[1],
                "audit_date": str(row[2]),
                "json_products": json.loads(row[3]),
                "total_price": float(row[4])
            }
            for row in data
        ]
        json_data = sorted(json_data, key=lambda x: x["audit_date"])
        return jsonify(json_data), 200

    except:
        return jsonify({"error": "Internal server Error"}), 500


if __name__ == "__main__":
    app.run(debug=True)

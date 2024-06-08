"""Flask API"""
import os
import json
import logging

from flask import Flask, request, jsonify
from flask_cors import CORS
from werkzeug.middleware.proxy_fix import ProxyFix

import utils


logging.basicConfig(level=logging.INFO)

app = Flask(__name__)
CORS(app)

app.wsgi_app = ProxyFix(app.wsgi_app, x_for=1, x_proto=1, x_host=1, x_port=1, x_prefix=1)

ENV = os.getenv("environment") or "prod"


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
    json_products = str(req["json_products"])
    total_price = req["total_price"]
    audit_date = req["audit_date"]

    sql = f"""
        INSERT INTO data_surtidos (username, audit_date, json_products, total_price)
        VALUES (%s, '{audit_date}', %s, %s)
        ON DUPLICATE KEY UPDATE
        json_products = VALUES(json_products),
        total_price = VALUES(total_price),
        audit_date = '{audit_date}'
    """

    try:
        utils.execute_query(sql, (username, json_products, total_price))
        return jsonify({"message": "Data added successfully!"}), 201
    except Exception as error:
        logging.warning(error)
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
        data = utils.get_data(sql, (user))
        json_data = [
            {
                "surtido_id": row[0],
                "username": row[1],
                "audit_date": str(row[2]),
                "json_products": json.loads(json.dumps(eval(row[3]))),
                "total_price": float(row[4])
            }
            for row in data
            if float(row[4]) > 0
        ]
        json_data = sorted(json_data, key=lambda x: x["audit_date"])
        return jsonify(json_data), 200

    except Exception as error:
        logging.warning(error)
        return jsonify({"error": "Internal server Error", "msg": str(error)}), 500


if __name__ == "__main__":
    app.run(debug=(ENV != "prod"))

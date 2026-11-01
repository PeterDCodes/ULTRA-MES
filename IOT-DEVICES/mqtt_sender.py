import paho.mqtt.publish as publish

# Publish a single message to a public test broker


def publish_message(topic, message):

    publish.single(
        topic=topic, 
        payload=message, 
        hostname="localhost", 
        port=1883
    )

    print("Message sent!")
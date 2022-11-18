#!/bin/sh
echo "Init localstack..."

awslocal sns create-topic --name my-sns
awslocal sqs create-queue --queue-name sample-queue

echo "Init localstack finished!"
#!/bin/sh

#create your SQS names
SQS_NAME_1='sqs-1'
SQS_NAME_2='sqs-2'
#localstack default SQS url
DEFAULT_SQS_URL_1='http://localhost:4566/000000000000/'$SQS_NAME_1
DEFAULT_SQS_URL_2='http://localhost:4566/000000000000/'$SQS_NAME_2

#create your SNS names
SNS_NAME_1='sns-1'
#localstack default SNS arn
DEFAULT_SNS_ARN_1='arn:aws:sns:ap-northeast-2:000000000000:'$SNS_NAME_1

echo "Init localstack..."

awslocal sqs create-queue --queue-name $SQS_NAME_1
awslocal sqs create-queue --queue-name $SQS_NAME_2

awslocal sns create-topic --name $SNS_NAME_1

awslocal sns subscribe --topic-arn $DEFAULT_SNS_ARN_1 --protocol sqs --notification-endpoint $DEFAULT_SQS_URL_1
awslocal sns subscribe --topic-arn $DEFAULT_SNS_ARN_1 --protocol sqs --notification-endpoint $DEFAULT_SQS_URL_2

echo "SNS subscriptions list >>>>>"
awslocal sns list-subscriptions

echo "Created SQS list >>>>>>"
awslocal sqs list-queues

echo "Init localstack finished!"
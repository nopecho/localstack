#!/bin/sh

# standard SQS
SQS_NAME='sqs'
STANDARD_SQS_URL='http://localhost:4566/000000000000/'$SQS_NAME
# fifo SQS
SQS_NAME_FIFO='sqs.fifo'
FIFO_SQS_URL='http://localhost:4566/000000000000/'$SQS_NAME_FIFO

# standard SNS topic
SNS_NAME='sns'
STANDARD_SNS_ARN='arn:aws:sns:ap-northeast-2:000000000000:'$SNS_NAME
# fifo SNS topic
SNS_NAME_FIFO='sns.fifo'
FIFO_SNS_ARN='arn:aws:sns:ap-northeast-2:000000000000:'$SNS_NAME_FIFO

echo "Init localstack..."
# create standard SQS, SNS
awslocal sqs create-queue --queue-name $SQS_NAME
awslocal sns create-topic --name $SNS_NAME
# subscribe SNS topic
awslocal sns subscribe --topic-arn $STANDARD_SNS_ARN --protocol sqs --notification-endpoint $STANDARD_SQS_URL

# create fifo SQS ,SNS
awslocal sqs create-queue --queue-name $SQS_NAME_FIFO --attributes FifoQueue=true
awslocal sns create-topic --name $SNS_NAME_FIFO --attributes FifoTopic=true
# subscribe SNS topic
awslocal sns subscribe --topic-arn $FIFO_SNS_ARN --protocol sqs --notification-endpoint $FIFO_SQS_URL

echo "SNS subscriptions list >>>>>"
awslocal sns list-subscriptions

echo "Created SQS list >>>>>>"
awslocal sqs list-queues

echo "Init localstack finished :)"
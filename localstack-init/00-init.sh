#!/bin/sh

########## Setting SQS ##########
DEFAULT_AWS_URL='http://localhost:4566/000000000000/'

# standard SQS
SQS_NAME='any-sqs'
STANDARD_SQS_URL=$DEFAULT_AWS_URL$SQS_NAME

SQS_NAME2='any-sqs2'
STANDARD_SQS_URL_2=$DEFAULT_AWS_URL$SQS_NAME2

# fifo SQS
SQS_NAME_FIFO='any-sqs.fifo'
FIFO_SQS_URL=$DEFAULT_AWS_URL$SQS_NAME_FIFO
# create more SQS...

########## Setting SNS ##########
DEFAULT_SNS_ARN='arn:aws:sns:ap-northeast-2:000000000000:'

# standard SNS topic
SNS_NAME='any-sns'
STANDARD_SNS_ARN=$DEFAULT_SNS_ARN$SNS_NAME

# fifo SNS topic
SNS_NAME_FIFO='any-sns.fifo'
FIFO_SNS_ARN=$DEFAULT_SNS_ARN$SNS_NAME_FIFO
# create more SNS...

########## CLI Command SQS, SNS ##########
echo "Init localstack..."

########## STANDARD SQS, SNS ##########
# create standard SQS, SNS
awslocal sqs create-queue \
--queue-name $SQS_NAME

awslocal sqs create-queue \
--queue-name $SQS_NAME2

awslocal sns create-topic \
--name $SNS_NAME

# subscribe standard SNS topic
awslocal sns subscribe \
--topic-arn $STANDARD_SNS_ARN \
--protocol sqs \
--notification-endpoint $STANDARD_SQS_URL

awslocal sns subscribe \
--topic-arn $STANDARD_SNS_ARN \
--protocol sqs \
--notification-endpoint $STANDARD_SQS_URL_2

########## FIFO SQS, SNS ##########
# create fifo SQS ,SNS
awslocal sqs create-queue \
--queue-name $SQS_NAME_FIFO \
--attributes FifoQueue=true

awslocal sns create-topic \
--name $SNS_NAME_FIFO \
--attributes FifoTopic=true

# subscribe fifo SNS topic
awslocal sns subscribe \
--topic-arn $FIFO_SNS_ARN \
--protocol sqs \
--notification-endpoint $FIFO_SQS_URL

echo "Init localstack finished :)"
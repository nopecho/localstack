## http://korea.gnu.org/manual/release/make/make-sjp/make-ko_toc.html
.PHONY : help stop-aws start-aws
.DEFAULT : xxx

LOCAL_AWS_PORT ?= 4566
LOCAL_AWS_SERVICES ?= sns,sqs
LOCAL_AWS_ACCESS_KEY_ID ?= test
LOCAL_AWS_SECRET_ACCESS_KEY ?= test
LOCAL_AWS_DEFAULT_REGION ?= ap-northeast-2
LOCAL_AWS_CONTAINER := local-aws
RUNNING_AWS_CONTAINER := $(shell docker ps -f name=$(LOCAL_AWS_CONTAINER) --format "{{.Names}}")

## https://gist.github.com/prwhite/8168133#gistcomment-3785627
help: ## show help message
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[$$()% 0-9a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

stop-aws: ## Stop localstack Docker Container
ifeq ($(RUNNING_AWS_CONTAINER),$(LOCAL_AWS_CONTAINER))
	docker stop $(LOCAL_AWS_CONTAINER)
else
	@echo "$(LOCAL_AWS_CONTAINER) is not running."
endif

start-aws: ## Start localstack Docker Container
ifneq ($(RUNNING_AWS_CONTAINER),$(LOCAL_AWS_CONTAINER))
	@docker run --rm --name $(LOCAL_AWS_CONTAINER) -d \
	-v ${PWD}/localstack-init:/docker-entrypoint-initaws.d \
	-v /var/run/docker.sock:/var/run/docker.sock \
	-p $(LOCAL_AWS_PORT):4566 \
	-e SERVICES=$(LOCAL_AWS_SERVICES) \
	-e AWS_ACCESS_KEY_ID=$(LOCAL_AWS_ACCESS_KEY_ID) \
	-e AWS_SECRET_ACCESS_KEY=$(LOCAL_AWS_SECRET_ACCESS_KEY) \
	-e AWS_DEFAULT_REGION=$(LOCAL_AWS_DEFAULT_REGION) \
	localstack/localstack:1.2.0
else
	@echo "$(LOCAL_AWS_CONTAINER) is Already running."
endif

show-aws: ## Show localstack config
ifeq ($(RUNNING_AWS_CONTAINER),$(LOCAL_AWS_CONTAINER))
	@echo ">>>>> Created SQS list >>>>>>"
	@docker exec $(LOCAL_AWS_CONTAINER) awslocal sqs list-queues
	@echo ">>>>> SNS subscriptions list >>>>>"
	@docker exec $(LOCAL_AWS_CONTAINER) awslocal sns list-subscriptions

else
	@echo "$(LOCAL_AWS_CONTAINER) is not running."
endif
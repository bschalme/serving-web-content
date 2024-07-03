# Serving Web Content

This is the exercise from the Spring Getting Started guide [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/).

# Building

The usual `./mvnw clean install` works just fine.

# Deploying

## To Amazon Elastic Container Registry (ECR)

First, set up some environment variables. You only need to do these once. The line beginning with `aws ecr get-logon-password` command gives you a 12-hour authorization token that the `docker login` command uses to login to your AWS Elastic Container Registry:

```
export AWS_REGION=<YourAwsRegion> # E.g. export AWS_REGION=us-east-1
export AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query 'Account' --output text)
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
export ECR_REGISTRY=$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
export ECR_REPOSITORY=serving-web-content
export DOCKER_DEFAULT_PLATFORM=linux/amd64
```

Now go build and deploy:

```
docker build -t $ECR_REGISTRY/$ECR_REPOSITORY\:latest .
docker push $ECR_REGISTRY/$ECR_REPOSITORY\:latest
```


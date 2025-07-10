# amz-link-account


# goal
This repository contains a basic Nest.js project with an example
implementation of Amazon Instant Access signing utilities.
The project demonstrates how to sign and verify payloads similar to the
[Amazon Instant Access Java SDK](https://github.com/amzn/amazon-instant-access-sdk-java).

## Getting Started

```bash
cd backend
npm install
npm run start
```

Environment variables `PRIVATE_KEY` and `PUBLIC_KEY` can be set to provide
keys used when calling the `/instant-access/sign` and `/instant-access/verify`
endpoints.


# todo list
- make controller
- make service
- trans https://github.com/amzn/amazon-instant-access-sdk-java in nest.js way

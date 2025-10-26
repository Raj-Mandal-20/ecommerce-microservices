FROM node:18-alpine

WORKDIR /app

COPY services/product-service/package*.json ./

RUN npm install

COPY services/product-service ./ 

COPY proto ./proto

EXPOSE 5050 50051

CMD ["npm", "run", "dev"]

FROM node:lts-alpine

# set the working direction
# WORKDIR /frontend
WORKDIR /app

# install app dependencies
COPY package.json ./
COPY package-lock.json ./

# Fix permissions for packages
# RUN npm config set unsafe-perm true

RUN npm install

# Bundle app source
# COPY . ./
COPY . .

# RUN chown -R node:node /frontend/node_modules

# start app
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]
# CMD ["npm", "run", "dev"]
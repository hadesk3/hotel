# Use an official Node.js runtime as a parent image
FROM node:22.11.0 as build

# Set the working directory in the container
WORKDIR /app

# Copy the package.json and package-lock.json (or yarn.lock) files to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code to the container
COPY . .

# Build the React app for production
RUN npm run build


EXPOSE 3000

# Start the React app or Node.js server (nếu là ứng dụng React, có thể dùng react-scripts)
CMD ["npm", "start"]

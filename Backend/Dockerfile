# Set base image
FROM node:18

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY ["package.json", "package-lock.json*", "./"]

# Install dependencies
RUN npm install

# Copy source code
COPY . .

# Expose port
EXPOSE 8080

# Run the application
CMD ["npm", "start"]
